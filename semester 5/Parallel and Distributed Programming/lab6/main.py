import random
from concurrent.futures import ThreadPoolExecutor
import time
import sys


def is_connected(v, pos, path):
    # check if current vertex and last are connected
    if graph[path[pos - 1]][v] == 0:
        return False

    # check vertex not in path already
    for vertex in path:
        if vertex == v:
            return False

    return True


def hamiltonian_cycle_help_parallel(path, pos):
    # chech if all vertices are in path
    if pos == nr_vertices:
        # check last vertex is connected to first
        if graph[path[pos - 1]][path[0]] == 1:
            return True
        else:
            return False

    executor = ThreadPoolExecutor(nr_threads)
    # try building the hamiltonian cycle starting with 1
    for v in range(1, nr_vertices):
        if is_connected(v, pos, path):
            path[pos] = v
            f = executor.submit(hamiltonian_cycle_help, path, pos + 1)
            if f.result():
                return True
            # remove last vertex if not a solution
            path[pos] = -1

    return False


def hamiltonian_cycle_parallel():
    path = [-1] * nr_vertices
    path[0] = 0

    if not hamiltonian_cycle_help_parallel(path, 1):
        print("Solution does not exist")
        return False

    print_cycle(path)
    return True


def hamiltonian_cycle_help(path, pos):
    # chech if all vertices are in path
    if pos == nr_vertices:
        # check last vertex is connected to first
        if graph[path[pos - 1]][path[0]] == 1:
            return True
        else:
            return False

    # try building the hamiltonian cycle starting with 1
    for v in range(1, nr_vertices):
        if is_connected(v, pos, path):
            path[pos] = v
            if hamiltonian_cycle_help(path, pos + 1):
                return True
            # remove last vertex if not a solution
            path[pos] = -1

    return False


def hamiltonian_cycle():
    path = [-1] * nr_vertices
    path[0] = 0

    if not hamiltonian_cycle_help(path, 1):
        print("Solution does not exist")
        return False

    print_cycle(path)
    return True


def print_cycle(path):
    for vertex in path:
        print(vertex, end=" ")
    print(path[0], "\n")


def make_graph():
    g = [[random.randrange(0, 2) for column in range(nr_vertices)] for row in range(nr_vertices)]
    return g


if __name__ == '__main__':
    '''
    graph = [[0, 1, 0, 1, 0], [1, 0, 1, 1, 1],
             [0, 1, 0, 0, 1, ], [1, 1, 0, 0, 1],
             [0, 1, 1, 1, 0], ]
    graph = [[0, 1, 0, 1, 0], [1, 0, 1, 1, 1],
             [0, 1, 0, 0, 1, ], [1, 1, 0, 0, 0],
             [0, 1, 1, 0, 0]]
    '''


    nr_vertices = int(input("nr of vertices >>> "))
    nr_threads = int(input("nr of threads >>> "))
    #graph = make_graph()
    sys.setrecursionlimit(1500)

    start1 = time.time()
    hamiltonian_cycle()
    end1 = time.time()
    print("Simple hamiltonian cycle:", end1 - start1)

    start2 = time.time()
    hamiltonian_cycle_parallel()
    end2 = time.time()
    print("Parallelized hamiltonian cycle:", end2 - start2)