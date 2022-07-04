from concurrent.futures import ThreadPoolExecutor
import time
import numpy as np


def calc_tasks_row(start_stop):
    global mat_size
    start = start_stop[0]
    stop = start_stop[1]
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


def calc_tasks_col(start_stop):
    global mat_size
    start = start_stop[0]
    stop = start_stop[1]
    total = 0
    i = start[0]
    j = start[1]
    while i < mat_size:
        if total <= stop:
            result_mat_col[i][j] = calc_one_elem(i, j)
            total += 1
            i += 1
        else:
            return
    for j in range(start[0] + 1, mat_size):
        for i in range(mat_size):
            if total <= stop:
                result_mat_col[i][j] = calc_one_elem(i, j)
                total += 1
            else:
                return


def calc_tasks_kth(task):
    global mat_size, nr_tasks
    total = nr_tasks - 1
    start = 0
    for i in range(mat_size):
        for j in range(mat_size):
            if start < task:
                start += 1
            elif total == nr_tasks - 1:
                total = 0
                result_mat_kth[i][j] = calc_one_elem(i, j)
            else:
                total += 1



def calc_one_elem(row_mat1, col_mat2):
    global mat1, mat2
    result = 0
    for i in range(len(mat1[row_mat1])):
        result += mat1[row_mat1][i] * mat2[i][col_mat2]
    return result


def calc_ss_point_row():
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
    start_stop[len(start_stop) - 1][1] = (mat_size * mat_size) - (nr_tasks - 1) * elems_per_task
    return start_stop


def calc_ss_point_col():
    global nr_tasks, mat_size
    elems_per_task = int((mat_size * mat_size) / nr_tasks)
    start_stop = []
    total = 0
    pair = []
    for j in range(mat_size):
        for i in range(mat_size):
            if total == 0:
                pair = [(i, j), 0]
                total += 1
            elif total == elems_per_task - 1:
                total = 0
                pair[1] = elems_per_task
                start_stop.append(pair)
            else:
                total += 1
    start_stop[len(start_stop) - 1][1] = (mat_size * mat_size) - (nr_tasks - 1) * elems_per_task
    return start_stop


def generate_mat(n):
    global mat1, mat2, result_mat_row, result_mat_col, result_mat_kth
    mat1 = np.random.randint(1, 9, size=(n, n))
    mat2 = np.random.randint(1, 9, size=(n, n))
    result_mat_row = np.random.randint(0, 1, size=(n, n))
    result_mat_col = np.random.randint(0, 1, size=(n, n))
    result_mat_kth = np.random.randint(0, 1, size=(n, n))


mat1 = []
mat2 = []
result_mat_row = []
result_mat_col = []
result_mat_kth = []
nr_tasks = 0
mat_size = 0

if __name__ == '__main__':
    mat_size = int(input("matrix size >>> "))
    nr_tasks = int(input("nr of tasks >>> "))
    nr_threads = int(input("nr of threads for the pool >>> "))
    generate_mat(mat_size)

    start_stop_row = calc_ss_point_row()
    start_row = time.time()
    executor_row = ThreadPoolExecutor(nr_threads)
    executor_row.map(calc_tasks_row, start_stop_row)
    end_row = time.time()
    print("For row:", end_row - start_row)

    start_stop_col = calc_ss_point_col()
    start_col = time.time()
    executor_col = ThreadPoolExecutor(nr_threads)
    executor_col.map(calc_tasks_col, start_stop_col)
    end_col = time.time()
    print("For col:", end_col - start_col)

    task_kth = list(range(0, nr_tasks))
    start_kth = time.time()
    executor_kth = ThreadPoolExecutor(nr_threads)
    executor_kth.map(calc_tasks_kth, task_kth)
    end_kth = time.time()
    print("For kth:", end_kth - start_kth)

    print(mat1)
    print(mat2)
    print("--------------------------------")
    print(result_mat_row)
    print("--------------------------------")
    print(result_mat_col)
    print("--------------------------------")
    print(result_mat_kth)
