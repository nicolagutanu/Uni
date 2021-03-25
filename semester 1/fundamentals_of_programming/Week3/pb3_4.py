
def show_numbers(d): #prints a list
    print(d)

def p_re(d, poz): #access to the real part of a complex number
    return d[poz][0]

def p_im(d, poz): #access to the imaginary part of a complex number
    return d[poz][1]

def modulus(d,poz): #calculates the modulus of a complex number
    return (p_im(d,poz)*p_im(d,poz)+p_re(d,poz)*p_re(d,poz))

def prop3(d,n): #returns the longest sequence of complex numbers that have equal modulus
    i = 0
    maxim = 0
    a = []
    while i < n-2:
        p = 0
        b=[]
        b.append(d[i])
        while modulus(d,i)==modulus(d,i+1) and i<n-2:
            p=p+1
            b.append(d[i+1])
            i=i+1
        i=i+1
        if i==n-1:
            if modulus(d,i-1)==modulus(d,i):
                p=p+1
                b.append(d[i])
        if p>maxim:
            a=b
    return a

def prop4(d,n): #returns the longest sequence of complex numbers that have increasing modulus
    i = 0
    maxim = 0
    a = []
    while i < n-2:
        p = 0
        b=[]
        b.append(d[i])
        m1=modulus(d,i)
        m2=modulus(d,i+1)
        while m1<m2 and i<n-2:
            p=p+1
            b.append(d[i+1])
            i=i+1
            m1=modulus(d,i)
            m2=modulus(d,i+1)
        i=i+1
        if i==n-1:
            m1=modulus(d,i-1)
            m2=modulus(d,i)
            if m1<m2:
                p=p+1
                b.append(d[i])
        if p>maxim:
            a=b
    return a

def cit(d,n): #reads the complex numbers from th console and puts them into a list as lists
    for i in range(0,n):
        re = int(input("Partea reala = "))
        im = int(input("Partea imaginara = "))
        d[i] = [re, im]
    
def menu():
    print("MENU")
    print("1. Read a list of complex numbers from the console.")
    print("2. Display the entire list of numbers on the console.")
    print("3. Display on the console the longest sequence of numbers having the same modulus")
    print("4. Display on the console the longest sequence of numbers having increasing modulus")
    print("5. Exit the program")
    
if __name__=="__main__":
    d=[[1,0],[2,0],[3,0],[4,0],[5,0],[6,0],[7,0],[8,0],[9,0],[10,0]]
    n=10
    while True:
        menu()
        button = int(input("Please enter your chosen instruction: "))
        if button == 1:
            n = int(input("Number of complex numbers = "))
            cit(d,n)
        elif button==2:
            show_numbers(d)
        elif button==3:
            print(prop3(d,n))
        elif button==4:
            print(prop4(d,n))
        elif button==5:
            exit()
        else:
            print("invalid")
    
