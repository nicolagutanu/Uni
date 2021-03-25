from command import add_comm, delete_comm, incr_comm, show_comm
from ui import sort

def init_list():
    d=[["OB3002",45,"London","Paris"],["OB3001",70,"Budapest","Bucharest"],["OB6009",60,"Chicago","LA"],["WA908",100,"Bucharest","Milano"],["BA789",80,"Athens","Paris"]]
    return d

def menu():
    print("MENU:")
    print("1. add - the application will add a new flight to the list")
    print("2. delete - the application will delete a given flight from the list")
    print("3. show - the application will show all flights with a given departure city")
    print("4. sort - the application will sort the flights by their destination city")
    print("5. increase - the application will increase the duration of a flight")

def start():
    d=init_list()
    while True:
        menu()
        b=input("Please enter your command: ")
        l=b.split()
        if l[0]=="add":
            add_comm(d,l)
        elif l[0]=="delete":
            delete_comm(d,l)
        elif l[0]=="show":
            show_comm(d,l)
        elif l[0]=="sort":
            sort(d)
        elif l[0]=="increase":
            incr_comm(d,l)
        else:
            print("invalid command")

start()
