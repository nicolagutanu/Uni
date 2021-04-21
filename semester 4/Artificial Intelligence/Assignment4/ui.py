from domain import *
from service import *

import pygame, sys
from pygame.locals import *
from random import random, randint
import numpy as np
import time

#Creating some colors
BLUE  = (0, 0, 255)
GRAYBLUE = (50,120,120)
RED   = (255, 0, 0)
GREEN = (0, 255, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

#define directions
UP = 0
DOWN = 2
LEFT = 1
RIGHT = 3

class Ui():
    def __init__(self):
        self.energy = 0
        self.alpha = 0
        self.beta = 0
        self.evaporationConst = 0
        self.nrOfAnts = 0
        self.sensors = [] #[[x, y], [a, b], ..]
        self.drone = 0
        self.map = Map()
        self.readFromFile()
        self.trace = {}  # {(a, b):x, ...} where a and b are positions of sensors in self.sensors and x is the trace
        self.makeGraph()
        self.service = Service(self.map, self.drone, self.trace, self.sensors, self.alpha, self.beta, self.evaporationConst)

    def setUpDrone(self):
        x = int(input("x coordinate of drone >>>"))
        y = int(input("y coordonate of drone >>>"))
        self.drone = Drone(x, y)

    def setUpMap(self):
        file = input("map file >>>")
        self.service.loadMap(file)

    def setUpInput(self):
        self.energy = int(input("energy of drone >>>"))
        self.alpha = float(input("alpha >>>"))
        self.beta = float(input("beta >>>"))
        self.evaporationConst = float(input("evaporation constant >>>"))
        self.nrOfAnts = int(input("nr of ants >>>"))
        sens = input("sensors >>>")
        while sens != "":
            sens = sens.split(" ")
            self.sensors.append([int(sens[0]), int(sens[1])])
            sens = input("sensors >>>")

    def makeGraph(self):
        for i in range(len(self.sensors)):
            for j in range(len(self.sensors)):
                self.trace[(i, j)] = 1

    def readFromFile(self):
        f = open("idk.txt", "r")
        self.energy = int(f.readline())
        self.alpha = float(f.readline())
        self.beta = float(f.readline())
        self.evaporationConst = float(f.readline())
        self.nrOfAnts = int(f.readline())
        nrOfSensors = int(f.readline())
        while nrOfSensors > 0:
            sensor = f.readline()
            sensor = sensor.split(" ")
            self.sensors.append([int(sensor[0]), int(sensor[1])])
            nrOfSensors -= 1
        xDrone = int(f.readline())
        yDrone = int(f.readline())
        self.sensors.append([xDrone, yDrone])
        self.drone = Drone(xDrone, yDrone)

        for i in range(0, 20):
            line = f.readline()
            line = line.split(",")
            for j in range(0, 20):
                self.map.get_surface()[i][j] = line[j]

    def run(self):
        ants = []
        min = sys.maxsize
        bestAnt = 0
        while self.nrOfAnts > 0:
            for i in range(0, 3):
                ant = Ant(self.energy)
                ants.append(ant)
                self.service.runOneAnt(ant)
                if ant.getCost() < min:
                    min = ant.getCost()
                    bestAnt = ant
                self.nrOfAnts -= 1
            self.service.calculateEvaporation(ants)
            print(str(self.nrOfAnts) + ": working on it")
        print(bestAnt.getPath())