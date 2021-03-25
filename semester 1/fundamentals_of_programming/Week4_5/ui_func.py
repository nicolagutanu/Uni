import datetime
def list_command(d,l):
    if len(l)==1: #prints the list 
        for i in range(0,len(d)):
            print(d[i])
    if len(l)==2: #looks for the list elements that are equal to the given expense
        j=0
        for i in range(0,len(d)):
            if d[i][2]==l[1]:
                print(d[i])
                j=j+1
    if len(l)==4: #checks the comparison sign and prints accordingly
        nr=int(l[3])
        if l[2]=='>':
            for i in range(0,len(d)):
                if d[i][2]==l[1] and d[i][1]>nr:
                    print(d[i])
        elif l[2]=='=':
            for i in range(0,len(d)):
                if d[i][2]==l[1] and d[i][1]==nr:
                    print(d[i])
        elif l[2]=='<':
            for i in range(0,len(d)):
                if d[i][2]==l[1] and d[i][1]<nr:
                    print(d[i])


def add_command(d,l): #adds the expense with its value to the current day
    x=datetime.datetime.now()
    d.append([x.day,int(l[1]),l[2]])

def insert_command(d,l): #adds the expense with its value to the give day
    d.append([int(l[1]),int(l[2]), l[3]])


def remove_command(d,l): #prints according to the given instruction
    if len(l)==2 and l[1].isdigit():
        nr=int(l[1])
        i=0
        while i<=len(d)-1:
            if d[i][0]==nr:
                d.remove(d[i])
            i=i+1
    if len(l)==2:
        if l[1]=='housekeeping' or l[1]=='food' or l[1]=='transport' or l[1]=='clothing' or l[1]=='internet' or l[1]=='others':
            i=0
            while i<=len(d)-1:
                if d[i][2]==l[1]:
                    d.remove(d[i])
                i=i+1
    if len(l)==4:
        nr1=int(l[1])
        nr2=int(l[3])
        for i in range(nr1,nr2+1):
            j=0
            while j<=len(d)-1:
                if d[j][0]==i:
                    d.remove(d[j])
                j=j+1

def sum_command(d,l): #calculates the sum of a given category
    s=l[1]
    e=0
    for i in range(0,len(d)):
        if d[i][2]==s:
            e=e+d[i][1]
    print(e)

def max_command(d,l): #finds the day with the total maximum expenses
    max=0
    for i in range(1,30):
        s=0
        for j in range(0,len(d)):
            if d[j][0]==i:
                s=s+d[j][1]
        if s>max:
            day=i
            max=s
    print(day)

def sort_command(d,l):
    if l[1]=='day':
        c=[] 
        for i in range(1, 31):
            s=0
            for j in range(0, len(d)):
                if d[j][0]==i:
                    s=s+d[j][1]
            if s!=0:
                c.append([i,s]) #calculates the daily expenses for each day existing in the list and puts them in a new list 
        for i in range(0, len(c)-1):
            for j in range(i+1, len(c)):
                if c[i][1]>c[j][1]:
                    a=c[i]
                    c[i]=c[j]
                    c[j]=a   #sorts the new list in ascending order and prints it
        for i in range(0, len(c)):
                print(c[i])
    else:
        c=[]
        for i in range(0, len(d)): #all expenses for the given category are put into a new list
            if d[i][2]==l[1]:
                c.append(d[i])
        for i in range(0, len(c)-1):
                for j in range(i+1, len(c)):
                    if c[i][1]>c[j][1]:
                        a=c[i]
                        c[i]=c[j]
                        c[j]=a  #sorts the new list in ascending order and prints it
        for i in range(0,len(c)):
            print(c[i])


def filter_command(d,l):
    if len(l)==2: #prints the expenses of the given category
        i=0
        print(len(d))
        while i<len(d):
            if d[i][2]==l[1]:
                i=i+1
            elif d[i][2]!=l[1]:
                d.remove(d[i])
    if len(l)==4: #checks the comparison signs and prints accordingly
        if l[2]=='<':
            i=0
            nr=int(l[3])
            while i<len(d):
                if d[i][2]==l[1]:
                    if d[i][1]>=nr:
                        d.remove(d[i])
                    else:
                        i=i+1
                if d[i][2]!=l[1]:
                    d.remove(d[i])   #prints expenses in the given category with the amount of money less than the value given in the instruction 
        if l[2]=='=':
            i=0
            nr=int(l[3])
            while i<len(d):
                if d[i][2]==l[1]:
                    if d[i][1]!=nr:
                        d.remove(d[i])
                    else:
                        i=i+1
                if d[i][2]!=l[1]:
                    d.remove(d[i])  #prints expenses in the given category with the amount of money equal to the value given in the instruction
        if l[2]=='>':
            i=0
            nr=int(l[3])
            while i<len(d):
                if d[i][2]==l[1]:
                    if d[i][1]<=nr:
                        d.remove(d[i])
                    else:
                        i=i+1
                if d[i][2]!=l[1]:
                    d.remove(d[i])   #prints expenses in the given category with the amount of money greater than the value given in the instruction

def undo_command(s,d): #deletes the values of the list and gives the list its previous values 
    x = s.pop()
    d.clear()
    d.extend(x)
