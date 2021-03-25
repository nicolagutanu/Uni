from entities import Tabla
import random


class RepoSrv(object):

    def __init__(self, tabla):
        self.__tabla = tabla

    def get_tabla(self):
        return self.__tabla

    def set_tabla(self):
        self.__tabla = Tabla()

    def add_player(self, tabla, c):
        if tabla.get_height()[c] >= 5:
            raise Exception('This column is full \n')
        else:
            tabla.get_height()[c] += 1
            tabla.get_mat()[tabla.get_height()[c]][c] = 'P'

    def add_computer(self, tabla, c):
        if tabla.get_height()[c] >= 5:
            raise Exception('This column is full \n')
        else:
            tabla.get_height()[c] += 1
            tabla.get_mat()[tabla.get_height()[c]][c] = 'C'

    def comp_moves(self, tabla, p_piece):
        # check horizontal
        for c in range(0, 7):
            for r in range(0, 3):
                if tabla.get_mat()[r][c] == 'P' and tabla.get_mat()[r + 1][c] == 'P' and tabla.get_mat()[r + 2][c] == 'P' and tabla.get_mat()[r + 3][c] == ' ':
                    self.add_computer(tabla, c)
                    return

        # check vertical
        for r in range(0, 6):
            for c in range(0, 3):
                if tabla.get_mat()[r][c] == 'P' and tabla.get_mat()[r][c + 1] == 'P' + tabla.get_mat()[r][c + 2] == 'P' and tabla.get_mat()[r][c + 3] == ' ':
                    self.add_computer(tabla, c + 3)
                    return

        # check primary diagonal:
        for r in range(3, 6):
            for c in range(0, 4):
                if tabla.get_mat()[r][c] == 'P' and tabla.get_mat()[r - 1][c + 1] == 'P' and tabla.get_mat()[r - 2][c + 2] == 'P' and tabla.get_mat()[r - 3][c + 3] == ' ':
                    self.add_computer(tabla, c + 3)
                    return

        # check secondary diagonal:
        for r in range(3, 6):
            for c in range(3, 7):
                if tabla.get_mat()[r][c] == 'P' and tabla.get_mat()[r - 1][c - 1] == 'P' and tabla.get_mat()[r - 2][c - 2] == 'P' and tabla.get_mat()[r - 3][c - 3] == ' ':
                    self.add_computer(tabla, c - 3)
                    return

        # otherwise
        c = random.randrange(7)
        if tabla.get_height()[c] <5 :
            self.add_computer(tabla, c)
        else:
            c = p_piece
            r = tabla.get_height()[c]
            if tabla.get_mat()[r+1][c] == ' ':
                self.add_computer(tabla, c)
            elif tabla.get_mat()[r][c + 1] == ' ':
                self.add_computer(tabla, c + 1)
            elif tabla.get_mat()[r][c - 1] == ' ':
                self.add_computer(tabla, c - 1)
        '''
        else:
            column = 0
            while column == 0:
                c = random.randrange(7)
                if tabla.get_height()[c] < 5:
                    self.add_computer(tabla, c)
                    column = 1
        '''

    def full_tabla(self, tabla):
        for i in range(0, 7):
            if tabla.get_height()[i] != 5:
                return False
            return True

    def check_win(self, tabla, piece):
        # check horizontal
        a = 0
        for c in range(0, 7):
            for r in range(0, 3):
                if tabla.get_mat()[r][c] == piece and tabla.get_mat()[r + 1][c] == piece and tabla.get_mat()[r + 2][c] == piece and tabla.get_mat()[r + 3][c] == piece:
                    return True

        # check vertical
        for r in range(0, 6):
            for c in range(0, 4):
                if tabla.get_mat()[r][c] == piece and tabla.get_mat()[r][c + 1] == piece and tabla.get_mat()[r][c + 2] == piece and tabla.get_mat()[r][c + 3] == piece:
                    return True

        # check primary diagonal:
        for r in range(3, 6):
            for c in range(0, 4):
                if tabla.get_mat()[r][c] == piece and tabla.get_mat()[r - 1][c + 1] == piece and tabla.get_mat()[r - 2][c + 2] == piece and tabla.get_mat()[r - 3][c + 3] == piece:
                    return True

        # check secondary diagonal:
        for r in range(3, 6):
            for c in range(3, 7):
                if tabla.get_mat()[r][c] == piece and tabla.get_mat()[r - 1][c - 1] == piece and tabla.get_mat()[r - 2][c - 2] == piece and tabla.get_mat()[r - 3][c - 3] == piece:
                    return True
