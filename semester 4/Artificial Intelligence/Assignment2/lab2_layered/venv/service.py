from domain import *
import pickle,pygame,sys
from random import random, randint
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
    def __init__(self, map, drone):
        self.map=map
        self.drone=drone

    def randomMap(self, fill=0.2):
        for i in range(self.map.get_n()):
            for j in range(self.map.get_m()):
                if random() <= fill:
                    self.map.get_surface()[i][j] = 1

    def __str__(self):
        string = ""
        for i in range(self.map.get_n()):
            for j in range(self.map.get_m()):
                string = string + str(int(self.map.get_surface()[i][j]))
            string = string + "\n"
        return string

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

    def image(self, colour=BLUE, background=WHITE):
        imagine = pygame.Surface((400, 400))
        brick = pygame.Surface((20, 20))
        brick.fill(BLUE)
        imagine.fill(WHITE)
        for i in range(self.map.get_n()):
            for j in range(self.map.get_m()):
                if (self.map.get_surface()[i][j] == 1):
                    imagine.blit(brick, (j * 20, i * 20))

        return imagine

    def move(self, detectedMap):
        pressed_keys = pygame.key.get_pressed()
        if self.drone.get_x() > 0:
            if pressed_keys[K_UP] and self.map.get_surface()[self.drone.get_x() - 1][self.drone.get_y()] == 0:
                self.drone.set_x(self.drone.get_x() - 1)

        if self.drone.get_x() < 19:
            if pressed_keys[K_DOWN] and self.map.get_surface()[self.drone.get_x() + 1][self.drone.get_y()] == 0:
                self.drone.set_x(self.drone.get_x() + 1)

        if self.drone.get_y() > 0:
            if pressed_keys[K_LEFT] and self.map.get_surface()[self.drone.get_x()][self.drone.get_y() - 1] == 0:
                self.drone.set_y(self.drone.get_y() - 1)

        if self.drone.get_y() < 19:
            if pressed_keys[K_RIGHT] and self.map.get_surface()[self.drone.get_x()][self.drone.get_y() + 1] == 0:
                self.drone.set_y(self.drone.get_y() + 1)

    def mapWithDrone(self, mapImage):
        drona = pygame.image.load("drona.png")
        mapImage.blit(drona, (self.drone.get_y() * 20, self.drone.get_x() * 20))

        return mapImage

    def searchAStar(self, mapM, droneD, initialX, initialY, finalX, finalY):
        # TO DO
        # implement the search function and put it in controller
        # returns a list of moves as a list of pairs [x,y]
        toVisit = PriorityQueue()
        priority=abs(finalX-initialX)+abs(finalY-initialY)
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
            path.append([path_index[0], path_index[1]])
            path_index = prev[path_index]
        path.append([path_index[0], path_index[1]])
        path.reverse()
        return path

    def searchGreedy(self, mapM, droneD, initialX, initialY, finalX, finalY):
        # TO DO
        # implement the search function and put it in controller
        # returns a list of moves as a list of pairs [x,y]
        toVisit = PriorityQueue()
        toVisit.add((initialX, initialY), 0)
        prev = {}
        visited=[]
        found = False
        while not toVisit.is_empty() and not found:
            node = toVisit.pop()
            visited.append((node[0],node[1]))
            if node[0] == finalX and node[1] == finalY:
                found = True
            else:
                row = [-1, 1, 0, 0]
                col = [0, 0, -1, 1]
                for i in range(0, 4):
                    current = (node[0] + row[i], node[1] + col[i])
                    if current[0] >= 0 and current[0] < mapM.get_n() and current[1] >= 0 and current[1] < mapM.get_m() and \
                            mapM.get_surface()[current[0], current[1]] == 0:
                        if current not in visited:
                            priority = abs(finalX - current[0]) + abs(finalY - current[1])
                            toVisit.add((current[0], current[1]), priority)
                            prev[(current[0], current[1])] = (node[0], node[1])

        path = []
        path_index = (finalX, finalY)
        while path_index != (initialX, initialY):
            path.append([path_index[0], path_index[1]])
            path_index = prev[path_index]
        path.append([path_index[0], path_index[1]])
        path.reverse()
        return path

    def searchBFS(self, mapM, droneD, initialX, initialY, finalX, finalY):
        toVisit=[]
        toVisit.append((initialX, initialY))
        prev={}
        found=False
        while len(toVisit)!=0 and not found:
            node=toVisit.pop(0)
            if node[0]==finalX and node[1]==finalY:
                found=True
            else:
                row = [-1, 1, 0, 0]
                col = [0, 0, -1, 1]
                for i in range(0, 4):
                    current = (node[0] + row[i], node[1] + col[i])
                    if current[0] >= 0 and current[0] < mapM.get_n() and current[1] >= 0 and current[
                        1] < mapM.get_m() and \
                            mapM.get_surface()[current[0], current[1]] == 0:
                        if current not in prev:
                            toVisit.append(current)
                            prev[current]=node
        path = []
        path_index = (finalX, finalY)
        while path_index != (initialX, initialY):
            path.append([path_index[0], path_index[1]])
            path_index = prev[path_index]
        path.append([path_index[0], path_index[1]])
        path.reverse()
        return path

    def displayWithPath(self, image, path):
        mark = pygame.Surface((20, 20))
        mark.fill(GREEN)
        for move in path:
            image.blit(mark, (move[1] * 20, move[0] * 20))

        return image