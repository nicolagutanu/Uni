

class Console:

    def __ui_add_stud(self, params):
        if len(params) != 3:
            raise ValueError("it requires exactly 3 params\n")
        id = int(params[0])
        name = params[1]
        group = params[2]
        self.__servStud.add_student(id, name, group)

    def __ui_remove_stud(self, params):
        if len(params) != 1:
            raise ValueError("it requires exactly 1 params\n")
        id = int(params[0])
        self.__servStud.remove_stud(id)

    def __ui_assg_lab(self, params):
        if len(params) != 3:
            raise ValueError("it requires exactly 3 params\n")
        id = int(params[0])
        lab_no = int(params[1])
        lab_pb = int(params[2])
        self.__servStud.assg_lab(id, lab_no, lab_pb)

    def __ui_assg_group(self, params):
        if len(params) != 2:
            raise ValueError("it requires exactly 2 params\n")
        group = params[0]
        lab_no = int(params[1])
        self.__servStud.assg_for_group(group, lab_no)

    def __ui_grade_stud(self, params):
        if len(params) != 3:
            raise ValueError("it requires exactly 3 params\n")
        stud_id = int(params[0])
        lab_no = int(params[1])
        val = int(params[2])
        self.__servGrade.grade_stud(stud_id, lab_no, val)

    def __ui_best_worst(self, params):
        if len(params) != 1:
            raise ValueError("it requires exactly 1 params\n")
        group = params[0]
        studs = self.__servGrade.best_worst(group)
        for stud in studs:
            print(stud[0])

    def __ui_failing(self, params):
        studs = self.__servGrade.failing()
        for stud in studs:
            print(stud)

    def __ui_print_grades(self, params):
        grades = self.__servGrade.get_all()
        for grade in grades:
            print(grade)

    def __ui_print_studs(self, params):
        studs = self.__servStud.get_all()
        for stud in studs:
            print(stud)

    def __init__(self, servStud, servGrade):
        self.__servStud = servStud
        self.__servGrade = servGrade
        self.__command = {
            "add_student" : self.__ui_add_stud,
            "remove_student": self.__ui_remove_stud,
            "assign_lab": self.__ui_assg_lab,
            "assign_to_group": self.__ui_assg_group,
            "grade_student": self.__ui_grade_stud,
            "print_group": self.__ui_best_worst,
            "failing_students": self.__ui_failing,
            "print_grades": self.__ui_print_grades,
            "print_students": self.__ui_print_studs
        }

    def run(self):
        while True:
            cmd = input(">>>")
            if cmd == "exit":
                return
            cmd = cmd.strip()
            parts = cmd.split()
            name_cmd = parts[0]
            params = parts[1:]
            if name_cmd in self.__command:
                try:
                    self.__command[name_cmd](params)
                except ValueError as ex:
                    print("UI Error: " + str(ex))
            else:
                print("invalid command\n")