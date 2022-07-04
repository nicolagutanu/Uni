from concurrent.futures import ThreadPoolExecutor
import random
import time


def regular_alg(x, y):
    prod = [0] * (len(x) + len(y) - 1)
    for i in range(len(x)):
        for j in range(len(y)):
            prod[i+j] += x[i] * y[j]
    return prod


def calc_nr_elems(x, y, start, end, result):
    for i in range(start, end):
        if i > len(result):
            return
        for j in range(0, i + 1):
            if j < len(x) and i - j < len(y):
                result[i] += x[j] * y[i - j]


def regular_alg_parallel(x, y):
    prod = [0] * (len(p1) + len(p2) - 1)
    executor = ThreadPoolExecutor(nr_threads)
    nr_elems = int(len(prod) / nr_threads)
    for i in range(0, len(prod), nr_elems):
        executor.submit(calc_nr_elems, x, y, i, i + nr_elems, prod)
    return prod


def karatsuba(x, y):
    if len(x) < 3 and len(y) < 3:
        return regular_alg(x, y)
    d = len(x)
    s = int(d/2)
    x0 = x[:s]
    x1 = x[s:]
    y0 = y[:s]
    y1 = y[s:]

    c0 = karatsuba(x0, y0)
    h1 = add_poly(x0, x1)
    h2 = add_poly(y0, y1)
    c1 = karatsuba(h1, h2)
    c2 = karatsuba(x1, y1)

    r1 = shift(c2, s * 2)
    h1 = substract_poly(c1, c2)
    h2 = substract_poly(h1, c0)
    r2 = shift(h2, s)

    return add_poly(add_poly(r1, r2), c0)


def karatsuba_parallel(x, y, depth):
    if depth > 4:
        return karatsuba(x, y)

    if len(x) < 3 and len(y) < 3:
        return karatsuba(x, y)
    d = len(x)
    s = int(d / 2)
    x0 = x[:s]
    x1 = x[s:]
    y0 = y[:s]
    y1 = y[s:]

    executor = ThreadPoolExecutor(nr_threads)
    f0 = executor.submit(karatsuba_parallel, x0, y0, depth + 1)
    f1 = executor.submit(karatsuba_parallel, add_poly(x0, x1), add_poly(y0, y1), depth + 1)
    f2 = executor.submit(karatsuba_parallel, x1, y1, depth + 1)

    c0 = f0.result()
    c1 = f1.result()
    c2 = f2.result()

    r1 = shift(c2, s * 2)
    h1 = substract_poly(c1, c2)
    h2 = substract_poly(h1, c0)
    r2 = shift(h2, s)

    return add_poly(add_poly(r1, r2), c0)


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


def substract_poly(a, b):
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


def make_polynomials():
    x, y = [], []
    for i in range(size):
        nr1 = random.randrange(1, 10)
        x.append(nr1)
        nr2 = random.randrange(1, 10)
        y.append(nr2)
    return [2, 8, 9, 9, 3, 7], [5, 4, 2, 2, 3, 5]
    #return x, y


if __name__ == '__main__':
    size = int(input("size of polynomials >>> "))
    nr_threads = int(input("nr of threads >>> "))
    p1, p2 = make_polynomials()
    print(p1)
    print(p2)

    start1 = time.time()
    (regular_alg(p1, p2))
    result1 = regular_alg(p1, p2)
    end1 = time.time()
    print(result1)
    print("Regular alg, no threads:", end1 - start1)

    '''
    start2 = time.time()
    result2 = karatsuba(p1, p2)
    end2 = time.time()
    print(result2)
    print("Karatsuba, no threads:", end2 - start2)


    start3 = time.time()
    result3 = regular_alg_parallel(p1, p2)
    end3 = time.time()
    print(result3)
    print("Regular alg, with threads:", end3 - start3)

    start4 = time.time()
    result4 = karatsuba_parallel(p1, p2, 4)
    end4 = time.time()
    print(result4)
    print("Karatsuba, with threads:", end4 - start4)
    '''