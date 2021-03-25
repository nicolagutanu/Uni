from entities import Movie, Client, Rental, UndoAction, ComplexUndoAction, UndoActionUpdate
from datetime import *
from re import search
from exceptions import *
from assg11 import *


class ServiceMovie(object):

    def __init__(self, repoMovie, validatorMovie, undoActions, redoActions, assg1):
        self.__repoMovie = repoMovie
        self.__validatorMovie = validatorMovie
        self.__undoActions = undoActions
        self.__redoActions = redoActions
        self.__assg1 = assg1

    def add_movie(self, movie_id, title, desc, genre):
        movie = Movie(movie_id, title, desc, genre)
        self.__validatorMovie.validate_movie(movie)
        self.__repoMovie.add(movie)
        action = UndoAction(self.__repoMovie.remove, self.__repoMovie.add, movie)
        self.__undoActions.push(action)
        self.__redoActions.clear()

    def get_movies(self):
        return self.__repoMovie.get_all()

    def get_no_movie(self):
        return self.__repoMovie.size()

    def get_movie_by_id(self, movie_id):
        return self.__repoMovie.search_by_id(movie_id)

    def search_by_title(self, key):
        movies = self.__repoMovie.get_all()
        list_of_t = []
        for m in movies:
            list_of_t.append(m.get_title())
        list_of_t = self.__assg1.filter(list_of_t, key)
        movies_filtered = []
        for i in range(0, len(list_of_t)):
            for mo in movies:
                if str(mo.get_title()) == str(list_of_t[i]):
                    movies_filtered.append(mo)
        for i in range(0,len(movies_filtered)):
            print(movies_filtered[i])
        return movies_filtered[:]

    def search_by_desc(self, key):
        c = 1
        key = key.lower()
        for i in self.__repoMovie.get_all():
            de = i.get_name().lower()
            if search(key, de):
                print(i)
                c = 0
        if c == 1:
            raise ValidError("inexisting movie\n")

    def search_by_genre(self, key):
        c = 1
        key = key.lower()
        for i in self.__repoMovie.get_all():
            ge = i.get_name().lower()
            if search(key, ge):
                print(i)
                c = 0
        if c == 1:
            raise ValidError("inexisting movie\n")

    def remove_movie(self, movie_id, title, desc, genre):
        movie = Movie(movie_id, title, desc, genre)
        self.__validatorMovie.validate_movie(movie)
        self.__repoMovie.remove(movie)
        action = UndoAction(self.__repoMovie.add, self.__repoMovie.remove, movie)
        self.__undoActions.push(action)
        self.__redoActions.clear()

    def update_movie(self, movie_id, title, desc, genre):
        movie2 = self.__repoMovie.search_by_id(movie_id)
        movie = Movie(movie_id, title, desc, genre)
        self.__validatorMovie.validate_movie(movie)
        self.__repoMovie.update(movie)
        print(movie)
        print(movie2)
        action = UndoActionUpdate(self.__repoMovie.update, movie, movie2)
        self.__undoActions.push(action)
        self.__redoActions.clear()


class ServiceClient(object):

    def __init__(self, repoClient, validatorClient, undoActions, redoActions):
        self.__repoClient = repoClient
        self.__validatorClient = validatorClient
        self.__undoActions = undoActions
        self.__redoActions = redoActions

    def add_client(self, client_id, name):
        client = Client(client_id, name)
        self.__validatorClient.validate_client(client)
        self.__repoClient.add(client)
        action = UndoAction(self.__repoClient.remove, self.__repoClient.add, client)
        self.__undoActions.push(action)
        self.__redoActions.clear()

    def get_clients(self):
        return self.__repoClient.get_all()

    def get_no_client(self):
        return self.__repoClient.size()

    def get_client_by_id(self, client_id):
        key = Client(client_id, None)
        return self.__repoClient.search(key)

    def search_by_name(self, key):
        c = 1
        key = key.lower()
        for i in self.__repoClient.get_all():
            na = i.get_name().lower()
            if search(key, na):
                print(i)
                c = 0
        if c == 1:
            raise ValidError("inexisting client\n")

    def remove_client(self, client_id, name):
        client = Client(client_id, name)
        self.__validatorClient.validate_client(client)
        self.__repoClient.remove(client)
        action = UndoAction(self.__repoClient.add, self.__repoClient.remove, client)
        self.__undoActions.push(action)
        self.__redoActions.clear()

    def update_client(self, client_id, name):
        client = Client(client_id, name)
        self.__validatorClient.validate_client(client)
        self.__repoClient.update(client)


