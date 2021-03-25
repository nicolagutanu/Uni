import random
import copy
import math

from domain import Table

class SrvRepo(object):
    def __init__(self, table):
        self._table = table

    def get_table(self):
        return self._table

    def set_table(self):
        self._table = Table()
    

    #_____Player_____:
    def add_on_column_player(self, table, y):  
        if table.get_heigh()[y] >= 5:
            raise Exception('!Column is full! \n')
        else:
            table.get_heigh()[y] += 1
            table.get_map()[table.get_heigh()[y]][y] = 'X'


    #_____Computer_____:
    def add_on_column_computer(self, table, y):  
        if table.get_heigh()[y] >= 5:
            raise Exception('!Column is full! \n')
        else:
            table.get_heigh()[y] += 1
            table.get_map()[table.get_heigh()[y]][y] = '0'


    #_____ComputerAI_____:
    ## Score:
    def decide_score (self, nr_pieces, nr_opp_pieces, nr_empty):
        score = 0
        if nr_pieces == 4:
            score += 100
        elif nr_pieces == 3 and nr_empty == 1:
            score += 10
        elif nr_pieces == 2 and nr_empty == 2:
            score += 2
        elif nr_opp_pieces == 2 and nr_empty == 2:
            score -= 4
        elif nr_opp_pieces == 3 and nr_empty == 1:
            score -= 100

        return score

    def score_position(self, table):
        score = 0
        piece = '0'
        opp_piece = 'X'
        empty = ' '

        # Center:
        nr_pieces = 0
        nr_empty = 0
        for r in range(6):
            if table.get_map()[r][3] == piece:
                nr_pieces += 1 
        score += self.decide_score(nr_pieces, 0, 0)*3

        # Horizontal:
        for r in range(6):
            for c in range(7 - 3):
                nr_pieces = 0
                nr_opp_pieces = 0
                nr_empty = 0
                for i in range(0, 4):
                    if table.get_map()[r][c+i] == piece:
                        nr_pieces += 1 
                    elif table.get_map()[r][c+i] == opp_piece:
                        nr_opp_pieces +=1
                    elif table.get_map()[r][c+i] == empty:
                        nr_empty += 1

                score += self.decide_score(nr_pieces, nr_opp_pieces, nr_empty)
        
        # Vertical:
        for r in range(6 - 3):
            for c in range(7):
                nr_pieces = 0
                nr_opp_pieces = 0
                nr_empty = 0
                for i in range (0, 4):
                    if table.get_map()[r+i][c] == piece:
                        nr_pieces += 1 
                    elif table.get_map()[r+i][c] == opp_piece:
                        nr_opp_pieces +=1
                    elif table.get_map()[r+i][c] == ' ':
                        nr_empty += 1
                
                score += self.decide_score(nr_pieces, nr_opp_pieces, nr_empty)

        # Diagonal /:
        for r in range(3, 6):
            for c in range(7-3):
                nr_pieces = 0
                nr_opp_pieces = 0
                nr_empty = 0
                for i in range (0, 4):
                    if table.get_map()[r-i][c+i] == piece:
                        nr_pieces += 1 
                    elif table.get_map()[r-i][c+i] == opp_piece:
                        nr_opp_pieces +=1
                    elif table.get_map()[r-i][c+i] == ' ':
                        nr_empty += 1
                
                score += self.decide_score(nr_pieces, nr_opp_pieces, nr_empty)

        # Diagonal \:
        for r in range(3, 6):
            for c in range(3, 7):
                nr_pieces = 0
                nr_opp_pieces = 0
                nr_empty = 0
                for i in range (0, 4):
                    if table.get_map()[r-i][c-i] == piece:
                        nr_pieces += 1 
                    elif table.get_map()[r-i][c-i] == opp_piece:
                        nr_opp_pieces +=1
                    elif table.get_map()[r-i][c-i] == ' ':
                        nr_empty += 1
                
                score += self.decide_score(nr_pieces, nr_opp_pieces, nr_empty)

        return score


    def is_full (self, table):
        for i in range (7):
            if table.get_heigh()[i] != 5:
                return False
        return True
        
    def is_terminal (self, table):
        return self.is_won(table, 'X') or self.is_won(table, '0') or self.is_full(table)

    #_____Winner_____:
    def is_won(self, table, piece):
        # Check horiziontal:
        for r in range(6):
            for c in range(7-3):
                if (table.get_map()[r][c] == piece) and (table.get_map()[r][c+1] == piece) and (table.get_map()[r][c+2] == piece) and (table.get_map()[r][c+3] == piece):
                    return [[r, c], [r, c+1], [r, c+2], [r, c+3]]

        # Check vertical:
        for r in range(6-3):
            for c in range(7):
                if (table.get_map()[r][c] == piece) and (table.get_map()[r+1][c] == piece) and (table.get_map()[r+2][c] == piece) and (table.get_map()[r+3][c] == piece):
                    return [[r, c], [r+1, c], [r+2, c], [r+3, c]]

        # Check diagonal /:
        for r in range(3, 6):
            for c in range(7-3):
                if (table.get_map()[r][c] == piece) and (table.get_map()[r-1][c+1] == piece) and (table.get_map()[r-2][c+2] == piece) and (table.get_map()[r-3][c+3] == piece):
                    return [[r, c], [r-1, c+1], [r-2, c+2], [r-3, c+3]]

        # Check diagonal \:
        for r in range(3, 6):
            for c in range(3, 7):
                if (table.get_map()[r][c] == piece) and (table.get_map()[r-1][c-1] == piece) and (table.get_map()[r-2][c-2] == piece) and (table.get_map()[r-3][c-3] == piece):
                    return [[r, c], [r-1, c-1], [r-2, c-2], [r-3, c-3]]





    ## MiniMax Algorithm
    def minimax(self, table, depth, computer):
        if depth == 0 or self.is_terminal(table):
            if self.is_terminal(table):
                if self.is_won(table, 'X'):
                    return (None, -100000000000000)
                elif self.is_won(table, '0'):
                    return (None, 1000000000000000)
                else:  # Game is over!
                    return (None, 0)
            else:
                return (None, self.score_position(table))

        if computer == True:
            value = -math.inf
            column = random.randrange(7)
            for col in range(7):
                if table.get_heigh()[col] != 5:
                    temp_table = copy.deepcopy(table)
                    self.add_on_column_computer(temp_table, col)
                    new_score = self.minimax(temp_table, depth - 1, False)[1]
                    if new_score > value:
                        value = new_score
                        column = col
            return column, new_score

        else:  # player
            value = math.inf
            column = random.randrange(7)
            for col in range(7):
                if table.get_heigh()[col] != 5:
                    temp_table = copy.deepcopy(table)
                    self.add_on_column_player(temp_table, col)
                    new_score = self.minimax(temp_table, depth - 1, True)[1]
                    if new_score < value:
                        value = new_score
                        column = col
            return column, new_score
    
