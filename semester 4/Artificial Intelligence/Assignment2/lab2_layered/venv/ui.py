from domain import *
from service import *

import pygame,sys
from pygame.locals import *
from random import random, randint
import numpy as np
import time
import keyboard
import msvcrt

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
    def __init__(self, service):
        self.service=service

    def run(self):
        self.service.randomMap()

        # initialize the pygame module
        pygame.init()
        # load and set the logo
        logo = pygame.image.load("logo32x32.png")
        pygame.display.set_icon(logo)
        pygame.display.set_caption("drone exploration")

        # create a surface on screen that has the size of 800 x 480
        screen = pygame.display.set_mode((1200, 400))
        screen.fill(WHITE)

        #final coordinates
        finalX = randint(0, 19)
        finalY = randint(0, 19)
        while self.service.map.get_surface()[finalX, finalY] != 0:
            finalX = randint(0, 19)
            finalY = randint(0, 19)

        # define a variable to control the main loop
        running = True

        # main loop
        while running:
            # event handling, gets all event from the event queue
            for event in pygame.event.get():
                # only do something if the event is of type QUIT
                if event.type == pygame.QUIT:
                    # change the value to False, to exit the main loop
                    running = False
                if event.type == KEYDOWN:
                    running=False

            screen.blit(self.service.mapWithDrone(self.service.image()), (0, 0))
            pygame.display.flip()

        timeGreedy=time.perf_counter()
        pathG = self.service.searchGreedy(self.service.map, self.service.drone,
                                         self.service.drone.get_x(),
                                         self.service.drone.get_y(), finalX, finalY)
        print("Greedy: "+str(time.perf_counter()-timeGreedy))
        screen.blit(self.service.displayWithPath(self.service.image(), pathG), (0, 0))

        timeAStar = time.perf_counter()
        pathA = self.service.searchAStar(self.service.map, self.service.drone,
                                         self.service.drone.get_x(),
                                         self.service.drone.get_y(), finalX, finalY)
        print("A*: " + str(time.perf_counter() - timeAStar))
        screen.blit(self.service.displayWithPath(self.service.image(), pathA), (400, 0))

        timeBFS = time.perf_counter()
        pathB=self.service.searchBFS(self.service.map, self.service.drone,
                                         self.service.drone.get_x(),
                                         self.service.drone.get_y(), finalX, finalY)
        print("BFS: " + str(time.perf_counter() - timeBFS))
        screen.blit(self.service.displayWithPath(self.service.image(), pathB), (800, 0))

        pygame.display.flip()
        time.sleep(20)
        pygame.quit()