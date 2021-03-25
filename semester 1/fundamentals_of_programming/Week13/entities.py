
class Tabla(object):

    def __init__(self):
        self.__mat = []
        self.__height = [-1, -1, -1, -1, -1, -1, -1]
        for x in range(0, 6):
            self.__mat.append([' ', ' ', ' ', ' ', ' ', ' ', ' '])

    def __str__(self):
        m = ''
        for x in range(len(self.__mat)-1, -1, -1):
            m += str(self.__mat[x][0]) + " | " + str(self.__mat[x][1]) + " | " + str(self.__mat[x][2]) + " | "  + str(self.__mat[x][3]) + " | " + str(self.__mat[x][4]) + " | " + str(self.__mat[x][5]) + " | " + str(self.__mat[x][6]) + '\n'
        return m

    def get_mat(self):
        return self.__mat

    def get_height(self):
        return self.__height