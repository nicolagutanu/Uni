

class TripleDictGraph:

    def __init__(self, nr_of_vertices):
        self._dictOut = {}
        self._dictIn = {}
        self._dictCosts = {}
        for i in range(nr_of_vertices):
            self._dictOut[i] = []
            self._dictIn[i] = []

    def is_edge(self, x, y):
        edge = (x, y)
        for key in self._dictCosts.keys():
            if key == edge:
                return 1
        return 0

    def is_vertex(self, x):
        if x in self._dictIn:
            return 1
        return 0

    def add_edge(self, x, y, c):
        if self.is_edge(x, y) == 0:
            self._dictIn[y].append(x)
            self._dictOut[x].append(y)
            self._dictCosts[(x, y)] = c

    def parse_outbound(self, vertex):
        return [y for y in self._dictOut[vertex]]

    def parse_inbound(self, vertex):
        return [y for y in self._dictIn[vertex]]



class PriorityQueue:

    def __init__(self):
        self.__values = {}

    def is_empty(self):
        return len(self.__values) == 0

    def length(self):
        return len(self.__values)

    def pop(self):
        top_priority = None
        top_obj = None
        for obj in self.__values:
            obj_priority = self.__values[obj]
            if top_priority is None or top_priority > obj_priority:
                top_priority = obj_priority
                top_obj = obj
        del self.__values[top_obj]
        return top_obj

    def add(self, obj, priority):
        self.__values[obj] = priority

    def search(self, val):
        return val in self.__values



def dijkstra_back(g, s, t):
    q = PriorityQueue()
    next = {}
    dist = {}
    q.add(t, 0)
    dist[t] = 0
    found = False
    while not q.is_empty() and not found:
        x = q.pop()
        for y in g.parse_inbound(x):
            if y not in dist.keys() or dist[x] + g._dictCosts[y, x] < dist[y]:
                dist[y] = dist[x] + g._dictCosts[y, x]
                q.add(y, dist[y])
                next[y] = x
        if x == s:
            found = True
    return(dist, next)

def dijkstra(g, s, t):
    q = PriorityQueue()
    prev = {}
    dist = {}
    q.add(s, 0)
    dist[s] = 0
    found = False
    while not q.is_empty() and not found:
        x = q.pop()
        for y in g.parse_outbound(x):
            if y not in dist.keys() or dist[x] + g._dictCosts[x, y] < dist[y]:
                dist[y] = dist[x] + g._dictCosts[x, y]
                q.add(y, dist[y])
                prev[y] = x
        if x == t:
            found = True
    return(dist, prev)

def file_graph_oriented():
    file = input("Input your file name: ")
    with open(file, "r") as f:
        lines = f.readlines()
        line = lines[0].split()
        n = int(line[0])
        m = int(line[1])
        g = TripleDictGraph(n)
        for i in range(1, m+1):
            line = lines[i].split()
            x = int(line[0])
            y = int(line[1])
            c = int(line[2])
            g.add_edge(x, y, c)
    return g

def print_dij(g, dist, next, s, t):
    """
    Input: the graph (g), the dictionary of distances (dist), the dictionary of the walk (next), the starting vertex (s), the terminal vertex (t)
    Output: printing the minimum cost walk with its cost
    """
    print("The cost of the minimum walk is:", dist[t])
    print_index = t
    while print_index != s:
        print(print_index, "(cost:", g._dictCosts[next[print_index], print_index], ")->", end="")
        print_index = next[print_index]
    print(print_index)
    """
    print(s, "(cost:", g._dictCosts[s, next[s]], ")->", next[s], end="")
    for i in range(s + 1, t):
        print("(cost:", g._dictCosts[i, next[i]], ")->", next[i], end="")
    print("\n")
    """

g = file_graph_oriented()
s = 100
t = 1
dist, next = dijkstra(g, s, t)
print_dij(g, dist, next, s, t)