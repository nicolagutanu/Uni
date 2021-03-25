def valid(k):
    s = a[0]
    for i in range(0, k):
        if x[i] == 0:
            s = s + a[i+1]
        else:
            s = s - a[i+1]
    if s < 0:
        return False
    else:
        return True


def printf(x):
    msg = ''
    for i in range(0, n - 1):
        if x[i] == 0:
            msg = msg + str(a[i]) + '+' #print(a[i], '+')
        else:
            msg = msg + str(a[i]) + '-' #print(a[i], '-')
    msg = msg + str(a[n-1]) + '\n' #print(a[n-1])
    print(msg)


def back(k):
    for i in range(0, 2):
        x[k] = i
        if valid(k):
            if k == n - 2:
                printf(x)
            else:
                back(k + 1)

if __name__=="__main__":
    n = int(input("The length of the series = "))
    a = []
    for i in range(0, n):
        p = int(input(">>>"))
        a.append(p)
    x = []
    for i in range(0, n-1):
        x.append(-1)
    back(0)