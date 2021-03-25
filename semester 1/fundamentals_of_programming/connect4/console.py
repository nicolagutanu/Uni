

class Console(object):
    def __init__(self, srv_repo):
        self._srv_repo = srv_repo

    def print_table(self):
        print(self._srv_repo.get_table())

    def run(self):

        while True:
            try:
                print('Choose one line 1-7: ')
                y = input ('>>> ')  # Read line

                try:
                    y = int(y)
                    if not(1 <= y and y <= 7):
                        raise Exception('Wrong line! ')
                except:
                    raise Exception('Wrong line! ')

                self._srv_repo.add_on_column_player(self._srv_repo.get_table(), y-1)
                self.print_table()
                if self._srv_repo.is_won(self._srv_repo.get_table(), 'X'):
                    print ('Winner: Player! ')
                    return

                self._srv_repo.add_on_column_computer(self._srv_repo.get_table(), self._srv_repo.minimax(self._srv_repo.get_table(), 3, True)[0])
                self.print_table()
                if self._srv_repo.is_won(self._srv_repo.get_table(), '0'):
                    print ('Winner: Computer! ')
                    return

            except Exception as ex:
                print (str(ex))
