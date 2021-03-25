def valid(k):
    if abs(x[k-1] - x[k]) < m:
        return False
    if x[k-1] == x[k]:
        return False
    return True


def printf(x, k):
    for i in range(0, k):
        print(x[i], end=" ")


def back(k):
    for i in range(1, n+1):
        x[k] = i
        if valid(k):
            if k == m:
                print(x)
            else:
                back(k+1)

if __name__=="__main__":
    n = int(input("The length of the series = "))
    m = int(input("The length of the series = "))
    x = []
    for i in range(0, 10):
        x.append(-1)
    back(0)
