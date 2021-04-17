# -*- coding: utf-8 -*-


# imports
from gui import *
from controller import *
from repository import *
from domain import *


# create a menu
#   1. map options:
#         a. create random map
#         b. load a map
#         c. save a map
#         d visualise map
#   2. EA options:
#         a. parameters setup: mutateProbability(default 0.04), crossoverPB(0.8),
#                              populationSize(0), individualSize(0), iterationNr
#
#         b. run the solver
#         c. visualise the statistics
#         d. view the drone moving on a path
#              function gui.movingDrone(currentMap, path, speed, markseen)
#              ATENTION! the function doesn't check if the path passes trough walls
class ui():
    def __init__(self, ):
        self.map = Map()
        self.loadFile = ""
        self.mutatePb = 0.04
        self.crossoverPb = 0.8
        self.populationSize = 0
        self.individualSize = 0
        self.iterationNr = 0
        self.stats = [[], []]
        self.runningTime = 0
        repo = repository()
        self.controller = controller(repo, self.map, self.crossoverPb, self.mutatePb)


    def mapMenu(self):
        print("1. create random map")
        print("2. load map")
        print("3. save map")
        print("4. parameter setup")
        print("5. run solver")
        print("6. run one iteration")
        print("7. visualise statistics")
        print("8. view the drone moving on a path")
        print("9. exit")

    def createRandomMap(self):
        self.map.randomMap()

    def loadMap(self):
        self.controller.repository.loadMap(self.map, self.loadFile)

    def saveMap(self):
        self.controller.repository.saveMap(self.map)

    def parameterSetup(self):
        self.loadFile = input("load file: ")
        self.mutatePb = float(input("mutate probability: "))
        self.crossoverPb = float(input("crossover probability: "))
        self.populationSize = int(input("population size: "))
        self.individualSize = int(input("battery: "))
        self.iterationNr = int(input("number of iterations: "))
        repo = repository()
        self.controller = controller(repo, self.map, self.crossoverPb, self.mutatePb)

    def runSolver(self):
        self.stats[0], self.stats[1], self.runningTime = self.controller.solver(self.populationSize, self.individualSize, self.iterationNr)

    def runOneIteration(self):
        s = [[], []]
        s = self.controller.run(self.populationSize, self.individualSize, self.iterationNr)
        plt.plot(s[0])
        plt.ylabel('fitness')
        plt.show()
        time.sleep(0.5)

    def visualiseStatistics(self):
        plt.plot(self.stats[0], self.stats[1])
        plt.xlabel("fitness")
        plt.ylabel("deviation")
        plt.show()
        time.sleep(0.5)
        for i in range(len(self.stats[0])):
            print("fitness: ", self.stats[0][i], ", standard deviation: ", self.stats[1][i])
        print("Total execution time: ", self.runningTime)

    def viewDroneOnMap(self):
        path = self.controller.finalPath()
        movingDrone(self.map, path)

    def run(self):
        while (True):
            self.mapMenu()
            val = int(input(">>> "))
            if val == 1:
                self.createRandomMap()
            elif val == 2:
                self.loadMap()
            elif val == 3:
                self.saveMap()
            elif val == 4:
                self.parameterSetup()
            elif val == 5:
                self.runSolver()
            elif val == 6:
                self.runOneIteration()
            elif val == 7:
                self.visualiseStatistics()
            elif val == 8:
                self.viewDroneOnMap()
            elif val == 9:
                exit()
            else:
                print("unrecognised command")