class ServiceRental(object):

    def __init__(self, repoMovie, repoClient, repoRental, validatorRental, undoActions, redoActions):
        self.__repoMovie = repoMovie
        self.__repoClient = repoClient
        self.__repoRental = repoRental
        self.__validatorRental = validatorRental
        self.__undoActions = undoActions
        self.__redoActions = redoActions

    def add_rental(self, rid, mid, cid, rented_date, due_date, returned_date):
        rental = Rental(rid, mid, cid, rented_date, due_date, returned_date)
        # self.__repoMovie.search(rental.get_mid())
        # self.__repoClient.search(rental.get_cid())
        self.__validatorRental.validate_rental(rental)
        self.__repoRental.add(rental)
        action = UndoAction(self.__repoRental.remove, self.__repoRental.add, rental)
        self.__undoActions.push(action)
        self.__redoActions.clear()

    def delete_movie_and_rentals(self, movie_id):
        movie = self.__repoMovie.search_by_id(movie_id)
        action = ComplexUndoAction()
        for rental in self.__repoRental.get_all():
            if rental.get_mid() == movie.get_movie_id():
                self.__repoRental.remove(rental)
                action.add_action(UndoAction(self.__repoRental.add, self.__repoRental.remove, rental))
        self.__repoMovie.remove(movie)
        action.add_action(UndoAction(self.__repoMovie.add, self.__repoMovie.remove, movie))
        self.__undoActions.push(action)
        self.__redoActions.clear()

    def get_rentals(self):
        return self.__repoRental.get_all()

    def update_rental(self, rid, mid, cid, rented_date, due_date, returned_date):
        rental = Rental(rid, mid, cid, rented_date, due_date, returned_date)
        self.__validatorRental.validate_rental(rental)
        self.__repoRental.update(rental)


    def famous_movies(self):
        l = []
        for i in self.__repoMovie.get_all():
            l.append([i.get_movie_id(), 0])
        rentals = self.__repoRental.get_all()
        for rental in rentals:
            id = rental.get_mid()
            if rental.get_returned_date() == "-":
                a = rental.get_rented_date()
                a = a + ".2019"
                a = datetime.strptime(a, "%d.%m.%Y").date()
                b = date.today()
                nr = (b - a).days
            else:
                a = rental.get_rented_date()
                a = datetime.strptime(a, "%d.%m").date()
                b = rental.get_returned_date()
                b = datetime.strptime(b, "%d.%m").date()
                nr = (b - a).days
            for i in l:
                if i[0] == id:
                    i[1] += nr
        l.sort(key=lambda x: x[1], reverse=True)
        return l

    def famous_clients(self):
        l = []
        for i in self.__repoClient.get_all():
            l.append([i.get_client_id(), 0])
        rentals = self.__repoRental.get_all()
        for rental in rentals:
            id = rental.get_cid()
            if rental.get_returned_date() == "-":
                a = rental.get_rented_date()
                a = a + ".2019"
                a = datetime.strptime(a, "%d.%m.%Y").date()
                b = date.today()
                nr = (b - a).days
            else:
                a = rental.get_rented_date()
                a = datetime.strptime(a, "%d.%m").date()
                b = rental.get_returned_date()
                b = datetime.strptime(b, "%d.%m").date()
                nr = (b - a).days
            for i in l:
                if i[0] == id:
                    i[1] += nr
        l.sort(key=lambda x: x[1], reverse=True)
        return l

    def late_movies(self):
        l = []
        rentals = self.__repoRental.get_all()
        for rental in rentals:
            if rental.get_returned_date() == "-":
                a = rental.get_rented_date()
                a = a + ".2019"
                a = datetime.strptime(a, "%d.%m.%Y").date()
                b = date.today()
                nr = (b - a).days
                l.append([rental.get_mid(), nr])
        l.sort(key=lambda x: x[1], reverse=True)
        return l


class ServiceUndoRedo(object):

    def __init__(self, undoActions, redoActions):
        self.__undoActions = undoActions
        self.__redoActions = redoActions

    def undo(self):
        action = self.__undoActions.pop()
        action.execute()
        rev_action = action.get_reverse()
        self.__redoActions.push(rev_action)

    def redo(self):
        action = self.__redoActions.pop()
        action.execute()
        rev_action = action.get_reverse()
        self.__undoActions.push(rev_action)
