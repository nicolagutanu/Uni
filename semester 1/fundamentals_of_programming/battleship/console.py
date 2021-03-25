

class Console(object):

    def __ui_place_ship(self, params):
        for i in range(2):
            print("Choose your battleship:")
            c1 = input(">>>")
            r1 = int(input(">>>"))
            c2 = input(">>>")
            r2 = int(input(">>>"))
            c3 = input(">>>")
            r3 = int(input(">>>"))
            self.__repo_srv.place_ship_player(self.__repo_srv.get_player(), c1, r1, c2, r2, c3, r3)
            print(self.__repo_srv.get_player())

    def __ui_start(self, params):
        self.__repo_srv.place_ship_computer(self.__repo_srv.get_player())

    def __ui_attack(self, params):
        while True:
            try:
                c = str(input(">>>"))
                r = int(input(">>>"))
                print("attack " + c + str(r) + "\n")
                print(self.__repo_srv.player_attack(self.__repo_srv.get_player(), self.__repo_srv.get_table(), c, r))
                if self.__repo_srv.check_player_win() == True:
                    print("player won\n")
                    return
                self.__repo_srv.comp_attack(self.__repo_srv.get_player())
                if self.__repo_srv.check_comp_win():
                    print("computer won\n")
                    return
            except Exception as ex:
                print(str(ex))

    def __ui_cheat(self, params):
        print(self.__repo_srv.get_player())

    def __init__(self, repo_srv):
        self.__repo_srv = repo_srv
        self.__commands = {
            "ship": self.__ui_place_ship,
            "start": self.__ui_start,
            "attack": self.__ui_attack,
            "cheat": self.__ui_cheat
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
                    print("UI Error" + str(ex))
            else:
                print("ivalid command\n")