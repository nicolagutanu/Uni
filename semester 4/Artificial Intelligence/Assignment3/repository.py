# -*- coding: utf-8 -*-

import pickle
from domain import *
import random


class repository():

    def __init__(self):
        self.populations = []
        
    def createPopulation(self, populationSize, individualSize):
        # args = [populationSize, individualSize] -- you can add more args
        return Population(populationSize, individualSize)

    def getLastPopulation(self):
        return self.populations[-1]

    def addPopulation(self, population):
        self.populations.append(population)

    def getAverageAndStd(self):
        fitness = []
        deviation = []
        for p in self.populations:
            f, d = p.averageFitnessAndDeviation()
            fitness.append(f)
            deviation.append(d)
        return np.average(fitness), np.std(deviation)

    def createPath(self, ind, x, y):
        return ind.createPath(x, y)

    def saveMap(self, map, numFile="test.map"):
        with open(numFile, 'wb') as f:
            pickle.dump(map, f)
            f.close()

    def loadMap(self, map, numfile):
        with open(numfile, "rb") as f:
            dummy = pickle.load(f)
            map.set_n(dummy.get_n())
            map.set_m(dummy.get_m())
            map.set_surface(dummy.get_surface())
            f.close()
        
    # TO DO : add the other components for the repository: 
    #    load and save from file, etc
            