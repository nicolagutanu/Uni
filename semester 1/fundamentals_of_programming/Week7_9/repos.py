from exceptions import RepoError, UndoStackError
import pickle
from assg11 import *


class RepoMovie(object):

    def __init__(self):
        self.__entities = []

    def size(self):
        return len(self.__entities)

    def add(self, movie):
        if movie in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(movie)

    def search(self, keyMovie):
        if keyMovie not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == keyMovie:
                return x

    def search_by_id(self, key):
        for x in self.__entities:
            if x.get_movie_id() == key:
                return x
        raise RepoError("inexisting id\n")

    def get_all(self):
        return self.__entities[:]

    def remove(self, movie):
        if movie not in self.__entities:
            raise RepoError("inexisting movie\n")
        self.__entities.remove(movie)

    def update(self, movie):
        if movie not in self.__entities:
            raise RepoError("inexisting movie\n")
        for i, x in enumerate(self.__entities):
            if x == movie:
                self.__entities[i] = movie
                return


class RepoClient(object):

    def __init__(self):
        self.__entities = []

    def size(self):
        return len(self.__entities)

    def add(self, client):
        if client in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(client)

    def search(self, keyClient):
        if keyClient not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == keyClient:
                return x

    def get_all(self):
        return self.__entities[:]

    def remove(self, client):
        if client not in self.__entities:
            raise RepoError("inexisting client\n")
        self.__entities.remove(client)

    def update(self, client):
        if client not in self.__entities:
            raise RepoError("inexisting client\n")
        for i, x in enumerate(self.__entities):
            if x == client:
                self.__entities[i] = client
                return


class RepoRental(object):

    def __init__(self):
        self.__entities = []

    def size(self):
        return len(self.__entities)

    def add(self, rental):
        if rental in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(rental)

    def search(self, keyRental):
        if keyRental not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == keyRental:
                return x

    def get_all(self):
        return self.__entities[:]

    def remove(self, rental):
        if rental not in self.__entities:
            raise RepoError("inexisting rental\n")
        self.__entities.remove(rental)

    def update(self, rental):
        if rental not in self.__entities:
            raise RepoError("inexisting rental\n")
        for i, x in enumerate(self.__entities):
            if x == rental:
                self.__entities[i] = rental
                return


class StackUndoActions(object):

    def __init__(self):
        self.__undoActions = []

    def push(self, action):
        self.__undoActions.append(action)

    def pop(self):
        if len(self.__undoActions) == 0:
            raise UndoStackError("You cannot do undo")
        return self.__undoActions.pop()

    def clear(self):
        self.__undoActions = []


