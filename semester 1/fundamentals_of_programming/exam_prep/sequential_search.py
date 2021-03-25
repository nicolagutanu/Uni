def Sequential_Search(x, v):
    pos = 0
    found = False

    while pos < len(x) and not found:
        if x[pos] == v:
            found = True
        else:
            pos = pos + 1

    return pos


print(Sequential_Search([11, 23, 58, 31, 56, 77, 43, 12, 65, 19], 31))