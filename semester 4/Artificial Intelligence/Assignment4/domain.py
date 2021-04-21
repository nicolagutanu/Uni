import numpy as np
import random

class PriorityQueue:

    def __init__(self):
        self.__values = {}

    def is_empty(self):
        return len(self.__values) == 0

    def pop(self):
        top_priority = None
        top_obj = None
        for obj in self.__values:
            obj_priority = self.__values[obj]
            if top_priority is None or top_priority > obj_priority:
                top_priority = obj_priority
                top_obj = obj
        del self.__values[top_obj]
        return top_obj

    def add(self, obj, priority):
        self.__values[obj] = priority

class Map():
    def __init__(self, n=20, m=20):
        self.__n = n
        self.__m = m
        self.surface = np.zeros((self.__n, self.__m))

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

    def get_x(self):
        return self.x

    def get_y(self):
        return self.y

    def set_x(self, value_x):
        self.x=value_x

    def set_y(self, value_y):
        self.y=value_y


class Ant():
    def __init__(self, energy):
        self.path = []
        self.energy = energy
        self.cost = 0

    def fitness(self, addedCost):
        self.cost += addedCost

    def getEnergy(self):
        return self.energy

    def getCost(self):
        return self.cost

    def getPath(self):
        return self.path

    def substractEnergy(self, energy):
        self.energy -= energy
