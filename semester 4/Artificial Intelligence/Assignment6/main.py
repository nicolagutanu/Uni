import csv
from random import sample, uniform
import math
import matplotlib.pyplot as plt


class Point:
    def __init__(self, label, val1, val2):
        self.label = label
        self.val1 = val1
        self.val2 = val2
        self.cluster = None

    def getLabel(self):
        return self.label

    def getVal1(self):
        return self.val1

    def getVal2(self):
        return self.val2

    def setLabel(self, newLabel):
        self.label = newLabel

    def deleteFromCluster(self):
        self.cluster.points.remove(self)


class Cluster:
    def __init__(self, label):
        self.label = label
        self.mean_val1 = 0
        self.mean_val2 = 0
        self.points = []

    def getLabel(self):
        return self.label

    def getMeanVal1(self):
        return self.mean_val1

    def getMeanVal2(self):
        return self.mean_val2

    def getPoints(self):
        return self.points

    def setMeanVals(self, val1, val2):
        self.mean_val1 = val1
        self.mean_val2 = val2

    def updateMeans(self):
        oldMeanVal1 = self.mean_val1
        oldMeanVal2 = self.mean_val2

        v1 = 0
        v2 = 0
        for p in self.points:
            v1 += p.getVal1()
            v2 += p.getVal2()
        self.mean_val1 = v1 / len(self.points)
        self.mean_val2 = v2 / len(self.points)

        #change stopping cond
        if abs(self.mean_val1 - oldMeanVal1) < 0.001 and abs(self.mean_val2 - oldMeanVal2) < 0.001:
            return False
        return True

    def addPoint(self, point):
        self.points.append(point)
        if point.cluster:
            point.deleteFromCluster()
        point.cluster = self

    def updateLabel(self):
        labels = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
        for i in self.points:
            labels[i.label] += 1

        self.label = max(labels, key=labels.get)

    def makeNewCentroid(self):
        pass

    def computeStats(self, clusters):
        if len(self.points) == 0:
            truePositive = 1
            falsePositive = 1
            falseNegative = 1
            trueNegative = 1
        else:
            truePositive = 0
            falsePositive = 0
            falseNegative = 0
            trueNegative = 0

        for p in self.points:
            if p.getLabel() == self.label:
                truePositive += 1
            else:
                falsePositive += 1

        for c in clusters:
            if c.getLabel() != self.label:
                for p in c.getPoints():
                    if p.getLabel() == c.getLabel():
                        trueNegative += 1
                    else:
                        falseNegative += 1

        accuracy = (truePositive + trueNegative) / (truePositive + falsePositive + falseNegative + trueNegative)
        precision = truePositive / (truePositive + falsePositive)
        rappel = truePositive / (truePositive + falseNegative)
        score = (2 * rappel * precision) / (rappel + precision)
        print(self.label)
        print('Accuracy: ', accuracy)
        print('Precision: ', precision)
        print('Rappel: ', rappel)
        print('Score: ', score)


def readFromFile():
    points = []
    with open('dataset.csv') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                line_count += 1
            else:
                p = Point(row[0], float(row[1]), float(row[2]))
                points.append(p)
                line_count += 1
    return points


def closestCluster(clusters, point):
    bestCluster = clusters[1]
    for i in range(len(clusters)-1):
        c1 = clusters[i]
        if math.dist((c1.getMeanVal1(), c1.getMeanVal2()), (point.getVal1(), point.getVal2())) < math.dist((bestCluster.getMeanVal1(), bestCluster.getMeanVal2()), (point.getVal1(), point.getVal2())):
            bestCluster = clusters[i]
    return bestCluster


def main():
    points = readFromFile()

    val1 = []
    val2 = []
    for p in points:
        val1.append(p.getVal1())
        val2.append(p.getVal2())
    centroids = []
    c1 = uniform(min(val1), max(val1)), uniform(min(val2), max(val2))
    c2 = uniform(min(val1), max(val1)), uniform(min(val2), max(val2))
    c3 = uniform(min(val1), max(val1)), uniform(min(val2), max(val2))
    c4 = uniform(min(val1), max(val1)), uniform(min(val2), max(val2))
    centroids.append(c1)
    centroids.append(c2)
    centroids.append(c3)
    centroids.append(c4)

    clusters = []
    clusters.append(Cluster('A'))
    clusters.append(Cluster('B'))
    clusters.append(Cluster('C'))
    clusters.append(Cluster('D'))

    for i in range(len(centroids)):
        clusters[i].setMeanVals(centroids[i][0], centroids[i][1])

    ok = True
    while ok:
        for p in points:
            bestCluster = closestCluster(clusters, p)
            bestCluster.addPoint(p)
        for c in clusters:
            if len(c.getPoints()) == 0:
                newCentroid = uniform(min(val1), max(val1)), uniform(min(val2), max(val2))
                c.setMeanVals(newCentroid[0], newCentroid[1])
            elif not c.updateMeans():
                ok = False

    for c in clusters:
        c.updateLabel()
    for c in clusters:
        c.computeStats(clusters)


main()