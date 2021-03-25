

class Console(object):

    def __init__(self, repo_srv):
        self.__repo_srv = repo_srv

    def print_table(self):
        print(' ')
        print('-------------------------------------')
        print(self.__repo_srv.get_tabla())
        print('-------------------------------------')
        print(' ')

    def run(self):
        while True:
            try:
                print('Choose a column between 1 and 7')
                c = int(input('>>>'))
                if c > 7 or c < 1:
                    raise Exception('Wrong input')
                self.__repo_srv.add_player(self.__repo_srv.get_tabla(), c-1)
                self.print_table()
                if self.__repo_srv.check_win(self.__repo_srv.get_tabla(), 'P'):
                    print('Player has won')
                    return
                elif self.__repo_srv.full_tabla(self.__repo_srv.get_tabla()):
                    print('Draw')
                    return
                self.__repo_srv.comp_moves(self.__repo_srv.get_tabla(), c-1)
                self.print_table()
                if self.__repo_srv.check_win(self.__repo_srv.get_tabla(), 'C'):
                    print('Computer has won')
                    return
                elif self.__repo_srv.full_tabla(self.__repo_srv.get_tabla()):
                    print('Draw')
                    return
            except Exception as ex:
                print(str(ex))

