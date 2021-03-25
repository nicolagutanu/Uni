

class FileRepoStudent(object):

    def __init__(self, file_name, read_obj, write_obj):
        self.__file_name = file_name
        self.__read_obj = read_obj
        self.__write_obj = write_obj

    def __read_all_from_file(self):
        self.__entities = []
        with open(self.__file_name, "r") as f:
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if line != "":
                    obj = self.__read_obj(line)
                    self.__entities.append(obj)

    def __write_all_to_file(self):
        with open(self.__file_name, "w") as f:
            for obj in self.__entities:
                line = self.__write_obj(obj)
                f.write(line + "\n")

    def add(self, stud):
        self.__read_all_from_file()
        if stud in self.__entities:
            raise ValueError("existing id\n")
        self.__entities.append(stud)
        self.__write_all_to_file()

    def remove(self, stud):
        self.__read_all_from_file()
        if stud not in self.__entities:
            raise ValueError("inexisting id\n")
        self.__entities.remove(stud)
        self.__write_all_to_file()

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)

class FileRepoGrade(object):

    def __init__(self, file_name, read_obj, write_obj):
        self.__file_name = file_name
        self.__read_obj = read_obj
        self.__write_obj = write_obj

    def __read_all_from_file(self):
        self.__entities = []
        with open(self.__file_name, "r") as f:
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if line != "":
                    obj = self.__read_obj(line)
                    self.__entities.append(obj)

    def __write_all_to_file(self):
        with open(self.__file_name, "w") as f:
            for obj in self.__entities:
                line = self.__write_obj(obj)
                f.write(line + "\n")

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)

    def add(self, lab):
        self.__read_all_from_file()
        '''
        if lab in self.__entities:
            raise ValueError("student already has a lab problem\n")
        '''
        self.__entities.append(lab)
        self.__write_all_to_file()

    def update(self, grade):
        self.__read_all_from_file()
        if grade not in self.__entities:
            raise ValueError("inexisting grade\n")
        for i, x in enumerate(self.__entities):
            if x == grade:
                self.__entities[i] = grade
        self.__write_all_to_file()

    def search_by_id(self, id):
        self.__read_all_from_file()
        for grade in self.__entities:
            if grade.get_stud_id() == id:
                return grade
        raise ValueError("inexisting id\n")
