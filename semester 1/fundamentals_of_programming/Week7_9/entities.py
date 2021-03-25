class Movie(object):
    def __init__(self, movie_id, title, desc, genre):
        self.__movie_id = movie_id
        self.__title = title
        self.__desc = desc
        self.__genre = genre

    def get_movie_id(self):
        return self.__movie_id

    def get_title(self):
        return self.__title

    def get_desc(self):
        return self.__desc

    def get_genre(self):
        return self.__genre

    def set_title(self, value):
        self.__title = value

    def set_desc(self, value):
        self.__desc = value

    def set_genre(self, value):
        self.__genre = value

    def __eq__(self, other):
        return self.__movie_id == other.__movie_id

    def __str__(self):
        return str(self.__movie_id) + ". " + self.__title + ", " + self.__desc + ", " + self.__genre

    @staticmethod
    def read_movie(line):
        parts = line.split(",")
        return Movie(int(parts[0].strip()), parts[1].strip(), parts[2].strip(), parts[3].strip())

    @staticmethod
    def write_movie(movie):
        return str(movie.__movie_id) + "," + movie.__title + "," + movie.__desc + "," + movie.__genre


class Client(object):
    def __init__(self, client_id, name):
        self.__client_id = client_id
        self.__name = name

    def get_client_id(self):
        return self.__client_id

    def get_name(self):
        return self.__name

    def set_name(self, value):
        self.__name = value

    def __eq__(self, other):
        return self.__client_id == other.__client_id

    def __str__(self):
        return str(self.__client_id) + ". " + self.__name

    @staticmethod
    def read_client(line):
        parts = line.split(",")
        return Client(int(parts[0].strip()), parts[1].strip())

    @staticmethod
    def write_client(client):
        return str(client.__client_id) + "," + client.__name


class Rental(object):
    def __init__(self, rid, mid, cid, rented_date, due_date, returned_date):
        self.__rid = rid
        self.__mid = mid
        self.__cid = cid
        self.__rented_date = rented_date
        self.__due_date = due_date
        self.__returned_date = returned_date

    def get_rid(self):
        return self.__rid

    def get_mid(self):
        return self.__mid

    def get_cid(self):
        return self.__cid

    def get_rented_date(self):
        return self.__rented_date

    def get_due_date(self):
        return self.__due_date

    def get_returned_date(self):
        return self.__returned_date

    def set_rented_date(self, rented_date):
        self.__rented_date = rented_date

    def set_due_date(self, due_date):
        self.__due_date = due_date

    def set_returned_date(self, returned_date):
        self.__returned_date = returned_date

    def __eq__(self, other):
        return self.__mid == other.__mid and self.__cid == other.__cid and self.__rid == other.__rid

    def __str__(self):
        return str(self.__rid) + ". " + str(self.__mid) + ", " + str \
            (self.__cid) + ", " + self.__rented_date + ", " + self.__due_date + ", " + self.__returned_date

    @staticmethod
    def read_rental(line):
        parts = line.split(",")
        return Rental(int(parts[0].strip()), int(parts[1].strip()), int(parts[2].strip()), parts[3].strip(),
                      parts[4].strip(), parts[5].strip())

    @staticmethod
    def write_rental(rental):
        return str(rental.__rid) + "," + str(rental.__mid) + "," + str \
            (rental.__cid) + "," + rental.__rented_date + "," + rental.__due_date + "," + rental.__returned_date


class UndoAction:

    def __init__(self, action, rev_action, obj):
        self.__action = action
        self.__rev_action = rev_action
        self.__obj = obj

    def execute(self):
        self.__action(self.__obj)

    def get_reverse(self):
        return UndoAction(self.__rev_action, self.__action, self.__obj)


class UndoActionUpdate:

    def __init__(self, action, obj, rev_obj):
        self.__action = action
        self.__obj = obj
        self.__rev_obj = rev_obj

    def execute(self):
        self.__action(self.__rev_obj)

    def get_reverse(self):
        return UndoActionUpdate(self.__action, self.__rev_obj, self.__obj)


class ComplexUndoAction(UndoAction):

    def __init__(self):
        self.__undoActions = []

    def add_action(self, action):
        self.__undoActions.append(action)

    def execute(self):
        for i in range(len(self.__undoActions) - 1, -1, -1):
            self.__undoActions[i].execute()

    def get_reverse(self):
        rez = ComplexUndoAction()
        for i in range(len(self.__undoActions) - 1, -1, -1):
            rez.__undoActions.append(self.__undoActions[i].get_reverse())
        return rez
