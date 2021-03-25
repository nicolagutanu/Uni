def sum(l):
    if type(l) != list:
        raise TypeError("not a python list\n")
    e = 0
    for i in range(len(l)):
        if l[i] % 2 == 0:
            e = e + l[i]
    if e == 0:
        raise ValueError("there are no even numbers in the list\n")
    else:
        return e

def test_sum():
    l = {}
    try:
        sum(l)
    except Exception as ex:
        assert (str(ex) == "not a python list\n")
    l = [1,1,1,1]
    try:
        sum(l)
    except Exception as ex:
        assert (str(ex) == "there are no even numbers in the list\n")


test_sum()

'''
def error(l):
    if type(l) != list:
        raise TypeError("not a python list\n")
    e = 0
    for i in range(len(l)):
        if l[i] % 2 == 0:
            e = e + 1
    if e == 0:
        raise ValueError("no even numbers in the list\n")

def sum(l):
    try:
        error(l)
        s = 0
        for i in range(len(l)):
            if l[i] % 2 == 0:
                s = s + l[i]
        print(s)
    except Exception as ex:
        print(str(ex))

if __name__ == "__main__":
    n = int(input("The length of the series = "))
    a = []
    for i in range(0, n):
        p = int(input(">>>"))
        a.append(p)
    sum(a)

'''