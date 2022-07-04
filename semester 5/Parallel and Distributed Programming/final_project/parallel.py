import random
import time
from concurrent.futures import ThreadPoolExecutor


def graph_color_help_parallel(solution, v):
    # if we checked all vertices, we are done
    if v == nr_vertices:
        return True

    executor = ThreadPoolExecutor(nr_threads)
    for c in range(1, nr_colors + 1):
        # check if color fits in solution
        if is_safe(v, solution, c):
            solution[v] = c
            # we move to the next vertex in the graph and try to assign it a color
            f = executor.submit(graph_color_help, solution, v + 1)
            if f.result():
                return True
            # if not, solution remains empty
            solution[v] = 0


def graph_coloring_parallel():
    solution = [0] * nr_vertices
    # start looking from vertex 0
    if graph_color_help_parallel(solution, 0) is None:
        print("Graph doesn't have a solution")
    else:
        print("Solution:")
        print(solution)


# checks adjacent vertices to not be the same color as the given vertex
def is_safe(v, solution, c):
    for i in range(nr_vertices):
        if graph[v][i] == 1 and solution[i] == c:
            return False
    return True


def graph_color_help(solution, v):
    # if we checked all vertices, we are done
    if v == nr_vertices:
        return True

    for c in range(1, nr_colors + 1):
        # check if color fits in solution
        if is_safe(v, solution, c):
            solution[v] = c
            # we move to the next vertex in the graph and try to assign it a color
            if graph_color_help(solution, v + 1):
                return True
            # if not, solution remains empty
            solution[v] = 0


def graph_coloring():
    solution = [0] * nr_vertices
    # start looking from vertex 0
    if graph_color_help(solution, 0) is None:
        print("Graph doesn't have a solution")
    else:
        print("Solution:")
        print(solution)


def make_graph():
    g = [[0 for column in range(nr_vertices)] for row in range(nr_vertices)]
    for i in range(1, nr_vertices):
        for j in range(0, i):
            if random.randrange(0, 2) == 1:
                g[i][j] = 1
                g[j][i] = 1
    return g


if __name__ == '__main__':
    '''
    # wrong
    graph = [[1, 1, 1, 1, 1], 
            [1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1], 
            [1, 1, 1, 1, 1]]
           
    # good 
    graph = [[0, 1, 1, 1],
            [1, 0, 1, 0],
            [1, 1, 0, 1],
            [1, 0, 1, 0]]
    '''

    nr_vertices = int(input("nr of vertices >>> "))
    nr_colors = int(input("nr of colors >>> "))
    nr_threads = int(input("nr of threads >>> "))
    graph = make_graph()
    print(graph)

    start1 = time.time()
    graph_coloring()
    end1 = time.time()
    print("Simple graph coloring:", end1 - start1)

    start2 = time.time()
    graph_coloring_parallel()
    end2 = time.time()
    print("Parallelized graph coloring:", end2 - start2)