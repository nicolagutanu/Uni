

class Console(object):

    def __ui_print(self, params):
        print(self.__repo_srv.get_player())

    def __ui_place_ast(self, params):
        print(self.__repo_srv.place_ast(self.__repo_srv.get_alien(), self.__repo_srv.get_player()))

    def __ui_place_ali(self, params):
        self.__repo_srv.place_ali(self.__repo_srv.get_alien())

    def __ui_cheat(self, params):
        print(self.__repo_srv.get_alien())

    def __ui_play(self, params):
        print(self.__repo_srv.play(self.__repo_srv.get_alien(), self.__repo_srv.get_player(), params[0]))
        self.__repo_srv.get_closer(self.__repo_srv.get_alien())
        self.__repo_srv.close_to_earth(self.__repo_srv.get_alien())

    def __ui_start(self, params):
        self.__repo_srv.place_ast(self.__repo_srv.get_alien(), self.__repo_srv.get_player())
        self.__repo_srv.place_ali(self.__repo_srv.get_alien())
        print(self.__repo_srv.get_player())


    def __init__(self, repo_srv):
        self.__repo_srv = repo_srv
        self.__commands = {
            "print_board": self.__ui_print,
            "place_asteroids": self.__ui_place_ast,
            "place_aliens": self.__ui_place_ali,
            "cheat": self.__ui_cheat,
            "fire": self.__ui_play,
            "start": self.__ui_start
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
            if name_cmd in self.__commands:
                try:
                    self.__commands[name_cmd](params)
                except Exception as ex:
                    print(str(ex))
            else:
                print("inaliv command\n")