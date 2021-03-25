import unittest
from entities import *
from services import *
from repos import *



class Tests(unittest.TestCase):

    def test_movie(self):
        movie = Movie(1, "unu", "un", "drama")
        self.assertEqual(movie.get_movie_id(), 1)
        self.assertEqual(movie.get_title(), "unu")
        self.assertEqual(movie.get_desc(), "un")
        self.assertEqual(movie.get_genre(), "drama")

    def test_client(self):
        client = Client(1, "ana")
        self.assertEqual(client.get_client_id(), 1)
        self.assertEqual(client.get_name(), "ana")

    def test_rental(self):
        rental = Rental(1, 3, 2, "12.03", "19.03", "18.03")
        self.assertEqual(rental.get_rid(), 1)
        self.assertEqual(rental.get_mid(), 3)
        self.assertEqual(rental.get_cid(), 2)
        self.assertEqual(rental.get_rented_date(), "12.03")
        self.assertEqual(rental.get_due_date(), "19.03")
        self.assertEqual(rental.get_returned_date(), "18.03")

    def test_addm(self):
        self.__srv = ServiceMovie()
        self.__srv.add_movie(1, "unu", "movie", "drama")
        x = self.__srv.get_movies()
        self.assertEqual(x[0].get_movie_id(), 1)
        self.assertEqual(x[0].get_title(), "unu")
        self.assertEqual(x[0].get_desc(), "movie")
        self.assertEqual(x[0].get_genre, "drama")

    def test_addc(self):
        self.__srv = ServiceClient()
        self.__srv.add_client(1, "ana")
        x = self.__srv.get_clients()
        self.assertEqual(x[0].get_client_id(), 1)
        self.assertEqual(x[0].get_name(), "ana")

    def test_removem(self):
        Movie.movie_id = 0
        self.__srv = ServiceMovie()
        self.__srv.get_no_movie()
        self.__srv.remove_movie(3, None, None, None)
        c = 0
        x = self.__srv.get_movies()
        for i in x:
            if i.get_movie_id() == 3:
                c = 1
        self.assertEqual(c, 0)

    def test_removec(self):
        Client.client_id = 0
        self.__srv = ServiceClient()
        self.__srv.get_no_client()
        self.__srv.remove_client(3, None)
        c = 0
        x = self.__srv.get_clients()
        for i in x:
            if i.get_client_id() == 3:
                c = 1
        self.assertEqual(c, 0)

    def test_updatem(self):
        Movie.movie_id = 0
        self.__srv = ServiceMovie()
        self.__srv.get_no_movie()
        self.__srv.update_movie(1, "doi", "mov", "comedy")
        x = self.__srv.get_movies()
        for i in x:
            if i.get_movie_id() == 1:
                self.assertEqual(i.get_title(), "doi")
                self.assertEqual(i.get_desc(), "mov")
                self.assertEqual(i.get_genre(), "comedy")

    def test_updatec(self):
        Client.client_id = 0
        self.__srv = ServiceClient()
        self.__srv.get_no_client()
        self.__srv.update_client(1, "andreea")
        x = self.__srv.get_clients()
        for i in x:
            if i.get_client_id() == 1:
                self.assertEqual(i.get_name(), "andreea")

if __name__ == '__main__':

    try:
        unittest.main()
    except Exception:
        pass