class Expense:
    def __init__(self,day,money,type):
        self._day=day
        self._money=money
        self._type=type

    def get_day(self):
        return self._day

    def get_money(self):
        return self._money

    def get_type(self):
        return self._type

    def set_day(self,day):
        self._day=day

    def set_money(self,money):
        self._money=money

    def set_type(self,type):
        self._type=type

    def __str__(self):
        return "("+str(self.get_day())+","+str(self.get_money())+","+str(self.get_type())+")"

def test_exp():
    e=Expense(10,100,'food')
    assert e.get_day()==10
    assert e.get_money()==100 
    assert e.get_type()=='food'

test_exp()
