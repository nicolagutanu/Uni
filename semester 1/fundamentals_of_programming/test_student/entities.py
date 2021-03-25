

class Student(object):

    def __init__(self, id, name, group):
        self.__id = id
        self.__name = name
        self.__group = group

    def get_id(self):
        return self.__id

    def get_name(self):
        return self.__name

    def get_group(self):
        return self.__group

    def set_name(self, value):
        self.__name = value

    def set_group(self, value):
        self.__group = value

    def __eq__(self, other):
        return self.__id == other.__id

    def __str__(self):
        return str(self.__id) + ". " + str(self.__name) + ", group: " + str(self.__group)

    @staticmethod
    def read_object(line):
        parts = line.split(",")
        return Student(int(parts[0].strip()), parts[1].strip(), parts[2].strip())

    @staticmethod
    def write_object(student):
        return str(student.__id) + "," + student.__name + "," + student.__group


class Grade(object):

    def __init__(self, stud_id, lab_no, lab_pb, val):
        self.__stud_id = stud_id
        self.__lab_no = lab_no
        self.__lab_pb = lab_pb
        self.__val = val

    def get_stud_id(self):
        return self.__stud_id

    def get_lab_no(self):
        return self.__lab_no

    def get_lab_pb(self):
        return self.__lab_pb

    def get_val(self):
        return self.__val

    def set_lab_no(self, value):
        self.__lab_no = value

    def set_lab_pb(self, value):
        self.__lab_pb = value

    def set_val(self, value):
        self.__val = value

    def __eq__(self, other):
        return self.__stud_id == other.__stud_id

    def __str__(self):
        return str(self.__stud_id) + ". " + str(self.__lab_no) + ", " + str(self.__lab_pb) + ", " + str(self.__val)

    @staticmethod
    def read_object(line):
        parts = line.split(",")
        return Grade(int(parts[0].strip()), int(parts[1].strip()), int(parts[2].strip()), int(parts[3].strip()))

    @staticmethod
    def write_object(grade):
        return str(grade.__stud_id) + "," + str(grade.__lab_no) + "," + str(grade.__lab_pb) + "," + str(grade.__val)