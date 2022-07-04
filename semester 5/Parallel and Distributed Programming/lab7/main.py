from concurrent.futures import ThreadPoolExecutor
import random
import time
from mpi4py import MPI
import math


def add_poly(a, b):
    result = []
    if len(a) < len(b):
        for i in range(len(a)):
            result.append(a[i] + b[i])
        for j in range(len(a), len(b)):
            result.append(b[j])

    elif len(a) > len(b):
        for i in range(len(b)):
            result.append(a[i] + b[i])
        for j in range(len(b), len(a)):
            result.append(a[j])

    else:
        for i in range(len(b)):
            result.append(a[i] + b[i])

    return result


def subtract_poly(a, b):
    result = []
    if len(a) < len(b):
        for i in range(len(a)):
            result.append(a[i] - b[i])
        for j in range(len(a), len(b)):
            result.append(b[j])

    elif len(a) > len(b):
        for i in range(len(b)):
            result.append(a[i] - b[i])
        for j in range(len(b), len(a)):
            result.append(a[j])

    else:
        for i in range(len(b)):
            result.append(a[i] - b[i])

    k = len(result) - 1
    while result[k] == 0 and k > 0:
        result.pop(k)
        k -= 1

    return result


def shift(p, zeros):
    new_p = []
    for i in range(zeros):
        new_p.append(0)
    for i in range(len(p)):
        new_p.append(p[i])
    return new_p


def regular_alg(x, y, begin, end):
    prod = [0] * (len(x) + len(y) - 1)
    for i in range(begin, end):
        for j in range(len(y)):
            prod[i+j] += x[i] * y[j]
    return prod


def regular_alg_simple(x, y):
    prod = [0] * (len(x) + len(y) - 1)
    for i in range(len(x)):
        for j in range(len(y)):
            prod[i+j] += x[i] * y[j]
    return prod


def karatsuba(x, y):
    if len(x) < 3 and len(y) < 3:
        return regular_alg_simple(x, y)
    d = max(len(x), len(y))
    s = int(d/2)
    x0 = x[:s]
    x1 = x[s:len(x)]
    y0 = y[:s]
    y1 = y[s:len(y)]

    c0 = karatsuba(x0, y0)
    h1 = add_poly(x0, x1)
    h2 = add_poly(y0, y1)
    c1 = karatsuba(h1, h2)
    c2 = karatsuba(x1, y1)

    r1 = shift(c2, s * 2)
    h1 = subtract_poly(c1, c2)
    h2 = subtract_poly(h1, c0)
    r2 = shift(h2, s)

    return add_poly(add_poly(r1, r2), c0)


def multiply_regular_worker(my_proc):
    a, b = [], []
    begin, end = 0, 0

    a = MPI.COMM_WORLD.recv(source=0, tag=0)
    b = MPI.COMM_WORLD.recv(source=0, tag=0)
    begin = MPI.COMM_WORLD.recv(source=0, tag=0)
    end = MPI.COMM_WORLD.recv(source=0, tag=0)

    result = regular_alg(a, b, begin, end)

    MPI.COMM_WORLD.send(result, dest=0, tag=0)


def multiply_karatsuba_worker(my_proc):
    a, b = [], []
    begin, end = 0, 0

    a = MPI.COMM_WORLD.recv(source=0, tag=0)
    b = MPI.COMM_WORLD.recv(source=0, tag=0)
    begin = MPI.COMM_WORLD.recv(source=0, tag=0)
    end = MPI.COMM_WORLD.recv(source=0, tag=0)

    for i in range(0, begin):
        a[i] = 0
    for i in range(end, len(a)):
        a[i] = 0

    result = karatsuba(a, b)

    MPI.COMM_WORLD.send(result, dest=0, tag=0)


def multiplication_master(a, b, nr_proc):
    start = time.time()
    chunk_size = int(len(a) / (nr_proc - 1))

    begin, end = 0, 0
    for i in range(1, nr_proc):
        begin = end
        end += chunk_size
        if i == nr_proc - 1:
            end = len(a)

        MPI.COMM_WORLD.send(a, dest=i, tag=0)
        MPI.COMM_WORLD.send(b, dest=i, tag=0)
        MPI.COMM_WORLD.send(begin, dest=i, tag=0)
        MPI.COMM_WORLD.send(end, dest=i, tag=0)

    result = []
    for i in range(1, nr_proc):
        result.append(MPI.COMM_WORLD.recv(source=i, tag=0))

    final = []
    for i in range(len(result[0])):
        coeff = 0
        for j in range(len(result)):
            coeff += result[j][i]
        final.append(coeff)

    end = time.time()
    print("Result:", final)
    print("Execution time:", end - start)


def make_polynomials():
    x, y = [], []
    for i in range(size):
        nr1 = random.randrange(1, 10)
        x.append(nr1)
        nr2 = random.randrange(1, 10)
        y.append(nr2)
    #return [1, 4, 1, 3, 8, 6], [2, 5, 5, 4, 9, 1]
    return x, y


if __name__ == '__main__':
    my_process = MPI.COMM_WORLD.Get_rank()
    nr_process = MPI.COMM_WORLD.Get_size()

    alg = "karatsuba"

    if my_process == 0:
        size = 10
        p1, p2 = make_polynomials()
        print(p1)
        print(p2)

        multiplication_master(p1, p2, nr_process)
    elif alg == "karatsuba":
        multiply_karatsuba_worker(my_process)
    else:
        multiply_regular_worker(my_process)

    MPI.Finalize()

    '''
    # mpiexec -n 4 python main.py
    '''