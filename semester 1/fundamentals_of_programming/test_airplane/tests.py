def test_add(l):
    #function that checks to see if the code, duration, departure city and
    #destination city are within the given parameters
    if len(l)!=5:
        raise ValueError("Not a valid command")
    if len(l[1])<3:
        raise ValueError("Not a valid code")
    if len(l[3])<3:
        raise ValueError("Not a valid departure city")
    if len(l[4])<3:
        raise ValueError("Not a valid destination city")
    if int(l[2])<20:
        raise ValueError("Not a valid duration")
    

def test_delete(d,l):
    #function that checks that the given flight exists in the list
    t=0
    for i in range(0,len(d)):
        if d[i][0]==l[1]:
            t=1
    if t==0:
        raise ValueError("This flight doesn't exist")

def test_show(d,l):
    #function that checks that the given departure city exists
    t=0
    for i in range(0,len(d)):
        if d[i][2]==l[1]:
            t=1
    if t==0:
        raise ValueError("This departure city doesn't exist")

def test_incr(l):
    #function that checks that the given number of minutes is in range [10,60]
    if int(l[2])<10 or int(l[2])>60:
        raise ValueError("The input number is not within the given range")
