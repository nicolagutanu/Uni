def solution(n): 
    if n<4:
        print(n) #if the number id smaller than 4, we print it
    else:
        p=4
        i=3
        while i<n: #otherwise we search for the prime divisors of each number and count it once
            c=p
            k=2
            while c!=1 and i<n:
                if c%k==0:
                    i=i+1
                    while c%k==0:
                        c=c//k #then we divide the number until undividable with the prime divisor
                else:
                    k=k+1 #we raise the prime divisor
            p=p+1
        print(k)
if __name__=="__main__":
    solution(int(input("n=")))
