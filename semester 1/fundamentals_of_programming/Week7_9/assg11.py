

class Hope:

    def __init__(self, lis, index):
        self.__lis = lis
        self.__index = index

    def __setitem__(self, key, value):
        self.__lis[key] = value
        return self.__lis

    def __delitem__(self, key):
        del self.__lis[key]

    def __iter__(self):
        self.__index = 0
        return self

    def __getitem__(self, item):
        return self.__lis[item]

    def __next__(self):
        try:
            item = self.__lis[self.__index]
        except IndexError:
            return
        self.__index += 1
        return item


    def shell_sort(self, lis):
        self.__lis = lis
        n = len(self.__lis)
        gap = int(n / 2)
        while gap > 0:
            for i in range(int(gap), n):
                copy = self.__lis[i]
                j = i
                while int(j) >= int(gap) and self.__lis[int(j) - int(gap)] > copy:
                    self.__lis[int(j)] = self.__lis[int(j) - int(gap)]
                    j = j - gap
                self.__lis[int(j)] = copy
            gap = int(gap / 2)
        return self.__lis[:]

    '''
    def select_sort(self, list):
        self.__list = list
        max = len(self.__list)
        nextValue = self.__list[max-1]
        for i in range(max-2, -1, -1):
            if self.__list[i] > nextValue:
                nextValue = self.__list[i]
        while max > -1 and self.__list[max-1] == nextValue:
            max -= 1
        while max > -1:
            value = nextValue
            nextValue = self.__list[max-1]
            for i in range(max-2, -1, -1):
                if self.__list == value:
                    aux = self.__list[i]
                    self.__list[i] = self.__list[max-1]
                    self.__list[max-1] = aux
                    max = max - 1
                elif self.__list[i] > nextValue:
                    nextValue = self.__list[i]
            while max > -1 and self.__list[max-1] == nextValue:
                max = max - 1
        return self.__list[:]
    '''

    def filter(self, lis, value):
        self.__iter__()
        self.__lis = lis
        while self.__index < len(self.__lis):
            x = self.__next__()
            if str(x).lower().find(str(value).lower()) != -1:
                pass
            else:
                self.__index -= 1
                self.__delitem__(self.__index)
        return self.__lis[:]