from tests import Tests
from validators import ValidatorMovie, ValidatorClient, ValidatorRental
from repos import RepoMovie, RepoClient, RepoRental, StackUndoActions, FileRepoClient, FileRepoMovie, FileRepoRental, PickleRepoClient, PickleRepoMovie, PickleRepoRental
from services import ServiceMovie, ServiceClient, ServiceRental, ServiceUndoRedo
from console import Console
from entities import Movie, Client, Rental
import random
from assg11 import *
tests = Tests()
tests.run_all_tests()

validatorMovie = ValidatorMovie()
validatorClient = ValidatorClient()
validatorRental = ValidatorRental()

print("Settings: \n"
      "1. access inmemmory based data\n"
      "2. access textfile based data\n"
      "3. access binaryfile based data\n")
setting = int(input(">>>"))
if setting == 1:
    repoMovie = RepoMovie()
    repoMovie.add(Movie(1, "The_Notebook", "two_people_fall_in_love", "romance"))
    repoMovie.add(Movie(2, "Fast_and_Furious", "cars", "action"))
    repoMovie.add(Movie(3, "Midway", "ww2", "action"))
    repoMovie.add(Movie(4, "Charlie's_Angels", "girl_spies", "action"))
    repoClient = RepoClient()
    repoClient.add(Client(1, "Luke Skywalker"))
    repoClient.add(Client(2, "Leia Princess"))
    repoClient.add(Client(3, "Darth Vader"))
    repoRental = RepoRental()
    for i in range(1, 11):
        rid = i
        mid = random.randint(1, repoMovie.size())
        cid = random.randint(1, repoClient.size())
        day = random.randint(1, 30)
        month = random.randint(1, 12)
        rented_date = str(day) + "." + str(month)
        day2 = random.randint(day, 30)
        due_date = str(day2) + "." + str(month)
        day3 = random.randint(day, day2)
        returned_date = str(day3) + "." + str(month)
        repoRental.add(Rental(rid, mid, cid, rented_date, due_date, returned_date))
elif setting == 2:
    repoMovie = FileRepoMovie('movies', Movie.read_movie, Movie.write_movie)
    repoClient = FileRepoClient('clients', Client.read_client, Client.write_client)
    repoRental = FileRepoRental('rentals', Rental.read_rental, Rental.write_rental)

else:
    repoMovie = PickleRepoMovie('moviepickle')
    repoClient = PickleRepoClient('clientpickle')
    repoRental = PickleRepoRental('rentalpickle')


undoActions = StackUndoActions()
redoActions = StackUndoActions()
list = []
index = 0
assg1 = Hope(list, index)

repoUndo = RepoMovie()
serviceMovie = ServiceMovie(repoMovie, validatorMovie, undoActions, redoActions, assg1)
serviceClient = ServiceClient(repoClient, validatorClient, undoActions, redoActions)
serviceRental = ServiceRental(repoMovie, repoClient, repoRental, validatorRental, undoActions, redoActions)
serviceUndoRedo = ServiceUndoRedo(undoActions, redoActions)
ui = Console(serviceMovie, serviceClient, serviceRental, serviceUndoRedo)
ui.run()

#validator, repo, service, ui = console(service)