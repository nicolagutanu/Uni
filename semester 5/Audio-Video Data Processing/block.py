import math


class Block:
    def __init__(self):
        self.mat = []
        self.type = ""
        self.row = 0
        self.col = 0
        self.size = 0

    def __str__(self):
        values = "Block={"
        for i in range(self.size):
            for j in range(self.size):
                values += str(self.mat[i][j]) + " "
        values += self.type + " " + str(self.row) + " " + str(self.col) + "}"
        return values

    def make_into_8x8(self):
        new_mat = []
        for i in range(self.size):
            row1 = []
            row2 = []
            for j in range(self.size):
                row1.append(self.mat[i][j] - 128)
                row1.append(self.mat[i][j] - 128)
                row2.append(self.mat[i][j] - 128)
                row2.append(self.mat[i][j] - 128)
            new_mat.append(row1)
            new_mat.append(row2)
        return new_mat

    def make_dct_matrix(self):
        new_mat = []
        for i in range(8):
            row = []
            for j in range(8):
                row.append(self.make_one_dct_value(i, j))
            new_mat.append(row)
        return new_mat

    def make_one_dct_value(self, u, v):
        total = 0
        for x in range(self.size):
            for y in range(self.size):
                total += self.mat[x][y] * math.cos(((2*x + 1) * u * math.pi) / 16) * math.cos(((2*y + 1) * v * math.pi) / 16)
        return self.before_coeff(u, v) * total

    def before_coeff(self, u, v):
        final = 1/4
        uf = vf = 1
        if u == 0:
            uf = 1/math.sqrt(2)
        elif u > 0:
            uf = 1
        if v == 0:
            vf = 1/math.sqrt(2)
        elif v > 0:
            vf = 1
        return final * uf * vf

    def make_yuv_matrix(self):
        new_mat = []
        for i in range(8):
            row = []
            for j in range(8):
                row.append(self.make_one_yuv_value(i, j))
            new_mat.append(row)
        return new_mat

    def make_one_yuv_value(self, x, y):
        total = 0
        for u in range(self.size):
            for v in range(self.size):
                total += self.coeff(u, v) * self.mat[u][v] * math.cos(((2 * x + 1) * u * math.pi) / 16) * math.cos(((2 * y + 1) * v * math.pi) / 16)
        return (1/4) * total + 128

    def coeff(self, u, v):
        uf = vf = 1
        if u == 0:
            uf = 1 / math.sqrt(2)
        elif u > 0:
            uf = 1
        if v == 0:
            vf = 1 / math.sqrt(2)
        elif v > 0:
            vf = 1
        return uf * vf
