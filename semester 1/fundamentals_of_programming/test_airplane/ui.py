def add(d,l):
    d.append([l[1],int(l[2]),l[3],l[4]])

def delete(d,l):
    i=0
    while i<len(d):
        if d[i][0]==l[1]:
            d.remove(d[i])
        i=i+1

def show(d,l):
    for i in range(0,len(d)):
        if d[i][2]==l[1]:
            print(d[i])

def sort(d):
    p=[]
    for i in range(0,len(d)):
        p.append(d[i])
    for i in range(0,len(p)-1):
        for j in range(i+1,len(p)):
            if p[i][3]>p[j][3]:
                aux=p[i]
                p[i]=p[j]
                p[j]=aux
    for i in range(0,len(p)):
        print(p[i])

def incr(d,l):
    nr=int(l[2])
    for i in range(0,len(d)):
        if d[i][2]==l[1]:
            d[i][1]=d[i][1]+nr
    
