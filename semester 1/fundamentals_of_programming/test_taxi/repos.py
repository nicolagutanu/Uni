

class Repo(object):

    def __init__(self, file_name, read_object):
        self.__entities = []
        self.__file_name = file_name
        self.__read_object = read_object

    def add(self, obj):  #add an object
        self.__entities.append(obj)

    def get_all(self):  #get the whole list of objects
        return self.__entities[:]

    def search(self, key):  #search for a certain object in the list
        if key not in self.__entities:
            raise ValueError("inexisting id\n")
        for i in self.__entities:
            if key == i:
                return i

    def read_all_from_file(self):
        self.__entities = []
        with open(self.__file_name, "r") as f:
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if line != "":
                    obj = self.__read_object(line)
                    self.__entities.append(obj)

