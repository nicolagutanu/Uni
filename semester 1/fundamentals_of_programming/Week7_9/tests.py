from entities import Movie
from validators import ValidatorMovie
from exceptions import RepoError, ValidError
from repos import RepoMovie, StackUndoActions
from services import ServiceMovie
from assg11 import *


class Tests(object):

    def __init__(self):
        pass

    def __test_create_movie(self):
        movie_id = 1
        title = "The_Notebook"
        desc = "two_people_fall_in_love"
        genre = "romance"
        movie = Movie(movie_id, title, desc, genre)
        assert(movie.get_movie_id() == 1)
        assert(movie.get_title() == "The_Notebook")
        assert(movie.get_desc() == "two_people_fall_in_love")
        assert(movie.get_genre() == "romance")
        movie.set_title("Love,_Rosie")
        assert(movie.get_title() == "Love,_Rosie")
        movie.set_desc("love")
        assert(movie.get_desc() == "love")
        movie.set_genre("drama")
        assert(movie.get_genre() == "drama")
        self.__movie = movie
        self.__dif_movie_same_id = Movie(1, "Fast_and_Furious", "cars", "action")
        assert(self.__movie == self.__dif_movie_same_id)

    def __test_validate_movie(self):
        validatorMovie = ValidatorMovie()
        validatorMovie.validate_movie(self.__movie)
        self.__movie_bad_id = Movie(-7, "The_Notebook", "two_people_fall_in_love", "romance")
        self.__movie_bad_title = Movie(7, "", "two_people_fall_in_love", "romance")
        self.__movie_bad_desc = Movie(7, "The_Notebook", "", "romance")
        self.__movie_bad_genre = Movie(7, "The_Notebook", "two_people_fall_in_love", "")
        self.__movie_bad = Movie(-7, "", "", "")
        try:
            validatorMovie.validate_movie(self.__movie_bad_id)
            assert False
        except Exception as ex:
            assert(str(ex) == "invalid id\n")
        try:
            validatorMovie.validate_movie(self.__movie_bad_title)
            assert False
        except Exception as ex:
            assert(str(ex) == "invalid title\n")
        try:
            validatorMovie.validate_movie(self.__movie_bad_desc)
            assert False
        except Exception as ex:
            assert(str(ex) == "invalid desc\n")
        try:
            validatorMovie.validate_movie(self.__movie_bad_genre)
            assert False
        except Exception as ex:
            assert(str(ex) == "invalid genre\n")
        try:
            validatorMovie.validate_movie(self.__movie_bad)
            assert False
        except Exception as ex:
            assert(str(ex) == "invalid id\ninvalid title\ninvalid desc\ninvalid genre\n")
        self.__validMovie = validatorMovie

    def __test_repo_movie_add_search(self):
        repo = RepoMovie()
        assert(repo.size() == 0)
        repo.add(self.__movie)
        assert(repo.size() == 1)
        foundMovie = repo.search_by_id(self.__movie.get_movie_id())
        assert(foundMovie.get_movie_id() == self.__movie.get_movie_id())
        try:
            repo.add(self.__dif_movie_same_id)
            assert False
        except RepoError as re:
            assert(str(re) == "existing id\n")
        self.__inexisting_movie = Movie(2, "Midway", "WW2", "action")
        try:
            repo.search(self.__inexisting_movie)
            assert False
        except RepoError as re:
            assert (str(re) == "inexisting id\n")
        movies = repo.get_all()
        assert(movies == [self.__movie])
        self.__repo = repo

    def __test_srv_movie_add_search(self):
        repo = RepoMovie()
        undoActions = StackUndoActions()
        redoActions = StackUndoActions()
        list = []
        index = 0
        assg1 = Hope(list, index)
        servMovies = ServiceMovie(repo, self.__validMovie, undoActions, redoActions, assg1)
        assert(servMovies.get_no_movie() == 0)
        movie_id = 1
        title = "The_Notebook"
        desc = "two_people_fall_in_love"
        genre = "romance"
        servMovies.add_movie(movie_id, title, desc, genre)
        assert(servMovies.get_no_movie() == 1)
        foundMovie = servMovies.get_movie_by_id(1)
        assert(foundMovie.get_title() == "The_Notebook")
        try:
            servMovies.add_movie(1, "Fast_and_Furious", "cars", "action")
            assert False
        except RepoError as re:
            assert(str(re) == "existing id\n")
        try:
            servMovies.get_movie_by_id(10)
            assert False
        except RepoError as re:
            assert(str(re) == "inexisting id\n")
        try:
            servMovies.add_movie(-2, "Fas_and_Furious", "cars", "romance")
            assert False
        except ValidError as re:
            assert(str(re) == "invalid id\n")
        try:
            servMovies.add_movie(1, "", "cars", "action")
            assert False
        except ValidError as re:
            assert (str(re) == "invalid title\n")
        try:
            servMovies.add_movie(1, "Fast_and_Furious", "", "action")
            assert False
        except ValidError as re:
            assert (str(re) == "invalid desc\n")
        try:
            servMovies.add_movie(1, "Fast_and_Furious", "cars", "")
            assert False
        except ValidError as re:
            assert (str(re) == "invalid genre\n")
        try:
            servMovies.add_movie(-1, "", "", "")
            assert False
        except ValidError as re:
            assert (str(re) == "invalid id\ninvalid title\ninvalid desc\ninvalid genre\n")
        movies = servMovies.get_movies()
        assert(movies == [self.__movie])

    def run_all_tests(self):
        self.__test_create_movie()
        self.__test_validate_movie()
        self.__test_repo_movie_add_search()
        self.__test_srv_movie_add_search()


