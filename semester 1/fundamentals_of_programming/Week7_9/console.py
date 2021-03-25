from exceptions import ValidError, RepoError, UndoStackError


class Console(object):

    def __ui_add_movie(self, params):
        if len(params) != 4:
            raise ValueError("it requires exactly 4 params!")
        movie_id = int(params[0])
        title = params[1]
        desc = params[2]
        genre = params[3]
        self.__serviceMovie.add_movie(movie_id, title, desc, genre)

    def __ui_remove_movie(self, params):
        if len(params) != 4:
            raise ValueError("it requires exactly 4 params")
        movie_id = int(params[0])
        title = params[1]
        desc = params[2]
        genre = params[3]
        self.__serviceRental.delete_movie_and_rentals(movie_id)

    def __ui_update_movie(self, params):
        if len(params) != 4:
            raise ValueError("it requires exactly 4 params")
        movie_id = int(params[0])
        title = params[1]
        desc = params[2]
        genre = params[3]
        self.__serviceMovie.update_movie(movie_id, title, desc, genre)

    def __ui_print_movie(self, params):
        movies = self.__serviceMovie.get_movies()
        for movie in movies:
            print(movie)

    def __ui_search_m_id(self, params):
        self.__serviceMovie.get_movie_by_id(int(params[0]))

    def __ui_search_m_t(self, params):
        title = params[0]
        self.__serviceMovie.search_by_title(title)

    def __ui_search_m_d(self, params):
        desc = params[0]
        self.__serviceMovie.search_by_desc(desc)

    def __ui_search_m_g(self, params):
        genre = params[0]
        self.__serviceMovie.search_by_genre(genre)

    def __ui_stat_movies(self, params):
        l = self.__serviceRental.famous_movies()
        for i in l:
            print(i[0], " ", i[1])

    def __ui_add_client(self, params):
        if len(params) != 2:
            raise ValueError("it requires exactly 2 params!")
        client_id = int(params[0])
        if params[1].find("_") != -1:
            x = params[1].split("_")
            name = x[0] + " " + x[1]
        else:
            name = params[1]
        self.__serviceClient.add_client(client_id, name)

    def __ui_remove_client(self, params):
        if len(params) != 2:
            raise ValueError("it requires exactly 2 params!")
        client_id = int(params[0])
        if params[1].find("_") != -1:
            x = params[1].split("_")
            name = x[0] + " " + x[1]
        else:
            name = params[1]
        self.__serviceClient.remove_client(client_id, name)

    def __ui_update_client(self, params):
        if len(params) != 2:
            raise ValueError("it requires exactly 2 params!")
        client_id = int(params[0])
        if params[1].find("_") != -1:
            x = params[1].split("_")
            name = x[0] + " " + x[1]
        else:
            name = params[1]
        self.__serviceClient.update_client(client_id, name)

    def __ui_print_client(self, params):
        clients = self.__serviceClient.get_clients()
        for client in clients:
            print(client)

    def __ui_search_c_id(self, params):
        self.__serviceClient.get_client_by_id(params)

    def __ui_search_c_name(self, params):
        if params[1].find("_") != -1:
            x = params[1].split("_")
            name = x[0] + " " + x[1]
        else:
            name = params[1]
        self.__serviceClient.search_by_name(name)

    def __ui_stat_clients(self, params):
        l = self.__serviceRental.famous_clients()
        for i in l:
            print(i[0], " ", i[1])

    def __ui_add_rental(self, params):
        if len(params) != 6:
            raise ValueError("it requires exactly 6 params!")
        rid = int(params[0])
        mid = int(params[1])
        cid = int(params[2])
        rented_date = params[3]
        due_date = params[4]
        returned_date = params[5]
        self.__serviceRental.add_rental(rid, mid, cid, rented_date, due_date, returned_date)

    def __ui_return(self, params):
        if len(params) != 6:
            raise ValueError("it requires exactly 6 params!")
        rid = int(params[0])
        mid = int(params[1])
        cid = int(params[2])
        rented_date = params[3]
        due_date = params[4]
        returned_date = params[5]
        self.__serviceRental.update_rental(rid, mid, cid, rented_date, due_date, returned_date)

    def __ui_print_rental(self, params):
        rentals = self.__serviceRental.get_rentals()
        for rental in rentals:
            print(rental)

    def __ui_stat_rentals(self, params):
        l = self.__serviceRental.late_movies()
        for i in l:
            print(i[0], " " , i[1])

    def __ui_undo(self, params):
        self.__serviceUndoRedo.undo()

    def  __ui_redo(self, params):
        self.__serviceUndoRedo.redo()

    def __init__(self, serviceMovie, serviceClient, serviceRental, serviceUndoRedo):
        self.__serviceMovie = serviceMovie
        self.__serviceClient = serviceClient
        self.__serviceRental = serviceRental
        self.__serviceUndoRedo = serviceUndoRedo
        self.__commands = {
            "add_movie": self.__ui_add_movie,
            "remove_movie": self.__ui_remove_movie,
            "update_movie": self.__ui_update_movie,
            "print_movie": self.__ui_print_movie,
            "search_movie_by_id": self.__ui_search_m_id,
            "search_movie_by_title": self.__ui_search_m_t,
            "search_movie_by_desc": self.__ui_search_m_d,
            "search_movie_by_genre": self.__ui_search_m_g,
            "stat_movies": self.__ui_stat_movies,
            "add_client": self.__ui_add_client,
            "remove_client": self.__ui_remove_client,
            "update_client": self.__ui_update_client,
            "print_client": self.__ui_print_client,
            "search_client_by_id": self.__ui_search_c_id,
            "search_client_by_name":self.__ui_search_c_name,
            "stat_clients": self.__ui_stat_clients,
            "rent": self.__ui_add_rental,
            "return": self.__ui_return,
            "print_rental": self.__ui_print_rental,
            "stat_rentals": self.__ui_stat_rentals,
            "undo": self.__ui_undo,
            "redo": self.__ui_redo
        }

    def run(self):
        while True:
            cmd = input(">>>")
            if cmd == "exit":
                return
            cmd = cmd.strip()
            if cmd == "":
                continue
            parts = cmd.split()
            name_cmd = parts[0]
            params = parts[1:]
            if name_cmd in self.__commands:
                try:
                    self.__commands[name_cmd](params)
                except ValueError as ve:
                    print("UI Error:\n" + str(ve))
                except ValidError as vale:
                    print("UI Error:\n" + str(vale))
                except RepoError as re:
                    print("UI Error:\n" + str(re))
                except UndoStackError as use:
                    print("Undo Error:\n" + str(use))
            else:
                print("invalid command!\n")

