def isEmpty(lista):
    if len(lista)==0:
        return true
    else:
        return false

def oneElem(lista):
    if len(lista)==1:
        return true
    else:
        return false

def sublist(lista):
    lista.remove(lista[0])

def firstElem(lista):
    return lista[0]

def getValues():
    lista=list()
    x=int(input("x="))
    while (x!=0):
        lista.append(x)
        x=int(input("x="))
    return lista

def gcd(a, b):
    if a==b:
        return a
    elif a>b:
        return gcd(a-b, b)
    else:
        return gcd(a, b-a)

def great(lista, value):
    if oneElem(lista):
        return gcd(lista[0], value)
    else:
        first=lista[0]
        return great(lista[1:], gcd(first, value))

def main():
    lista=getValues()
    print(great(lista,1))

main()