from commands import print_list, add_list, remove_list, sum_list, max_list, sort_list, filter_list, undo_list
from ui_func import undo_command    
def menu():
    print("MENU")
    print("add/insert - Add a new expense into the list")
    print("remove - Modify expenses from the list")
    print("list - Write the expenses having different properties")
    print("sum - write the total expense for category food")
    print("max - write the day with the maximum expenses")
    print("sort - sort expenses by given instruction")
    print("filter - – keep expenses by given condition")
    print("exit - Terminate the program")

def init_list(): 
    d=[[2,15,"housekeeping"],[15,5,"internet"],[5,50,"others"],[22,100,"food"],[28,100,"clothing"],[30,25,"transport"],[10,150,"food"],[16,15,"housekeeping"],[10,5,"internet"],[25,10,"others"]]
    return d

def cop_list(a,d): #function that copies a list
    for i in range(0,len(d)):
        a.append(d[i])
    

def call_menu(): #function that recognizes the given instruction and reacts accordingly
    d=init_list()
    s=[] #for undo, we will have a list that holds all the previous lists
    while True:
        menu()
        button = input("Please enter your chosen instruction: ")
        l=button.split()
        if l[0]=='list':
            print_list(d,l)
        elif l[0]=='add' or l[0]=='insert':
            a=[]
            cop_list(a,d)
            s.append(a)
            add_list(d,l)
        elif l[0]=='remove':
            a=[]
            cop_list(a,d)
            s.append(a)
            remove_list(d,l)
        elif l[0]=='sum':
            sum_list(d,l)
        elif l[0]=='max':
            max_list(d,l)
        elif l[0]=='sort':
            sort_list(d,l)
        elif l[0]=='filter':
            a=[]
            cop_list(a,d)
            s.append(a)
            filter_list(d,l)
        elif l[0]=='undo':
            undo_list(s,d)
        elif l[0]=='exit':
            exit()
        else:
            print("Invalid instruction")


      
if __name__=="__main__":
    call_menu()
