

class ValidatorStud(object):

    def __init__(self):
        pass

    def validate_student(self, stud):
        errors = ""
        if stud.get_id() < 1:
            errors += "invalid id\n"
        if stud.get_name() == "":
            errors += "invalid name\n"
        if stud.get_group() == "":
            errors += "invalid group\n"
        if len(errors) != 0:
            raise ValueError(errors)


class ValidatorGrade(object):

    def __init__(self):
        pass

    def validate_grade(self, grade):
        errors = ""
        if grade.get_stud_id() < 1:
            errors += "invalid id\n"
        if grade.get_lab_no() < 1:
            errors += "invalid lab number\n"
        if grade.get_lab_pb() < 1:
            errors += "invalid lab problem\n"
        if grade.get_val() < 0 or grade.get_val() > 10:
            errors += "invalid grade\n"
        if len(errors) != 0:
            raise ValueError(errors)
