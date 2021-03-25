import numpy as np

class Environment():
    def __init__(self):
        self.__n = 20
        self.__m = 20
        self.__surface = np.zeros((self.__n, self.__m))

    def get_n(self):
        return self.__n

    def get_m(self):
        return self.__m

    def get_surface(self):
        return self.__surface

    def set_n(self, value_n):
        self.__n=value_n

    def set_m(self, value_m):
        self.__m=value_m

    def set_surface(self, value_surface):
        self.__surface=value_surface

    def __str__(self):
        string=""
        for i in range(self.__n):
            for j in range(self.__m):
                string = string + str(int(self.__surface[i][j]))
            string = string + "\n"
        return string


class DMap():
    def __init__(self):
        self.__n = 20
        self.__m = 20
        self.surface = np.zeros((self.__n, self.__m))
        for i in range(self.__n):
            for j in range(self.__m):
                self.surface[i][j] = -1

    def get_n(self):
        return self.__n

    def get_m(self):
        return self.__m

    def get_surface(self):
        return self.surface

    def set_n(self, value_n):
        self.__n=value_n

    def set_m(self, value_m):
        self.__m=value_m

    def set_surface(self, value_surface):
        self.__surface=value_surface


class Drone():
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.visited = np.zeros((20, 20))
        self.stack=[]

    def get_x(self):
        return self.x

    def get_y(self):
        return self.y

    def get_visited(self):
        return self.visited

    def get_stack(self):
        return self.stack

    def set_x(self, value_x):
        self.x=value_x

    def set_y(self, value_y):
        self.y=value_y

    def set_visited(self, value_visited):
        self.visited=value_visited

    def set_stack(self, value_stack):
        self.stack=value_stack