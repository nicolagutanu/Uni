from domain import *
from service import *

import pygame,sys
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
    def __init__(self, service):
        self.service=service

    def run(self):
        self.service.loadEnvironment("test2.map")

        m = DMap()

        # initialize the pygame module
        pygame.init()
        # load and set the logo
        logo = pygame.image.load("logo32x32.png")
        pygame.display.set_icon(logo)
        pygame.display.set_caption("drone exploration")

        # create a surface on screen that has the size of 800 x 480
        screen = pygame.display.set_mode((800, 400))
        screen.fill(WHITE)
        screen.blit(self.service.image_env(), (0, 0))

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
                #if event.type == KEYDOWN:
                # use this function instead of move
                # d.moveDSF(m)
                # pass
                    #d.move(m)
                    #self.service.move(m)
            self.service.moveDFS(m)
            time.sleep(0.3)
            x=self.service.drone.get_x()
            y=self.service.drone.get_y()
            self.service.markDetectedWalls(x, y)
            screen.blit(self.service.image_map(x, y), (400, 0))
            pygame.display.flip()

        pygame.quit()