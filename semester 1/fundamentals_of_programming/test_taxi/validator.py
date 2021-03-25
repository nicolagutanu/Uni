

class Validator(object):

    def __init__(self):
        pass

    def val_order(self, order):
        errors = ""
        if order.get_dist() < 1:
            errors += "invalid_dist\n"