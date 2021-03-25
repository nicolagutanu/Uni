

class Table(object):
    def __init__(self):
        self._map = []
        self._heigh = [-1, -1, -1, -1, -1, -1, -1]

        #create table
        for x in range (6):
            self._map.append([])
            for y in range (7):
                self._map[x].append(' ')
                y = int(y)

    def __str__(self):
        string = '-'*27 + '\n'
        for x in range(len(self._map)-1, -1, -1):
            string += '{:^3}|{:^3}|{:^3}|{:^3}|{:^3}|{:^3}|{:^3}\n'.format(self._map[x][0], self._map[x][1], self._map[x][2], self._map[x][3], self._map[x][4], self._map[x][5], self._map[x][6])
            string += '-'*27 + '\n'
        return string

    def get_map(self):
        return self._map

    def get_heigh(self):
        return self._heigh
