from ui import *
import random

if __name__ == '__main__':
    m = Map()
    x = random.randint(0, 19)
    y = random.randint(0, 19)
    drone = Drone(x, y)
    service = Service(m, drone)
    ui = Ui(service)
    ui.run()