class FileRepoMovie(RepoMovie):

    def __init__(self, filename, read_object, write_object):
        RepoMovie.__init__(self)
        self.__filename = filename
        self.__read_object = read_object
        self.__write_object = write_object

    def __read_all_from_file(self):
        self.__entities = []
        with open(self.__filename, "r") as f:
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if line != "":
                    obj = self.__read_object(line)
                    self.__entities.append(obj)

    def __write_all_to_file(self):
        with open(self.__filename, "w") as f:
            for obj in self.__entities:
                line = self.__write_object(obj)
                f.write(line + "\n")

    def add(self, entity):
        self.__read_all_from_file()
        if entity in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(entity)
        self.__write_all_to_file()

    def search(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == entity:
                return x

    def search_by_id(self, entity):
        self.__read_all_from_file()
        for x in self.__entities:
            if x.get_movie_id() == entity:
                return x
        raise RepoError("inexisting id\n")

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def remove(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting movie\n")
        self.__entities.remove(entity)
        self.__write_all_to_file()

    def update(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting movie\n")
        for i, x in enumerate(self.__entities):
            if x == entity:
                self.__entities[i] = entity
                return
        self.__write_all_to_file()

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)


class FileRepoClient(RepoClient):

    def __init__(self, filename, read_object, write_object):
        RepoClient.__init__(self)
        self.__filename = filename
        self.__read_object = read_object
        self.__write_object = write_object

    def __read_all_from_file(self):
        self.__entities = []
        with open(self.__filename, "r") as f:
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if line != "":
                    obj = self.__read_object(line)
                    self.__entities.append(obj)

    def __write_all_to_file(self):
        with open(self.__filename, "w") as f:
            for obj in self.__entities:
                line = self.__write_object(obj)
                f.write(line + "\n")

    def add(self, entity):
        self.__read_all_from_file()
        if entity in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(entity)
        self.__write_all_to_file()

    def search(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == entity:
                return x

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def remove(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting client\n")
        self.__entities.remove(entity)
        self.__write_all_to_file()

    def update(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting client\n")
        for i, x in enumerate(self.__entities):
            if x == entity:
                self.__entities[i] = entity
                return
        self.__write_all_to_file()

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)


class FileRepoRental(RepoRental):

    def __init__(self, filename, read_object, write_object):
        RepoRental.__init__(self)
        self.__filename = filename
        self.__read_object = read_object
        self.__write_object = write_object

    def __read_all_from_file(self):
        self.__entities = []
        with open(self.__filename, "r") as f:
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if line != "":
                    obj = self.__read_object(line)
                    self.__entities.append(obj)

    def __write_all_to_file(self):
        with open(self.__filename, "w") as f:
            for obj in self.__entities:
                line = self.__write_object(obj)
                f.write(line + "\n")

    def add(self, entity):
        self.__read_all_from_file()
        if entity in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(entity)
        self.__write_all_to_file()

    def search(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == entity:
                return x

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def remove(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting rental\n")
        self.__entities.remove(entity)
        self.__write_all_to_file()

    def update(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting rental\n")
        for i, x in enumerate(self.__entities):
            if x == entity:
                self.__entities[i] = entity
                return
        self.__write_all_to_file()

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)

    @property
    def entities(self):
        return self.__entities


class PickleRepoMovie(RepoMovie):

    def __init__(self, filename):
        RepoMovie.__init__(self)
        self.__filename = filename

    def __read_all_from_file(self):
        self.__entities = []
        try:
            with open(self.__filename, "rb") as f:
                self.__entities = pickle.load(f)
        except:
            self.__entities = []

    def __write_all_to_file(self):
        with open(self.__filename, "wb") as f:
            pickle.dump(self.__entities, f)
            f.close()

    def add(self, entity):
        self.__read_all_from_file()
        if entity in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(entity)
        self.__write_all_to_file()

    def search(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == entity:
                return x

    def search_by_id(self, entity):
        self.__read_all_from_file()
        for x in self.__entities:
            if x.get_movie_id() == entity:
                return x
        raise RepoError("inexisting id\n")

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def remove(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting movie\n")
        self.__entities.remove(entity)
        self.__write_all_to_file()

    def update(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting movie\n")
        for i, x in enumerate(self.__entities):
            if x == entity:
                self.__entities[i] = entity
                return
        self.__write_all_to_file()

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)


class PickleRepoClient(RepoClient):

    def __init__(self, filename):
        RepoClient.__init__(self)
        self.__filename = filename

    def __read_all_from_file(self):
        self.__entities = []
        with open(self.__filename, "rb") as f:
            self.__entities = pickle.load(f)
            f.close()

    def __write_all_to_file(self):
        with open(self.__filename, "wb") as f:
            pickle.dump(self.__entities, f)
            f.close()

    def add(self, entity):
        self.__read_all_from_file()
        if entity in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(entity)
        self.__write_all_to_file()

    def search(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == entity:
                return x

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def remove(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting client\n")
        self.__entities.remove(entity)
        self.__write_all_to_file()

    def update(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting client\n")
        for i, x in enumerate(self.__entities):
            if x == entity:
                self.__entities[i] = entity
                return
        self.__write_all_to_file()

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)


class PickleRepoRental(RepoRental):

    def __init__(self, filename):
        RepoRental.__init__(self)
        self.__filename = filename

    def __read_all_from_file(self):
        self.__entities = []
        with open(self.__filename, "rb") as f:
            self.__entities = pickle.load(f)
            f.close()

    def __write_all_to_file(self):
        with open(self.__filename, "wb") as f:
            pickle.dump(self.__entities, f)
            f.close()

    def add(self, entity):
        self.__read_all_from_file()
        if entity in self.__entities:
            raise RepoError("existing id\n")
        self.__entities.append(entity)
        self.__write_all_to_file()

    def search(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting id\n")
        for x in self.__entities:
            if x == entity:
                return x

    def get_all(self):
        self.__read_all_from_file()
        return self.__entities[:]

    def remove(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting rental\n")
        self.__entities.remove(entity)
        self.__write_all_to_file()

    def update(self, entity):
        self.__read_all_from_file()
        if entity not in self.__entities:
            raise RepoError("inexisting rental\n")
        for i, x in enumerate(self.__entities):
            if x == entity:
                self.__entities[i] = entity
                return
        self.__write_all_to_file()

    def size(self):
        self.__read_all_from_file()
        return len(self.__entities)
