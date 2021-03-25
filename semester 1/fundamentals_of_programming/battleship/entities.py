

class Ship(object):

    def __init__(self):
        self.__ocean = []
        for i in range(0, 5):
            self.__ocean.append([".", ".", ".", ".", ".", ".", "."])

    def __str__(self):
        print("  A B C D E F ")
        m = ""
        for i in range(5):
            m += str(i) + " " + str(self.__ocean[i][0]) + " " + str(self.__ocean[i][1]) + " " + str(self.__ocean[i][2]) + " " + str(self.__ocean[i][3]) + " " + str(self.__ocean[i][4]) + " " + str(self.__ocean[i][5]) + "\n"
        return m

    def get_ocean(self):
        return self.__ocean