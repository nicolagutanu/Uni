from ui import *
import random

if __name__ == '__main__':
    e = Environment()
    dm = DMap()
    x = random.randint(0, 19)
    y = random.randint(0, 19)
    drone = Drone(x, y)
    service = Service(e, dm, drone)
    ui = Ui(service)
    ui.run()
