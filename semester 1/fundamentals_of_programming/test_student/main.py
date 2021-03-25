from validator import *
from entities import *
from console import *
from repo import *
from service import *

validatorStudent = ValidatorStud()
validatorGrade = ValidatorGrade()

repoStudent = FileRepoStudent("students", Student.read_object, Student.write_object)
repoGrade = FileRepoGrade("grades", Grade.read_object, Grade.write_object)

serviceStudent = ServiceStudent(repoStudent, repoGrade, validatorStudent, validatorGrade)
serviceGrade = ServiceGrade(repoGrade, repoStudent, validatorGrade)
ui = Console(serviceStudent, serviceGrade)
ui.run()