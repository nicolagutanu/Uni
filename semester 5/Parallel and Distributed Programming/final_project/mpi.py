import random
import time
from mpi4py import MPI


# checks adjacent vertices to not be the same color as the given vertex
def is_safe(v, solution, c, nr_vert, g):
    for i in range(nr_vert):
        if g[v][i] == 1 and solution[i] == c:
            return False
    return True


def graph_color_help(solution, v, end, nr_col, nr_vert, g):
    # if we checked all vertices, we are done
    if v == end:
        return solution

    for c in range(1, nr_col + 1):
        # check if color fits in solution
        if is_safe(v, solution, c, nr_vert, g):
            solution[v] = c
            # we move to the next vertex in the graph and try to assign it a color
            if graph_color_help(solution, v + 1, end, nr_col, nr_vert, g):
                return solution
            # if not, solution remains empty
            solution[v] = 0


'''
def graph_coloring():
    solution = [0] * nr_vertices
    # start looking from vertex 0
    if graph_color_help(solution, 0) is None:
        print("Graph doesn't have a solution")
    else:
        print("Solution:")
        print(solution)
'''


def graph_coloring_worker(my_proc):
    begin, end, nr_vert, nr_col = 0, 0, 0, 0
    result, g = [], []

    begin = MPI.COMM_WORLD.recv(source=0, tag=0)
    end = MPI.COMM_WORLD.recv(source=0, tag=0)
    result = MPI.COMM_WORLD.recv(source=0, tag=0)

    nr_vert = MPI.COMM_WORLD.recv(source=0, tag=0)
    nr_col = MPI.COMM_WORLD.recv(source=0, tag=0)
    g = MPI.COMM_WORLD.recv(source=0, tag=0)

    result = graph_color_help(result, begin, end, nr_col, nr_vert, g)

    MPI.COMM_WORLD.send(result, dest=0, tag=0)


def graph_coloring_master(g, nr_vert, nr_col, nr_proc):
    start = time.time()
    chunk_size = int(nr_vert / (nr_proc - 1))

    begin, end = 0, 0
    result = [0] * nr_vert
    for i in range(1, nr_proc):
        begin = end
        end += chunk_size
        if i == nr_proc - 1:
            end = nr_vert

        MPI.COMM_WORLD.send(begin, dest=i, tag=0)
        MPI.COMM_WORLD.send(end, dest=i, tag=0)
        MPI.COMM_WORLD.send(result, dest=i, tag=0)

        MPI.COMM_WORLD.send(nr_vert, dest=i, tag=0)
        MPI.COMM_WORLD.send(nr_col, dest=i, tag=0)
        MPI.COMM_WORLD.send(g, dest=i, tag=0)

        result = MPI.COMM_WORLD.recv(source=i, tag=0)

    end = time.time()
    print("Result:", result)
    print("Execution time:", end - start)


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
    my_process = MPI.COMM_WORLD.Get_rank()
    nr_process = MPI.COMM_WORLD.Get_size()

    if my_process == 0:
        nr_vertices = 7
        nr_colors = 3
        graph = make_graph()
        print(graph)

        graph_coloring_master(graph, nr_vertices, nr_colors, nr_process)
    else:
        graph_coloring_worker(my_process)

    MPI.Finalize()
    # mpiexec -n 4 python main.py