

class Earth(object):

    def __init__(self):
        self.__mat = []
        for i in range(7):
            self.__mat.append([" ", " ", " ", " ", " ", " ", " "])
        self.__mat[3][3] = "E"

    def __str__(self):
        m = "  A B C D E F G" + "\n"
        for i in range(7):
            m += str(i+1) + "|" + self.__mat[0][i] + "|" + self.__mat[1][i] + "|" + self.__mat[2][i] + "|" + self.__mat[3][i] + "|" + self.__mat[4][i] + "|" + self.__mat[5][i] + "|" + self.__mat[6][i] + "|" + "\n"
        return m

    def get_mat(self):
        return self.__mat
