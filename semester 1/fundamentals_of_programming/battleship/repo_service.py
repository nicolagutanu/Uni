from entities import *
import random


class Repo_Service(object):

    def __init__(self, table, player_board):
        self.__table = table
        self.__player_board = player_board

    def get_table(self):
        return self.__table

    def get_player(self):
        return self.__player_board

    def set_table(self):
        self.__table = Ship()

    def set_player(self):
        self.__player_board = Ship()

    def place_ship_player(self, player_board, c1, r1, c2, r2, c3, r3):
        #input: 3 squares on the board
        #output: the board with the placed battleship on the given squares if all condintions are met
        if c1 not in ["A", "B", "C", "D", "E", "F"]:
            raise ValueError("ship outside of board\n")
        if c2 not in ["A", "B", "C", "D", "E", "F"]:
            raise ValueError("ship outside of board\n")
        if c3 not in ["A", "B", "C", "D", "E", "F"]:
            raise ValueError("ship outside of board\n")
        if r1 not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("ship outside of board\n")
        if r2 not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("ship outside of board\n")
        if r3 not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("ship outside of board\n")
        if c1 == "A":
            c1 = 0
        if c1 == "B":
            c1 = 1
        if c1 == "C":
            c1 = 2
        if c1 == "D":
            c1 = 3
        if c1 == "E":
            c1 = 4
        if c1 == "F":
            c1 = 5
        if c2 == "A":
            c2 = 0
        if c2 == "B":
            c2 = 1
        if c2 == "C":
            c2 = 2
        if c2 == "D":
            c2 = 3
        if c2 == "E":
            c2 = 4
        if c2 == "F":
            c2 = 5
        if c3 == "A":
            c3 = 0
        if c3 == "B":
            c3 = 1
        if c3 == "C":
            c3 = 2
        if c3 == "D":
            c3 = 3
        if c3 == "E":
            c3 = 4
        if c3 == "F":
            c3 = 5
        if player_board.get_ocean()[r1][c1] == "." and player_board.get_ocean()[r2][c2] == "." and player_board.get_ocean()[r3][c3] == ".":
            player_board.get_ocean()[r1][c1] = "+"
            player_board.get_ocean()[r2][c2] = "+"
            player_board.get_ocean()[r3][c3] = "+"
        else:
            raise ValueError("ships overlap\n")
        return player_board.get_ocean()[:]

    def place_ship_computer(self, player_board):
        chosen = False
        while chosen == False:
            r1 = random.randint(0, 5)
            c1 = random.randint(0, 3)
            if player_board.get_ocean()[r1][c1] == "." and player_board.get_ocean()[r1][c1+1] == "." and player_board.get_ocean()[r1][c1+2] == ".":
                chosen = True
                player_board.get_ocean()[r1][c1] = "-"
                player_board.get_ocean()[r1][c1+1] = "-"
                player_board.get_ocean()[r1][c1+2] = "-"
        chosen = False
        while chosen == False:
            r1 = random.randint(0, 3)
            c1 = random.randint(0, 5)
            if player_board.get_ocean()[r1][c1] == "." and player_board.get_ocean()[r1+1][c1] == "." and player_board.get_ocean()[r1+2][c1] == ".":
                chosen = True
                player_board.get_ocean()[r1][c1] = "-"
                player_board.get_ocean()[r1+1][c1] = "-"
                player_board.get_ocean()[r1+2][c1] = "-"

    def player_attack(self, player_board, table, c, r):
        if c == "A":
            c = 0
        if c == "B":
            c = 1
        if c == "C":
            c = 2
        if c == "D":
            c = 3
        if c == "E":
            c = 4
        if c == "F":
            c = 5
        if player_board.get_ocean()[r,c] == "-":
            print("player hits")
            player_board.get_ocean()[r][c] = "O"
            table.get_ocean()[r][c] = "X"
        else:
            print("player missed")
            table.get_ocean()[r][c] = "O"
        return table.get_ocean()[:]

    def comp_attack(self, player_board):
        r = random.randint(0,5)
        c = random.randint(0,5)
        if player_board.get_ocean()[r][c] == "+":
            print("computer hits\n")
            player_board.get_ocean()[r][c] = "X"
        else:
            print("computer misses\n")

    def check_player_win(self, player_board):
        minus = 0
        for i in range(5):
            for j in range(5):
                if player_board.get_ocean()[i][j] == "-":
                    minus += 1
        if minus == 0:
            return True
        else:
            return False

    def check_computer_win(self, player_board):
        plus = 0
        for i in range(5):
            for j in range(5):
                if player_board.get_ocean()[i][j] == "+":
                    plus += 1
        if plus == 0:
            return True
        else:
            return False

    def cheat(self, player_board):
        return player_board.get_ocean()[:]