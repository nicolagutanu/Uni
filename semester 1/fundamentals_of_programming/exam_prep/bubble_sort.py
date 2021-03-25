'''
container = [7,1,2,6,4,2,3]
length = len(container)

while length >= 1:
    num = 0
    for i in range(1, length):
        if container[i-1] > container[i]:
            container[i-1], container[i] = container[i], container[i-1]
            num = i
            #print(num,'\n')
    length = num
    print(container)

'''


def sort(l):
    length = len(l)
    while length >= 1:
        num = 0
        for i in range(1, length):
            if l[i-1] > l[i]:
                aux = l[i-1]
                l[i-1] = l[i]
                l[i] = aux
                num = i
        length = num
    return l

if __name__ == "__main__":
    l = [7,1,2,6,4,2,3]
    print(sort(l))