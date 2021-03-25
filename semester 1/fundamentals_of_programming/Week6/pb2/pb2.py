from domain import *
from service import *

class UI:
    def __init__(self, service):
        self._service=service


    def test_add(l):
        if l[1].isdigit()!=True or int(l[1])<1 or int(l[1])>30:
            raise ValueError("Not a valid day")
        if l[2].isdigit()==False or int(l[2])<0:
            raise ValueError("Not a valid value")
        if type(l[3])!=str:
            raise ValueError("Not a valid category")

    def test_filter(l):
        if l[1].isdigit()==False or int(l[1])<0:
            raise ValueError("Not a valid value")

    def test_undo(s):
        if s==[]:
            raise ValueError("You have retured to the original list")


    def add_serv(exps,l):
        try:
            test_add(l)
            add_exp(exps,l)
        except ValueError as exception:
            print(exception.args[0])
        
        
    def filter_serv(exps,l):
        try:
            test_filter(l)
            filter_exp(exps,l)
        except ValueError as exception:
            print(exception.args[0])

    def undo_serv(exps,s):
        try:
            test_undo(s)
            undo_exp(exps,s)
        except ValueError as exception:
            print(exception.args[0])

    def menu():
        print("MENU:")
        print("add - Add a new expense into the list")
        print("print - Write the expenses having different properties")
        print("filter - Keep expenses by given condition")
        print("undo - Returns the previous list")

    def cop_list(a,exps):
        for i in range(0,len(exps)):
            a.append(exps[i])

    def call_menu(self):
        exps=self._service.init_list()
        u=[]
        while True:
            UI.menu()
            button=input("Please introduce your command: ")
            l=button.split()
            try:
                if l[0]=='add':
                    a=[]
                    UI.cop_list(a,exps)
                    u.append(a)
                    self._service.add_exp(l)
                elif l[0]=='print':
                    self._service.print_exp()
                elif l[0]=='filter':
                    a=[]
                    UI.cop_list(a,exps)
                    u.append(a)
                    self._service.filter_exp(l)
                elif l[0]=='undo':
                    self._service.undo_exp(u)
            except NameError:
                print('Incorrect command')

s = service()
ui=UI(s)
ui.call_menu()





