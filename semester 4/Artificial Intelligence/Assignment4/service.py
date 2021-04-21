from domain import *
import pickle,pygame,sys
from random import *
from pygame.locals import *

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

class Service():
    def __init__(self, map, drone, trace, sensors, alpha, beta, evaporationConst):
        self.map = map
        self.drone = drone
        self.trace = trace
        self.sensors = sensors
        self.alpha = alpha
        self.beta = beta
        self.evaporationConst = evaporationConst

    def randomMap(self, fill=0.2):
        for i in range(self.map.get_n()):
            for j in range(self.map.get_m()):
                if random() <= fill:
                    self.map.get_surface()[i][j] = 1

    def saveMap(self, numFile="test.map"):
        with open(numFile, 'wb') as f:
            pickle.dump(self.map, f)
            f.close()

    def loadMap(self, numfile):
        with open(numfile, "rb") as f:
            dummy = pickle.load(f)
            self.map.set_n(dummy.get_n())
            self.map.set_m(dummy.get_m())
            self.map.set_surface(dummy.get_surface())
            f.close()

    def __str__(self):
        string = ""
        for i in range(self.map.get_n()):
            for j in range(self.map.get_m()):
                string = string + str(int(self.map.get_surface()[i][j]))
            string = string + "\n"
        return string

    def getMaxSquaresForSensor(self, s): #s = [a, b]
        rol = [0, 1, 0, -1]
        col = [1, 0, -1, 0]
        max = 0
        for energy in range (1, 6):
            sum = 0
            for i in range(0, 4):
                node = [s[0] + rol[i], s[1] + col[i]]
                e = 0
                while 0 <= node[0] < self.map.get_n() and 0 <= node[1] < self.map.get_m() and self.map.get_surface()[node[0], node[1]] == 0 and e < energy:
                    sum += 1
                    node = [node[0] + rol[i], node[1] + col[i]]
                    e += 1
            if sum > max:
                max = sum
                relevantEnergy = energy
        return relevantEnergy #, max

    def searchAStar(self, mapM, initialX, initialY, finalX, finalY):
        toVisit = PriorityQueue()
        priority = abs(finalX-initialX) + abs(finalY-initialY)
        toVisit.add((initialX, initialY), priority)
        prev = {}
        cost = np.zeros((mapM.get_n(), mapM.get_m()))
        for i in range(mapM.get_n()):
            for j in range(mapM.get_m()):
                cost[i, j] = -1
        cost[initialX, initialY] = 0
        found = False
        while not toVisit.is_empty() and not found:
            node = toVisit.pop()
            if node[0] == finalX and node[1] == finalY:
                found = True
            else:
                row = [-1, 1, 0, 0]
                col = [0, 0, -1, 1]
                for i in range(0, 4):
                    current = (node[0] + row[i], node[1] + col[i])
                    if current[0] >= 0 and current[0] < mapM.get_n() and current[1] >= 0 and current[1] < mapM.get_m() and \
                            mapM.get_surface()[current[0], current[1]] == 0:
                        new_cost = cost[node[0], node[1]] + 1
                        if cost[current[0], current[1]] == -1 or new_cost < cost[current[0], current[1]]:
                            cost[current[0], current[1]] = new_cost
                            priority = new_cost + abs(finalX - current[0]) + abs(finalY - current[1])
                            toVisit.add((current[0], current[1]), priority)
                            prev[(current[0], current[1])] = (node[0], node[1])

        path = []
        path_index = (finalX, finalY)
        while path_index != (initialX, initialY):
            path.append(path_index)
            path_index = prev.get(path_index)
        path.append([path_index[0], path_index[1]])
        return len(path)

    def returnPosition(self, searchingSensor):
        for i in range(len(self.sensors)):
            if self.sensors[i] == searchingSensor:
                return i

    def nVisibility(self, currentSensor, nextSensor):
        distance = self.searchAStar(self.map, currentSensor[0], currentSensor[1], nextSensor[0], nextSensor[1])
        return 1/distance

    def piHeuristic(self, currentSensor, nextSensor):
        start = self.returnPosition(currentSensor)
        end = self.returnPosition(nextSensor)
        pheromone = self.trace[(start, end)]
        if pheromone == 0:
            return self.nVisibility(currentSensor, nextSensor) ** self.beta
        else:
            return (pheromone ** self.alpha) * (self.nVisibility(currentSensor, nextSensor) ** self.beta)

    def calculateMovingProbability(self, currentSensor, nextSensor, neighbours):
        divider = 0
        for n in neighbours:
            divider += self.piHeuristic(currentSensor, n)
        return self.piHeuristic(currentSensor, nextSensor) / divider

    def calculateEvaporation(self, ants):
        for t in self.trace.keys(): #t=(a, b) where a, b positions in self.sensors
            self.trace[t] = (1 - self.evaporationConst) * self.trace[t]

        for ant in ants:
            for i in range(len(ant.getPath()) - 1):
                start = self.returnPosition(ant.getPath()[i])
                end = self.returnPosition(ant.getPath()[i+1])
                self.trace[start, end] += (1 / len(ant.getPath()))

    def nextMove(self, currentSensor, visited):
        neighbours = [] #not visited sensors, ex: [[x, y], [a, b], ....]
        bestNeighbour = 0
        cost = 0
        cost = 0
        for s in self.sensors:
            if s not in visited:
                neighbours.append(s)
        for n in range(len(neighbours) - 1):
            if self.calculateMovingProbability(currentSensor, neighbours[n], neighbours) > self.calculateMovingProbability(currentSensor, neighbours[n + 1], neighbours):
                bestNeighbour = neighbours[n] #[x, y]
                cost = self.searchAStar(self.map, currentSensor[0], currentSensor[1], neighbours[n][0], neighbours[n][1])
        return bestNeighbour, cost

    def makeFirstChoice(self, currentSensor, visited):
        pass
        neighbours = []
        weightNeighbours = []
        for s in self.sensors:
            if s not in visited:
                neighbours.append(s)
        for n in range(len(neighbours)):
            weightNeighbours.append(self.calculateMovingProbability(currentSensor, neighbours[n], neighbours))
        weightNeighbours = tuple(weightNeighbours)
        firstNeighbour = choices(neighbours, weights=weightNeighbours, k=1)
        cost = self.searchAStar(self.map, currentSensor[0], currentSensor[1], firstNeighbour[0][0], firstNeighbour[0][1])
        return firstNeighbour[0], cost

    def runOneAnt(self, ant):
        visited = []
        visited.append([self.drone.get_x(), self.drone.get_y()])
        ant.getPath().append([self.drone.get_x(), self.drone.get_y()]) #ex: [[x, y], [a, b],..]
        firstMove, cost = self.makeFirstChoice(ant.getPath()[-1], visited)
        ant.fitness(cost)
        ant.getPath().append(firstMove)
        ant.substractEnergy(self.getMaxSquaresForSensor(firstMove))
        visited.append(firstMove)
        while ant.getEnergy() > 0 or len(visited) == len(self.sensors):
            nextMove, cost = self.nextMove(ant.getPath()[-1], visited)
            ant.fitness(cost)
            ant.getPath().append(nextMove)
            ant.substractEnergy(self.getMaxSquaresForSensor(nextMove))
            visited.append(nextMove)