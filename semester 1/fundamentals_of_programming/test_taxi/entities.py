

class Orders(object):

    def __init__(self, id_d, dist):
        self.__id_d = id_d
        self.__dist = dist

    def get_id_d(self):
        return self.__id_d

    def get_dist(self):
        return self.__dist

    def set_dist(self, value):
        self.__dist = value

    def __eq__(self, other):
        return self.__id_d == other.__id_d

    def __str__(self):
        return str(self.__id_d) + " " + str(self.__dist)

    @staticmethod
    def read_object(line):
        parts = line.split(",")
        return Orders(int(parts[0].strip()), int(parts[1].strip()))


class Drivers(object):

    def __init__(self, id_dr, name):
        self.__id_dr = id_dr
        self.__name = name

    def get_id_dr(self):
        return self.__id_dr

    def get_name(self):
        return self.__name

    def set_name(self, value):
        self.__name = value

    def __eq__(self, other):
        return self.__id_dr == other.__id_dr

    def __str__(self):
        return str(self.__id_dr) + " " + self.__name

    @staticmethod
    def read_object(line):
        parts = line.split(",")
        return Drivers(int(parts[0].strip()), parts[1].strip())