def valid(x):
    s = a[0]
    for i in range(0, len(x)):
        if x[i] == '0':
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
        if x[i] == '0':
            msg = msg + str(a[i]) + '+'
        else:
            msg = msg + str(a[i]) + '-'
    msg = msg + str(a[n-1]) + '\n'
    print(msg)


def back(a):
    nr = pow(2, len(a)-1)
    for i in range(nr):
        binary = list(bin(i)[2:].zfill(len(a)-1))
        if valid(binary):
            printf(binary)



if __name__=="__main__":
    n = int(input("The length of the series = "))
    a = []
    for i in range(0, n):
        p = int(input(">>>"))
        a.append(p)
    back(a)
