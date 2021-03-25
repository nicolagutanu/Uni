def digits(n): #find the number of digits of the given number
    i = 0
    while n>0:
        i = i+1
        n = n // 10
    return i
def create_number(d,p): #we create the number with he sorted list
    a = 0
    i = 0
    for i in range(p):
        a = a*10+d[i]
    return a
def take_digits(n): 
    p = digits(n)
    d = list(range(0,p))
    i = 0
    while n>0: #we have the given number, which digits we put in a list
        d[i] = n % 10
        n = n // 10
        i = i+1
    d.sort() #we sort and reverse the list to get the biggest number possible
    d.reverse()
    print(create_number(d,p))
if __name__=="__main__":
    take_digits(int(input("n=")))
