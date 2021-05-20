import torch as t
import numpy as np


def getRandomPoints(min=-10, max=10, total=1000):
    # x1 and x2
    points = (max - min) * t.rand((total, 2)) + min
    finalData = []
    for p in points:
        # compute f
        f = t.sin(p[0] + p[1] / np.pi)
        finalData.append([p[0], p[1], f])
    finalData = t.tensor(finalData)
    # save data
    t.save(finalData, "mydataset.dat")


getRandomPoints()