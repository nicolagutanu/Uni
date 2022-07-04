from block import Block


def write_to_file(list, file_name):
    f = open(file_name, "w")
    for b in list:
        f.write(str(b))
        f.write("\n")
    f.close()


def make_average(row, col, mat):
    avg = 0
    for i in range(row, row + 2):
        for j in range(col, col + 2):
            avg += mat[i][j]
    return float(format(avg/4, ".2f"))


class Encoder:
    def __init__(self, ppm):
        self.width = ppm.width
        self.height = ppm.height

        self.red = ppm.red
        self.green = ppm.green
        self.blue = ppm.blue

        self.y = ppm.y
        self.u = ppm.u
        self.v = ppm.v

        self.list_y = []
        self.list_u = []
        self.list_v = []

        self.dct_y = []
        self.dct_u = []
        self.dct_v = []

        self.output = []

        self.quantization_matrix = [[6, 4, 4, 6, 10, 16, 20, 24],
                                    [5, 5, 6, 8, 10, 23, 24, 22],
                                    [6, 5, 6, 10, 16, 23, 28, 22],
                                    [6, 7, 9, 12, 20, 35, 32, 25],
                                    [7, 9, 15, 22, 27, 44, 41, 31],
                                    [10, 14, 22, 26, 32, 42, 45, 37],
                                    [20, 26, 31, 35, 41, 48, 48, 40],
                                    [29, 37, 38, 39, 45, 40, 41, 40]]

    def make_y_block_list(self):
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                self.list_y.append(self.make_64_block(i, j))

    def make_64_block(self, row, col):
        new_block = Block()
        new_block.row = row
        new_block.col = col
        new_block.type = "Y"
        new_block.size = 8
        for i in range(row, row+8):
            l = []
            for j in range(col, col+8):
                l.append(self.y[i][j])
            new_block.mat.append(l)
        return new_block

    def make_uv_block_list(self):
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                self.list_u.append(self.make_16_u_block(i, j))
                self.list_v.append(self.make_16_v_block(i, j))

    def make_16_u_block(self, row, col):
        new_block = Block()
        new_block.row = row
        new_block.col = col
        new_block.type = "U"
        new_block.size = 4
        for i in range(row, row + 8, 2):
            l = []
            for j in range(col, col + 8, 2):
                l.append(make_average(i, j, self.u))
            new_block.mat.append(l)
        return new_block

    def make_16_v_block(self, row, col):
        new_block = Block()
        new_block.row = row
        new_block.col = col
        new_block.type = "V"
        new_block.size = 4
        for i in range(row, row + 8, 2):
            l = []
            for j in range(col, col + 8, 2):
                l.append(make_average(i, j, self.v))
            new_block.mat.append(l)
        return new_block

    def make_u_into_8x8(self):
        for block in self.list_u:
            block.mat = block.make_into_8x8()
            block.size = 8

    def make_v_into_8x8(self):
        for block in self.list_v:
            block.mat = block.make_into_8x8()
            block.size = 8

    def prepare_y(self):
        for block in self.list_y:
            for i in range(block.size):
                for j in range(block.size):
                    block.mat[i][j] = block.mat[i][j] - 128

    def yuv_to_dct_blocks(self):
        for block in self.list_y:
            new_block = Block()
            new_block.mat = block.make_dct_matrix()
            new_block.size = 8
            new_block.type = "Y"
            new_block.row = block.row
            new_block.col = block.col
            self.dct_y.append(new_block)

        for block in self.list_u:
            new_block = Block()
            new_block.mat = block.make_dct_matrix()
            new_block.size = 8
            new_block.type = "U"
            new_block.row = block.row
            new_block.col = block.col
            self.dct_u.append(new_block)

        for block in self.list_v:
            new_block = Block()
            new_block.mat = block.make_dct_matrix()
            new_block.size = 8
            new_block.type = "V"
            new_block.row = block.row
            new_block.col = block.col
            self.dct_v.append(new_block)

    def quantization_phase(self):
        for block in self.dct_y:
            for i in range(block.size):
                for j in range(block.size):
                    block.mat[i][j] = int(block.mat[i][j] / self.quantization_matrix[i][j])

        for block in self.dct_u:
            for i in range(block.size):
                for j in range(block.size):
                    block.mat[i][j] = int(block.mat[i][j] / self.quantization_matrix[i][j])

        for block in self.dct_v:
            for i in range(block.size):
                for j in range(block.size):
                    block.mat[i][j] = int(block.mat[i][j] / self.quantization_matrix[i][j])

    def get_size(self, elem):
        if abs(elem) == 1:
            return 1
        elif abs(elem) in [2, 3]:
            return 2
        elif 4 <= abs(elem) <= 7:
            return 3
        elif 8 <= abs(elem) <= 15:
            return 4
        elif 16 <= abs(elem) <= 31:
            return 5
        elif 32 <= abs(elem) <= 63:
            return 6
        elif 64 <= abs(elem) <= 127:
            return 7
        elif 128 <= abs(elem) <= 255:
            return 8
        elif 256 <= abs(elem) <= 511:
            return 9
        elif 512 <= abs(elem) <= 1023:
            return 10

    def make_encoded_list_from_zigzag(self, zigzag_list):
        result = [(self.get_size(zigzag_list[0]), zigzag_list[0])]
        zeros = 0
        for i in range(1, len(zigzag_list)):
            elem = zigzag_list[i]
            if elem == 0:
                zeros += 1
            else:
                result.append((zeros, self.get_size(elem), elem))
                zeros = 0
        if zeros != 0:
            result.append((0, 0))
        return result

    def make_zigzag_list(self, matrix):
        sol = [[] for i in range(15)]
        for i in range(8):
            for j in range(8):
                sum = i + j
                if sum % 2 == 0:
                    sol[sum].insert(0, matrix[i][j])
                else:
                    sol[sum].append(matrix[i][j])
        final_list = []
        for i in sol:
            for j in i:
                final_list.append(j)
        return self.make_encoded_list_from_zigzag(final_list)

    def encode_output_array(self):
        for i in range(len(self.dct_y)):
            self.output.extend(self.make_zigzag_list(self.dct_y[i].mat))
            self.output.extend(self.make_zigzag_list(self.dct_u[i].mat))
            self.output.extend(self.make_zigzag_list(self.dct_v[i].mat))

    def display(self, block_list):
        for b in block_list:
            print(str(b))