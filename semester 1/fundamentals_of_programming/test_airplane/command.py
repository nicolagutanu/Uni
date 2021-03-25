from ui import add, delete, show, incr
from tests import test_add, test_delete, test_show, test_incr

def add_comm(d,l):
    #function that checks to see that all conditions are met and that adds a new
    #flight to the list, raises an error otherwise
    try:
        test_add(l)
        add(d,l)
    except ValueError as ex:
        print(ex.args[0])

def delete_comm(d,l):
    #function that checks to see that all conditions are met and that deletes a
    #given flight, raises an error otherwise
    try:
        test_delete(d,l)
        delete(d,l)
    except ValueError as ex:
        print(ex.args[0])

def show_comm(d,l):
    #function that checks to see that all conditions are met and that prints
    #the flights with the given condition, raises error otherwise
    try:
        test_show(d,l)
        show(d,l)
    except ValueError as ex:
        print(ex.args[0])

def incr_comm(d,l):
    #function that checks to see that all conditions are met and that changes
    #the duration of a flight, raises error otherwise
    try:
        test_incr(l)
        incr(d,l)
    except ValueError as ex:
        print(ex.args[0])
