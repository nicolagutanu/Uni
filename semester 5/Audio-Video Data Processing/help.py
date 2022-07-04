def get_size(elem):
    if abs(elem) == 1:
        return 1
    elif abs(elem) in [2, 3]:
        return 2
    elif 4 <= abs(elem) <= 7:
        return 3
    elif 8 <= abs(elem) <= 15:
        return 4
    elif 16 <= abs(elem) <= 31:
        return 5
    elif 32 <= abs(elem) <= 63:
        return 6
    elif 64 <= abs(elem) <= 127:
        return 7
    elif 128 <= abs(elem) <= 255:
        return 8
    elif 256 <= abs(elem) <= 511:
        return 9
    elif 512 <= abs(elem) <= 1023:
        return 10


def make_encoded_list_from_zigzag(zigzag_list):
    result = [(get_size(zigzag_list[0]), zigzag_list[0])]
    zeros = 0
    for i in range(1, len(zigzag_list)):
        elem = zigzag_list[i]
        if elem == 0:
            zeros += 1
        else:
            result.append((zeros, get_size(elem), elem))
            zeros = 0
    if zeros != 0:
        result.append((0, 0))
    return result


def make_zigzag_list(matrix):
    sol = [[] for i in range(15)]
    for i in range(8):
        for j in range(8):
            sum = i + j
            if sum % 2 == 0:
                sol[sum].insert(0, matrix[i][j])
            else:
                sol[sum].append(matrix[i][j])
    final_list = []
    for i in sol:
        for j in i:
            final_list.append(j)
    print(final_list)
    return make_encoded_list_from_zigzag(final_list)


def make_list_for_zigzag(list_elems):
        # list_elems = [(2, 4), (0, 8, 151), ..., (0, 0)]
        # first = (size, amplitude)
        # last = finishes with 0
        # inbetween = (count zeros before, size, amplitude)
        result = []
        for elem in list_elems:
            if len(elem) == 3:
                if elem[0] == 0:
                    result.append(elem[2])
                else:
                    for i in range(elem[0]):
                        result.append(0)
                    result.append(elem[2])
            elif elem[1] != 0:
                result.append(elem[1])
            else:
                steps = len(result)
                for i in range(0, 64 - steps):
                    result.append(0)
        return result


def make_matrix_for_block(lit):
    res = [[0 for i in range(8)] for j in range(8)]
    i, j = 0, 0
    while i + j != 14:
        if (i + j) % 2 == 0:
            k = i
            p = j
            while k >= 0 and p <= 7:
                if lit[0] != 0:
                    res[k][p] = lit.pop(0)
                else:
                    lit.pop(0)
                k -= 1
                p += 1
            if p == 8:
                i = k + 2
                j = p - 1
            else:
                i = k + 1
                j = p
        else:
            k = i
            p = j
            while p >= 0 and k <= 7:
                if lit[0] != 0:
                    res[k][p] = lit.pop(0)
                else:
                    lit.pop(0)
                k += 1
                p -= 1
            if k == 8:
                i = k - 1
                j = p + 2
            else:
                i = k
                j = p + 1
    return res


matrix = [[150, 80, 20, 4, 1, 0, 0, 0],
          [92, 75, 18, 3, 1, 0, 0, 0],
          [26, 19, 13, 2, 1, 0, 0, 0],
          [3, 2, 2, 1, 0, 0, 0, 0],
          [1, 0, 0, 0, 0, 0, 0, 0],
          [0, 0, 0, 0, 0, 0, 0, 0],
          [0, 0, 0, 0, 0, 0, 0, 0],
          [0, 0, 0, 0, 0, 0, 0, 0]]

result = make_zigzag_list(matrix)
r = make_list_for_zigzag(result)
re = make_matrix_for_block(r)
print(re)

