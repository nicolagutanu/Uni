import random
from domain import *
class service:
    def __init__(self):
        self._d=[]

    def init_list(self):
        exp_type=['food','internet','housekeeping','others','clothing']
        for i in range(1,11):
            day=random.randint(1,30)
            money=random.randint(0,200)
            expNr=random.randint(0,4)
            expType=exp_type[expNr]
            self._d.append(Expense(day, money, expType))
        return list(self._d)

    def add_exp(self,l):
        x=Expense(int(l[1]),int(l[2]),l[3])
        self._d.append(x)

    def filter_exp(self,l):
        nr=int(l[1])
        i=0
        while i<len(self._d):
            if self._d[i].get_money()<=nr:
                self._d.remove(self._d[i])
            else:
                i=i+1

    def undo_exp(self,u):
        x = u.pop()
        self._d.clear()
        self._d.extend(x)

    def print_exp(self):
        for i in range(0,len(self._d)):
            print(self._d[i])
