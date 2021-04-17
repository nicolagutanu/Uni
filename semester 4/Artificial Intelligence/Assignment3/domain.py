# -*- coding: utf-8 -*-

from random import *
from utils import *
import numpy as np

# the glass gene can be replaced with int or float, or other types
# depending on your problem's representation

class gene:

    def __init__(self):
        self.gene = randint(1, 4)
        #1-(0, 1) 2-(1, 0) 3-(0, -1) 4-(-1, 0)

    def getGene(self):
        return self.gene

class Individual:

    def __init__(self, size = 0):
        self.size = size
        self.x = [gene() for i in range(self.size)]
        self.f = None

    def createPath(self, x, y):
        path = []
        path.append([x, y])
        for i in self.x:
            x, y = self.compute_current(x, y, i)
            path.append([x, y])
        return path

    def compute_current(self, x, y, index):
        if index == 1:
            return (x, y+1)
        elif index == 2:
            return (x+1, y)
        elif index == 3:
            return (x, y-1)
        else:
            return (x-1, y)
        
    def fitness(self, map, initialX, initialY):
        # compute the fitness for the individual
        # and save it in self.__f
        self.f = 0
        visited = []
        visited.append((initialX, initialY))
        for i in range(self.size):
            current = self.compute_current(initialX, initialY, self.x[i].getGene())
            if current not in visited:
                visited.append(current)
                if current[0] < 0 or current[0] >= map.get_n() or current[1] < 0 or current[1] >= map.get_m():
                    break
                if map.get_surface()[current[0]][current[1]] == 1:
                    break
                col = [1, 0, -1, 0]
                row = [0, 1, 0, -1]
                self.f += 1
                for j in range(0, 4):
                    node = (current[0] + row[j], current[1] + col[j])
                    while 0 <= node[0] < map.get_n() and 0 <= node[1] < map.get_m() and map.get_surface()[node[0]][node[1]] == 0:
                        if node not in visited:
                            visited.append(node)
                            self.f += 1
                        node = (node[0] + row[j], node[1] + col[j])
    
    def mutate(self, mutateProbability = 0.04):
        if random() < mutateProbability:
            index1 = randint(0, self.size - 1)
            index2 = randint(0, self.size - 1)
            node = self.x[index1]
            self.x[index1] = self.x[index2]
            self.x[index2] = node

    def crossover(self, otherParent, crossoverProbability = 0.8):
        #self->offspring2
        #otherParent->offspring1
        offspring1, offspring2 = Individual(self.size), Individual(self.size)
        if random() < crossoverProbability:
            index = randint(0, self.size - 1)
            offspring1.x = self.x[:index] + otherParent.x[index:]
            offspring2.x = otherParent.x[:index] + self.x[index:]
            # perform the crossover between the self and the otherParent
        return offspring1, offspring2

    def getFitness(self):
        return self.f


class Population:

    def __init__(self, populationSize = 0, individualSize = 0):
        self.populationSize = populationSize
        self.v = [Individual(individualSize) for x in range(populationSize)]
        
    def evaluate(self, map, x, y):
        # evaluates the population
        for v in self.v:
            v.fitness(map, x, y)

    def getFitnesses(self):
        fit = [x.getFitness() for x in self.v]
        return fit

    def fitnessSum(self):
        sum = 0
        for x in self.v:
            sum += x.getFitness()
        return sum
            
    def selection(self, k):
        #selection of one parent by wheel selection
        # and returns that selection
        #descending sorting: bigest first
        selection = []
        distance = round(1/k, 2)
        d = distance
        prob = 0
        i = 0
        for x in self.v:
            sc = round(x.getFitness()/self.populationSize, 2)
            prob += sc
            if distance < prob:
                selection.append(x)
                i += 1
            while distance < prob:
                distance += d
            if i == k:
                break
        return selection
        '''
        selection = []
        self.sortPopulationByFitness()
        for i in range(0, k):
            selection.append(self.v[i])
        return selection
        '''

    def sortPopulationByFitness(self):
        for i in range(len(self.v)):
            for j in range(len(self.v)-i-1):
                if self.v[j].f < self.v[j+1].f:
                    self.v[j], self.v[j+1] = self.v[j+1], self.v[j]

    def averageFitnessAndDeviation(self):
        fitness = []
        for i in self.v:
            fitness.append(i.getFitness())
        avg = np.average(fitness)
        deviation = np.std(fitness)
        return avg, deviation

    def bestFitness(self):
        best = 0
        for i in self.v:
            if i.getFitness() > best:
                best = i.getFitness()
        return best

    def getBestIndividual(self):
        self.sortPopulationByFitness()
        return self.v[0], self.v[0].getFitness()

    def deleteOne(self, position):
        del self.v[position]
        i = 1 + 1

    def addOne(self, ind):
        self.v.append(ind)

    
class Map:

    def __init__(self, n = 20, m = 20):
        self.n = n
        self.m = m
        self.surface = np.zeros((self.n, self.m))
    
    def randomMap(self, fill = 0.2):
        for i in range(self.n):
            for j in range(self.m):
                if random() <= fill :
                    self.surface[i][j] = 1
                
    def __str__(self):
        string=""
        for i in range(self.n):
            for j in range(self.m):
                string = string + str(int(self.surface[i][j]))
            string = string + "\n"
        return string

    def get_n(self):
        return self.n

    def get_m(self):
        return self.m

    def get_surface(self):
        return self.surface

    def set_n(self, value_n):
        self.n=value_n

    def set_m(self, value_m):
        self.m=value_m

    def set_surface(self, value_surface):
        self.surface=value_surface
                
    