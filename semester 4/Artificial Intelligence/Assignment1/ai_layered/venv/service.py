from domain import *
import pickle,pygame,sys
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
    def __init__(self, env, map, drone):
        self.env=env
        self.map=map
        self.drone=drone

    def randomMap(self, fill=0.2):
        for i in range(self.env.get_n()):
            for j in range(self.env.get_m()):
                if random() <= fill:
                    self.env.get_surface()[i][j] = 1

    def readUDMSensors(self, x, y):
        readings = [0, 0, 0, 0]
        # UP
        xf = x - 1
        while ((xf >= 0) and (self.env.get_surface()[xf][y] == 0)):
            xf = xf - 1
            readings[UP] = readings[UP] + 1
        # DOWN
        xf = x + 1
        while ((xf < self.env.get_n()) and (self.env.get_surface()[xf][y] == 0)):
            xf = xf + 1
            readings[DOWN] = readings[DOWN] + 1
        # LEFT
        yf = y + 1
        while ((yf < self.env.get_m()) and (self.env.get_surface()[x][yf] == 0)):
            yf = yf + 1
            readings[LEFT] = readings[LEFT] + 1
        # RIGHT
        yf = y - 1
        while ((yf >= 0) and (self.env.get_surface()[x][yf] == 0)):
            yf = yf - 1
            readings[RIGHT] = readings[RIGHT] + 1

        return readings

    def saveEnvironment(self, numFile):
        with open(numFile, 'wb') as f:
            pickle.dump(self.env, f)
            f.close()

    def loadEnvironment(self, numfile):
        with open(numfile, "rb") as f:
            dummy = pickle.load(f)
            self.env.set_n(20)
            self.env.set_m(20)
            self.env.set_surface(dummy.get_surface())
            f.close()

    def image_env(self, colour=BLUE, background=WHITE):
        imagine = pygame.Surface((420, 420))
        brick = pygame.Surface((20, 20))
        brick.fill(BLUE)
        imagine.fill(WHITE)
        for i in range(self.env.get_n()):
            for j in range(self.env.get_m()):
                if (self.env.get_surface()[i][j] == 1):
                    imagine.blit(brick, (j * 20, i * 20))

        return imagine

    def markDetectedWalls(self, x, y):
        #   To DO
        # mark on this map the wals that you detect
        walls = self.readUDMSensors(x, y)
        i = x - 1
        if walls[UP] > 0:
            while ((i >= 0) and (i >= x - walls[UP])):
                self.map.get_surface()[i][y] = 0
                i = i - 1
        if (i >= 0):
            self.map.get_surface()[i][y] = 1

        i = x + 1
        if walls[DOWN] > 0:
            while ((i < self.map.get_n()) and (i <= x + walls[DOWN])):
                self.map.get_surface()[i][y] = 0
                i = i + 1
        if (i < self.map.get_n()):
            self.map.get_surface()[i][y] = 1

        j = y + 1
        if walls[LEFT] > 0:
            while ((j < self.map.get_m()) and (j <= y + walls[LEFT])):
                self.map.get_surface()[x][j] = 0
                j = j + 1
        if (j < self.map.get_m()):
            self.map.get_surface()[x][j] = 1

        j = y - 1
        if walls[RIGHT] > 0:
            while ((j >= 0) and (j >= y - walls[RIGHT])):
                self.map.get_surface()[x][j] = 0
                j = j - 1
        if (j >= 0):
            self.map.get_surface()[x][j] = 1

        return None

    def image_map(self, x, y):
        imagine = pygame.Surface((420, 420))
        brick = pygame.Surface((20, 20))
        empty = pygame.Surface((20, 20))
        empty.fill(WHITE)
        brick.fill(BLACK)
        imagine.fill(GRAYBLUE)

        for i in range(self.map.get_n()):
            for j in range(self.map.get_m()):
                if (self.map.get_surface()[i][j] == 1):
                    imagine.blit(brick, (j * 20, i * 20))
                elif (self.map.get_surface()[i][j] == 0):
                    imagine.blit(empty, (j * 20, i * 20))

        drona = pygame.image.load("drona.png")
        imagine.blit(drona, (y * 20, x * 20))
        return imagine

    def move(self, detectedMap):
        pressed_keys = pygame.key.get_pressed()
        if self.drone.get_x() > 0:
            if pressed_keys[K_UP] and self.map.get_surface()[self.drone.get_x() - 1][self.drone.get_y()] == 0:
                self.drone.set_x(self.drone.get_x()-1)

        if self.drone.get_x() < 19:
            if pressed_keys[K_DOWN] and self.map.get_surface()[self.drone.get_x() + 1][self.drone.get_y()] == 0:
                self.drone.set_x(self.drone.get_x()+1)

        if self.drone.get_y() > 0:
            if pressed_keys[K_LEFT] and self.map.get_surface()[self.drone.get_x()][self.drone.get_y() - 1] == 0:
                self.drone.set_y(self.drone.get_y()-1)

        if self.drone.get_y() < 19:
            if pressed_keys[K_RIGHT] and self.map.get_surface()[self.drone.get_x()][self.drone.get_y() + 1] == 0:
                self.drone.set_y(self.drone.get_y()+1)

    def moveDFS(self, detectedMap):
        if self.drone.get_x() > 0 and self.map.get_surface()[self.drone.get_x() - 1][self.drone.get_y()] == 0 and self.drone.get_visited()[self.drone.get_x() - 1][self.drone.get_y()] == 0:
            self.drone.get_stack().append((self.drone.get_x(), self.drone.get_y()))
            self.drone.set_x(self.drone.get_x() - 1)
            self.drone.get_visited()[self.drone.get_x()][self.drone.get_y()] = 1
            # self.x = self.x + 1

        elif self.drone.get_x() < 19 and self.map.get_surface()[self.drone.get_x() + 1][self.drone.get_y()] == 0 and self.drone.get_visited()[self.drone.get_x() + 1][self.drone.get_y()] == 0:
            self.drone.get_stack().append((self.drone.get_x(), self.drone.get_y()))
            self.drone.set_x(self.drone.get_x() + 1)
            self.drone.get_visited()[self.drone.get_x()][self.drone.get_y()] = 1
            # self.x = self.x - 1

        elif self.drone.get_y() > 0 and self.map.get_surface()[self.drone.get_x()][self.drone.get_y() - 1] == 0 and self.drone.get_visited()[self.drone.get_x()][self.drone.get_y() - 1] == 0:
            self.drone.get_stack().append((self.drone.get_x(), self.drone.get_y()))
            self.drone.set_y(self.drone.get_y() - 1)
            self.drone.get_visited()[self.drone.get_x()][self.drone.get_y()] = 1
            # self.y = self.y + 1

        elif self.drone.get_y() < 19 and self.map.get_surface()[self.drone.get_x()][self.drone.get_y() + 1] == 0 and self.drone.get_visited()[self.drone.get_x()][self.drone.get_y() + 1] == 0:
            self.drone.get_stack().append((self.drone.get_x(), self.drone.get_y()))
            self.drone.set_y(self.drone.get_y() + 1)
            self.drone.get_visited()[self.drone.get_x()][self.drone.get_y()] = 1
            # self.y = self.y - 1

        elif len(self.drone.get_stack()) != 0:
            (x, y) = self.drone.get_stack()[-1]
            self.drone.set_x(x)
            self.drone.set_y(y)
            self.drone.get_stack().pop()