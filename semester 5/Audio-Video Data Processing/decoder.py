from block import Block


class Decoder:
    def __init__(self, encoder):
        self.width = encoder.width
        self.height = encoder.height

        self.red = []
        self.green = []
        self.blue = []

        self.y = [[0 for x in range(self.width)] for y in range(self.height)]
        self.u = [[0 for x in range(self.width)] for y in range(self.height)]
        self.v = [[0 for x in range(self.width)] for y in range(self.height)]

        self.list_y = []
        self.list_u = []
        self.list_v = []

        self.dct_y = []
        self.dct_u = []
        self.dct_v = []

        self.output = encoder.output

        self.quantization_matrix = [[6, 4, 4, 6, 10, 16, 20, 24],
                                    [5, 5, 6, 8, 10, 23, 24, 22],
                                    [6, 5, 6, 10, 16, 23, 28, 22],
                                    [6, 7, 9, 12, 20, 35, 32, 25],
                                    [7, 9, 15, 22, 27, 44, 41, 31],
                                    [10, 14, 22, 26, 32, 42, 45, 37],
                                    [20, 26, 31, 35, 41, 48, 48, 40],
                                    [29, 37, 38, 39, 45, 40, 41, 40]]

    def make_y_from_list(self):
        for block in self.list_y:
            self.place_in_y(block.row, block.col, block)

    def place_in_y(self, row, col, block):
        bi = 0
        for i in range(row, row+8):
            bj = 0
            for j in range(col, col+8):
                self.y[i][j] = block.mat[bi][bj]
                bj += 1
            bi += 1

    def make_u_from_list(self):
        for block in self.list_u:
            self.place_in_u(block.row, block.col, block)

    def place_in_u(self, row, col, block):
        bi = 0
        for i in range(row, row+8):
            bj = 0
            for j in range(col, col+8):
                self.u[i][j] = block.mat[bi][bj]
                bj += 1
            bi += 1

    def make_v_from_list(self):
        for block in self.list_v:
            self.place_in_v(block.row, block.col, block)

    def place_in_v(self, row, col, block):
        bi = 0
        for i in range(row, row+8):
            bj = 0
            for j in range(col, col+8):
                self.v[i][j] = block.mat[bi][bj]
                bj += 1
            bi += 1

    def dequantization_phase(self):
        for block in self.dct_y:
            for i in range(block.size):
                for j in range(block.size):
                    block.mat[i][j] = block.mat[i][j] * self.quantization_matrix[i][j]

        for block in self.dct_u:
            for i in range(block.size):
                for j in range(block.size):
                    block.mat[i][j] = block.mat[i][j] * self.quantization_matrix[i][j]

        for block in self.dct_v:
            for i in range(block.size):
                for j in range(block.size):
                    block.mat[i][j] = block.mat[i][j] * self.quantization_matrix[i][j]

    def dct_to_yuv(self):
        for block in self.dct_y:
            block.mat = block.make_yuv_matrix()
            self.list_y.append(block)

        for block in self.dct_u:
            block.mat = block.make_yuv_matrix()
            self.list_u.append(block)

        for block in self.dct_v:
            block.mat = block.make_yuv_matrix()
            self.list_v.append(block)

    def make_list_for_zigzag(self, list_elems):
        # list_elems = [(2, 4), (0, 8, 151), ..., (0, 0)]
        # first = (size, amplitude)
        # last = finishes with 0
        # inbetween = (count zeros before, size, amplitude)
        result = []
        for elem in list_elems:
            if len(elem) == 3:
                if elem[0] == 0:
                    result.append(elem[2])
                else:
                    for i in range(elem[0]):
                        result.append(0)
                    result.append(elem[2])
            elif elem[1] != 0:
                result.append(elem[1])
            else:
                steps = len(result)
                for i in range(0, 64 - steps):
                    result.append(0)
        return result

    def make_matrix_for_block(self, list_zigzag):
        result = [[0 for i in range(8)] for j in range(8)]
        i, j = 0, 0
        while i + j != 14:
            if (i + j) % 2 == 0:
                k = i
                p = j
                while k >= 0 and p <= 7:
                    if list_zigzag[0] != 0:
                        result[k][p] = list_zigzag.pop(0)
                    else:
                        list_zigzag.pop(0)
                    k -= 1
                    p += 1
                if p == 8:
                    i = k + 2
                    j = p - 1
                else:
                    i = k + 1
                    j = p
            else:
                k = i
                p = j
                while p >= 0 and k <= 7:
                    if list_zigzag[0] != 0:
                        result[k][p] = list_zigzag.pop(0)
                    else:
                        list_zigzag.pop(0)
                    k += 1
                    p -= 1
                if k == 8:
                    i = k - 1
                    j = p + 2
                else:
                    i = k
                    j = p + 1
        return result

    def reconstruct_dct_from_encoded_output(self):
        help = []
        i = 0
        row = 0
        col = 0
        for o in self.output:
            if len(o) == 2 and o[0] == 0:
                help.append(o)
                matrix = self.make_matrix_for_block(self.make_list_for_zigzag(help))
                new_block = Block()
                new_block.row = row
                new_block.col = col
                new_block.size = 8
                new_block.mat = matrix
                if i == 0:
                    new_block.type = "Y"
                    self.dct_y.append(new_block)
                if i == 1:
                    new_block.type = "U"
                    self.dct_u.append(new_block)
                if i == 2:
                    new_block.type = "V"
                    self.dct_v.append(new_block)
                i += 1
                if i == 3:
                    i = 0
                    if col == self.width - 8:
                        row += 8
                        col = 0
                    else:
                        col += 8
                help.clear()
            else:
                help.append(o)
