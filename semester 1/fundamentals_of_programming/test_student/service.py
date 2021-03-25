from entities import *
import random


class ServiceStudent(object):

    def __init__(self, repoStud, repoGrade, valStud, valGrade):
        self.__repoStud = repoStud
        self.__repoGrade = repoGrade
        self.__valStud = valStud
        self.__valGrade = valGrade

    def add_student(self, id, name, group):
        stud = Student(id, name, group)
        self.__valStud.validate_student(stud)
        self.__repoStud.add(stud)

    def remove_stud(self, id):
        stud = Student(id, None, None)
        #self.__valStud.validate_student(stud)
        grade = Grade(id, None, None, None)
        if grade in self.__repoGrade.get_all():
            raise ValueError("cannot remove the student because it has grades\n")
        self.__repoStud.remove(stud)

    def assg_lab(self, id, lab_no, lab_pb):
        grades = self.__repoGrade.get_all()
        for g in grades:
            if g.get_stud_id() == id:
                if g.get_lab_no == lab_no:
                    raise ValueError("cannot assign another problem from the same lab number\n")
        lab = Grade(id, lab_no, lab_pb, 0)
        #self.__valGrade.validate_grade(lab)
        self.__repoGrade.add(lab)

    def assg_for_group(self, group, lab_no):
        studs = self.__repoStud.get_all()
        l = self.__repoStud.size()
        for stud in studs:
            if stud.get_group() == group:
                lab_pb = random.randint(1, l)
                lab = Grade(stud.get_id(), lab_no, lab_pb, None)
                #self.__valGrade.validate_grade(lab)
                #if lab not in self.__repoGrade.get_all():
                self.__repoGrade.add(lab)

    def get_all(self):
        return self.__repoStud.get_all()


class ServiceGrade(object):

    def __init__(self, repoGrade, repoStud, valGrade):
        self.__repoGrade = repoGrade
        self.__valGrade = valGrade
        self.__repoStud = repoStud

    def grade_stud(self, stud_id, lab_no, val):
        grades = self.__repoGrade.get_all()
        for g in grades:
            if g.get_stud_id() == stud_id and g.get_lab_no() == lab_no:
                grade = Grade(stud_id, lab_no, g.get_lab_pb(), val)
                #self.__valGrade.validate_grade(grade)
                self.__repoGrade.update(grade)

    def best_worst(self, group):
        studs = self.__repoStud.get_all()
        s = []
        for stud in studs:
            if stud.get_group() == group:
                s.append([stud, 0])
        for i in range(len(s)):
            g = 0
            nr = 0
            for grade in self.__repoGrade.get_all():
                if grade.get_stud_id() == s[i][0].get_id():
                    g = g + grade.get_val()
                    nr = nr + 1
            g = g // nr
            s[i][1] = g
        s.sort(key=lambda x: x[1], reverse = True)
        return s[:]

    def failing(self):
        studs = self.__repoStud.get_all()
        grades = self.__repoGrade.get_all()
        f = []
        for s in studs:
            for grade in grades:
                if grade.get_stud_id() == s.get_id():
                    if grade.get_val() < 5:
                        f.append(s)
        return f[:]

    def get_all(self):
        return self.__repoGrade.get_all()