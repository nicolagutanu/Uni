from tests import test_list, test_add, test_remove, test_sum, test_max, test_sort, test_filter
from ui_func import list_command, add_command, insert_command, remove_command, sum_command, max_command, sort_command, filter_command, undo_command
def print_list(d,l): #tests the given instruction for errors and prints if everything is ok
    try:
        test_list(l)
        list_command(d,l)
    except ValueError as exception:
        print(exception.args[0])
    
def add_list(d,l): #tests the given instruction for errors and prints if everything is ok       
    try:
        test_add(l)
        if len(l)==3:
            add_command(d,l)
        else:
            insert_command(d,l)
    except ValueError as exception:
        print(exception.args[0])
        

def remove_list(d,l): #tests the given instruction for errors and prints if everything is ok
    try:
        test_remove(l)
        remove_command(d,l)
    except ValueError as exception:
        print(exception.args[0])

def sum_list(d,l): #tests the given instruction for errors and does the sum if everything is ok
    try:
        test_sum(l)
        sum_command(d,l)
    except ValueError as exception:
        print(exception.args[0])

def max_list(d,l): #tests the given instruction for errors and calculates the maximum if everything is ok
    try:
        test_max(l)
        max_command(d,l)
    except ValueError as exception:
        print(exception.args[0])

def sort_list(d,l): #tests the given instruction for errors and sorts if everything is ok
    try:
        test_sort(l)
        sort_command(d,l)
    except ValueError as exception:
        print(exception.args[0])

def undo_list(s,d): #tests the given instruction for errors and reverses the last operation if everything is ok
    if s==[]:
        print("You have returned to the original list")
    else:
        undo_command(s,d)

def filter_list(d,l): #tests the given instruction for errors and prints accordingly if everything is ok
    try:
        test_filter(l)
        filter_command(d,l)
    except ValueError as exception:
        print(exception.args[0])



