from repository import *
import matplotlib.pyplot as plt
import time
import random


class controller():
    def __init__(self, repo, map, crossPb, mutatePb):
        # args - list of parameters needed in order to create the controller
        self.repository = repo
        self.statistics = [[], []]
        self.map = map
        self.x = randint(0, self.map.get_n()-1)
        self.y = randint(0, self.map.get_m()-1)
        while self.map.get_surface()[self.x, self.y] != 0:
            self.x = randint(0, self.map.get_n()-1)
            self.y = randint(0, self.map.get_m()-1)
        self.crossPb = crossPb
        self.mutatePb = mutatePb
        self.bestPath = [[], []]

    def finalPath(self):
        for i in range(len(self.bestPath)):
            for j in range(len(self.bestPath)-i-1):
                if self.bestPath[1][j] < self.bestPath[1][j+1]:
                    self.bestPath[0][j], self.bestPath[0][j+1] = self.bestPath[0][j+1], self.bestPath[0][j]
                    self.bestPath[1][j], self.bestPath[1][j + 1] = self.bestPath[1][j + 1], self.bestPath[1][j]
        return self.bestPath[0][0]

    def iteration(self):
        # args - list of parameters needed to run one iteration
        # a iteration:
        # selection of the parents - wheel selection
        # create offsprings by crossover of the parents
        # apply some mutations
        # selection of the survivors - replacing the parents with worst fitness function
        '''
        population = self.repository.getLastPopulation()
        population.evaluate(self.map, self.x, self.y)
        parents = population.selection(2)
        parent1 = parents[0]
        parent2 = parents[1]
        child1, child2 = parent1.crossover(parent2, self.crossPb)
        child1.mutate(self.mutatePb)
        child2.mutate(self.mutatePb)
        selection = self.repository.getLastPopulation()
        selection.sortPopulationByFitness()
        selection.deleteOne(-1)
        selection.deleteOne(-1)
        selection.addOne(child1)
        selection.addOne(child2)
        selection.evaluate(self.map, self.x, self.y)
        self.repository.addPopulation(selection)
        '''
        population = self.repository.getLastPopulation()
        population.evaluate(self.map, self.x, self.y)
        parents = population.selection(population.populationSize // 2)
        nrParents = len(parents) // 2
        children = []
        usedParents = []
        for i in range(0, nrParents):
            parent1 = parents[randint(0, len(parents)-1)]
            parent2 = parents[randint(0, len(parents) - 1)]
            if (parent1, parent2) not in usedParents:
                child1, child2 = parent1.crossover(parent2, self.crossPb)
                child1.mutate(self.mutatePb)
                child2.mutate(self.mutatePb)
                children.append(child1)
                children.append(child2)
                usedParents.append((parent1, parent2))
        finalSelection = self.repository.getLastPopulation()
        finalSelection.sortPopulationByFitness()
        for i in range(0, len(children)):
            finalSelection.deleteOne(-1)
        for i in range(0, len(children)):
            finalSelection.addOne(children[i])
        finalSelection.evaluate(self.map, self.x, self.y)
        self.repository.addPopulation(finalSelection)

    def run(self, populationSize, individualSize, iterationNr):
        # args - list of parameters needed in order to run the algorithm
        # until stop condition
        #    perform an iteration
        #    save the information need it for the statistics
        # return the results and the info for statistics
        stats = [[], []]
        population = self.repository.createPopulation(populationSize, individualSize)
        self.repository.addPopulation(population)
        for i in range(iterationNr):
            population = self.repository.getLastPopulation()
            population.evaluate(self.map, self.x, self.y)
            b = population.bestFitness()
            stats[0].append(b)
            stats[1].append(i)
            self.iteration()
        return stats
    
    def solver(self, populationSize, individualSize, iterationNr):
        # args - list of parameters needed in order to run the solver
        # create the population,
        # run the algorithm
        # return the results and the statistics
        statsOnePop = [[], []]
        runningTime = 0
        for i in range(30):
            while self.map.get_surface()[self.x, self.y] != 0:
                self.x = randint(0, self.map.get_n()-1)
                self.y = randint(0, self.map.get_m()-1)
            random.seed()
            timeForOneRun = time.perf_counter()
            statsOnePop = self.run(populationSize, individualSize, iterationNr)
            runningTime += time.perf_counter() - timeForOneRun
            plt.plot(statsOnePop[0])
            plt.ylabel("fitness")
            plt.show()
            f, d = self.repository.getAverageAndStd()
            self.statistics[0].append(f)
            self.statistics[1].append(d)
            population = self.repository.getLastPopulation()
            population.evaluate(self.map, self.x, self.y)
            i, f = population.getBestIndividual()
            path = self.repository.createPath(i, self.x, self.y)
            self.bestPath[0].append(path)
            self.bestPath[1].append(f)
        return self.statistics[0], self.statistics[1], runningTime
       