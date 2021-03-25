def prime(a): #checks if it's a prime number
    p=0
    for i in range(2,a//2):
        if a%i==0:
            p=1
    if p==1:
        return False
    else:
        return True
def look(n):
    p1=n+1
    a=False
    while a==False:
        while prime(p1)==False:
            p1=p1+1 #look for the first prime number greater than n
        p2=p1+1
        while prime(p2)==False:
            p2=p2+1 #look for second prime number greater than p1
        if p2-p1==2: #check the propriety
            print(p1,p2)
            a=True
        else:
            p1=p2 #if the numbers are not good, the first number becomes the second and we start the process again
if __name__=="__main__":
    look(int(input("n=")))
    
