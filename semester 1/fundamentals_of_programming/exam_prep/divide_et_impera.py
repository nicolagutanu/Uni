def test(l):
    if type(l) != list:
        raise TypeError("not a python list\n")
    e = 0
    for i in range(len(l)):
        if l[i] % 2 == 0:
            e = e + 1
    if e == 0:
        raise ValueError("no even numbers in the list\n")

def div_et_imp(l, st, dr):
    if st <= dr:
        mij = (st+dr)//2
        c = 0
        if l[mij] % 2 == 0 and mij % 2 == 0:
            c = l[mij]
        return c + div_et_imp(l, st, mij-1) + div_et_imp(l, mij+1, dr)
    else:
        return 0



if __name__=="__main__":
    n = int(input("The length of the series = "))
    l = []
    for i in range(0, n):
        p = int(input(">>>"))
        l.append(p)
    try:
        test(l)
        print(div_et_imp(l, 0, len(l)-1))
    except Exception as ex:
        print(str(ex))
