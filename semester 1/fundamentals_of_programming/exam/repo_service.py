from entities import *
import random


class Repo_Service(object):

    def __init__(self, player, alien):
        self.__player = player
        self.__alien = alien

    def get_player(self):
        return self.__player

    def get_alien(self):
        return self.__alien

    def set_player(self):
        self.__player = Earth()

    def set_alien(self):
        self.__alien = Earth()

    def place_ast(self, alien, player):
        for i in range(5):
            found = False
            while not found:
                r = random.randint(1, 5)
                c = random.randint(1, 5)
                if alien.get_mat()[r][c] == " " and alien.get_mat()[r+1][c+1] != "*" and alien.get_mat()[r+1][c] != "*" and alien.get_mat()[r+1][c-1] != "*" and alien.get_mat()[r][c+1] != "*" and alien.get_mat()[r-1][c+1] != "*" and alien.get_mat()[r-1][c] != "*" and alien.get_mat()[r-1][c-1] != "*" and alien.get_mat()[r][c-1] != "*":
                    alien.get_mat()[r][c] = "*"
                    found = True
        alien.get_mat()[0][0] = "*"
        alien.get_mat()[0][6] = "*"
        alien.get_mat()[6][1] = "*"
        for i in range(7):
            for j in range(7):
                player.get_mat()[i][j] = alien.get_mat()[i][j]
        return self.__alien


    def place_ali(self, alien):
        d = random.randint(0,1)
        if d == 0:
            found = False
            while not found:
                r1 = 0
                c1 = random.randint(0,6)
                c2 = 0
                r2 = random.randint(0,6)
                if alien.get_mat()[r1][c1] == " " and alien.get_mat()[r2][c2] == " ":
                    alien.get_mat()[r1][c1] = "X"
                    alien.get_mat()[r2][c2] = "X"
                    found = True
        else:
            found = False
            while not found:
                r3 = 6
                c3 = random.randint(0, 6)
                c4 = 6
                r4 = random.randint(0, 6)
                if alien.get_mat()[r3][c3] == " " and alien.get_mat()[r4][c4] == " ":
                    alien.get_mat()[r3][c3] = "X"
                    alien.get_mat()[r4][c4] = "X"
                    found = True

    def play(self, alien, player, coord):
        #input: the 2 boards: player for the one where aliens cannot be seen (a list of lists = matrix)
        #                     alien for the one that is used for cheating and has the location of the aliens (a list of lists = matrix)
        #       the coordonates where the player would like to fire (string)
        #output: player board with the needed changes made
        #we first chance the coordinates into digits so that we can use them in the matrix
        #then we check all cases to see what the player has hit:
        #     -hitting an asteroid means changing the asteroid symbol with a "-"
        #     -hitting an already hit spot will not change anything in the matrix
        #     -hitting an alien will result in its disappearance
        #     -hitting empty space will result in the appearence of a "-"
        r = int(coord[1]) - 1
        if coord[0] == "A":
            c = 0
        if coord[0] == "B":
            c = 1
        if coord[0] == "C":
            c = 2
        if coord[0] == "D":
            c = 3
        if coord[0] == "E":
            c = 4
        if coord[0] == "F":
            c = 5
        if coord[0] == "G":
            c = 6
        if alien.get_mat()[c][r] == "*":
            player.get_mat()[c][r] = "-"
            print("asteroid hit\n")
        elif alien.get_mat()[c][r] == "-":
            print("space hit before\n")
        elif alien.get_mat()[c][r] == "X":
            alien.get_mat()[c][r] = " "
            player.get_mat()[c][r] = "-"
            print("alien distroyed\n")
        else:
            player.get_mat()[c][r] = "-"
            print("empty space\n")
        a = 0
        for i in range(7):
            for j in range(7):
                if alien.get_mat()[i][j] == "X":
                    a += 1
        if a == 0:
            print("player has won\n")
        return self.__player

    def get_closer(self, alien):
        d = random.randint(0,1)
        if d == 0:
            for p in range(2):
                for i in range(7):
                    for j in range(7):
                        if alien.get_mat()[i][j] == "X":
                            if j == 0:
                                alien.get_mat()[i][6] = "X"
                                alien.get_mat()[i][j] = " "
                            elif j == 6:
                                alien.get_mat()[i][0] = "X"
                                alien.get_mat()[i][j] = " "
                            elif i == 0:
                                alien.get_mat()[6][j] = "X"
                                alien.get_mat()[i][j] = " "
                            elif i == 6:
                                alien.get_mat()[0][j] = "X"
                                alien.get_mat()[i][j] = " "
        else:
            for p in range(2):
                for i in range(7):
                    for j in range(7):
                        if alien.get_mat()[i][j] == "X":
                            if i == 0:
                                alien.get_mat()[i + 1][j] = "X"
                                alien.get_mat()[i][j] = " "
                            elif j == 0:
                                alien.get_mat()[i][j + 1] = "X"
                                alien.get_mat()[i][j] = " "
                            elif i == 6:
                                alien.get_mat()[i - 1][j] = "X"
                                alien.get_mat()[i][j] = " "
                            elif j == 6:
                                alien.get_mat()[i][j - 1] = "X"
                                alien.get_mat()[i][j] = " "
        #return self.__alien

    def close_to_earth(self, alien):
        if alien.get_mat()[4][4] == "X" or alien.get_mat()[4][3] == "X" or alien.get_mat()[4][2] == "X" or alien.get_mat()[3][4] == "X" or alien.get_mat()[2][4] == "X" or alien.get_mat()[2][3] == "X" or alien.get_mat()[2][2] == "X" or alien.get_mat()[3][2] == "X":
            print("lost game\n")