def test_list(l): #tests to see if the list instruction is valid
    if len(l)==1 and l[0]!='list': #checks to see if it starts with 'list'
        raise ValueError("Not a valid list instruction")    
    if len(l)==2: #checks that the second word is an expense
        if l[1]!='housekeeping' and l[1]!='food' and l[1]!='transport' and l[1]!='clothing' and l[1]!='internet' and l[1]!='others':
            raise ValueError("Not a valid category")
    if len(l)==4:
        if l[1]!='housekeeping' and l[1]!='food' and l[1]!='transport' and l[1]!='clothing' and l[1]!='internet' and l[1]!='others':
            raise ValueError("Not a valid category")
        if l[2]!='>' and l[2]!='=' and l[2]!='<':
            raise ValueError("Not a valid comparison sign")
        if l[3].isdigit()!=True and int(l[3])<0: #checks to see if the fourth word is a number and is positive
            raise ValueError("Not a valid value")

def test_add(l):
    if len(l)==3: #checks that the add instruction is correct
        if l[0]=='add' and l[1].isdigit()!=True and int(l[1])<0:
            raise ValueError("Not a valid value")
        if l[2]!='housekeeping' and l[2]!='food' and l[2]!='transport' and l[2]!='clothing' and l[2]!='internet' and l[2]!='others':
            raise ValueError("Not a valid category")
    if len(l)==4: #checks that the insert instruction is correct
        if l[0]=='insert' and l[1].isdigit()!=True or int(l[1])<1 or int(l[1])>30:
            raise ValueError("Not a valid day")
        if l[2].isdigit()==False or int(l[2])<0:
            raise ValueError("Not a valid value")
        if l[3]!='housekeeping' and l[3]!='food' and l[3]!='transport' and l[3]!='clothing' and l[3]!='internet' and l[3]!='others':
            raise ValueError("Not a valid category")


def test_remove(l):
    if len(l)==2: #checks that the given day exists
        if l[1].isdigit()==False or int(l[1])<1 or int(l[1])>30 and l[1]!='housekeeping' and l[1]!='food' and l[1]!='transport' and l[1]!='clothing' and l[1]!='internet' and l[1]!='others':
            raise ValueError("Not a valid remove instruction")
    if len(l)==4: #checks that the given days exist
        if l[1].isdigit()==False or int(l[1])<1 or int(l[1])>30:  
            raise ValueError("Not a valid day")
        if l[2]!='to' or int(l[1])>int(l[3]):
            raise ValueError("Not a valid remove instruction")
        if l[3].isdigit()==False or int(l[3])<1 or int(l[3])>30 :
            raise ValueError("Not a valid second day")

def test_sum(l): #checks if the given expense is a valid one
    if l[1]!='housekeeping' and l[1]!='food' and l[1]!='transport' and l[1]!='clothing' and l[1]!='internet' and l[1]!='others':
        raise ValueError("Not a valid category")        

def test_max(l): #checks if the given instruction is correct
    if l[1]!='day':
        raise ValueError("Not a valid max instruction")

def test_sort(l): 
    #checks if the given instruction is correct
    #checks if the given expense is a valid one
    if l[1]!='day' and l[1]!='housekeeping' and l[1]!='food' and l[1]!='transport' and l[1]!='clothing' and l[1]!='internet' and l[1]!='others':
        raise ValueError("Not a valid sort instruction")

def test_filter(l):
    if len(l)==2:  #checks if the given expense is a valid one
        if l[1]!='housekeeping' and l[1]!='food' and l[1]!='transport' and l[1]!='clothing' and l[1]!='internet' and l[1]!='others':
            raise ValueError("Not a valid category")
    if len(l)==4:  #checks if the given expense is a valid one
        if l[1]!='housekeeping' and l[1]!='food' and l[1]!='transport' and l[1]!='clothing' and l[1]!='internet' and l[1]!='others':
            raise ValueError("Not a valid category")
        if l[2]!='<' and l[2]!='>' and l[2]!='=':#checks to see if the third word is a comparison sign
            raise ValueError("Not a valid comparison sign")
        if l[3].isdigit()==False or int(l[3])<0: #checks to see if the fourth word is a number and is greater than 0
            raise ValueError("Not a valid value")




    
