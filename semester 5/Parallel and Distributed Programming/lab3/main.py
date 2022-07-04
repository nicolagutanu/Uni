import threading
import numpy as np
import time


def calc_tasks(start, stop):
    global mat_size
    total = 0
    i = start[0]
    j = start[1]
    while j < mat_size:
        if total <= stop:
            result_mat_row[i][j] = calc_one_elem(i, j)
            total += 1
            j += 1
        else:
            return
    for i in range(start[0] + 1, mat_size):
        for j in range(mat_size):
            if total <= stop:
                result_mat_row[i][j] = calc_one_elem(i, j)
                total += 1
            else:
                return


def calc_one_elem(row_mat1, col_mat2):
    global mat1, mat2
    result = 0
    for i in range(len(mat1[row_mat1])):
        result += mat1[row_mat1][i] * mat2[i][col_mat2]
    return result


def calc_ss_point():
    global nr_tasks, mat_size
    elems_per_task = int((mat_size * mat_size) / nr_tasks)
    start_stop = []
    total = 0
    pair = []
    for i in range(mat_size):
        for j in range(mat_size):
            if total == 0:
                pair = [(i, j), 0]
                total += 1
            elif total == elems_per_task - 1:
                total = 0
                pair[1] = elems_per_task
                start_stop.append(pair)
            else:
                total += 1
    start_stop[len(start_stop)-1][1] = (mat_size * mat_size) - (nr_tasks - 1) * elems_per_task
    return start_stop


def generate_mat(n):
    global mat1, mat2, result_mat_row
    mat1 = np.random.randint(1, 9, size=(n, n))
    mat2 = np.random.randint(1, 9, size=(n, n))
    result_mat = np.random.randint(0, 1, size=(n, n))


mat1 = []
mat2 = []
result_mat_row = []
nr_tasks = 0
mat_size = 0

if __name__ == '__main__':
    mat_size = int(input("matrix size >>>"))
    nr_tasks = int(input("nr of tasks >>>"))
    generate_mat(mat_size)
    threads = []
    start_stop = calc_ss_point()
    start = time.time()
    for i in range(nr_tasks):
        t = threading.Thread(target=calc_tasks, args=(start_stop[i][0], start_stop[i][1]))
        threads.append(t)
        t.start()

    for i in range(len(threads)):
        threads[i].join()

    end = time.time()
    print(end - start)

    print(mat1)
    print(mat2)
    print("--------------------------------")
    print(result_mat_row)
