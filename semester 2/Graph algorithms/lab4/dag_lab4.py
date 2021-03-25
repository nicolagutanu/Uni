class TripleDictGraph:

    def __init__(self, nr_of_vertices):
        """
        Input: number of vertices
        Output: creates the graph with the three empty dictionaries
        """
        self._dictOut = {}
        self._dictIn = {}
        self._dictCosts = {}
        for i in range(1, nr_of_vertices + 1):
            self._dictOut[i] = []
            self._dictIn[i] = []

    def is_edge(self, x, y):
        """
        Input: two vertices between which you want to check if there is an edge
        Output: 1 if it's an edge, 0 otherwise
        """
        edge = (x, y)
        for key in self._dictCosts.keys():
            if key == edge:
                return 1
        return 0

    def is_vertex(self, x):
        """
         Input: a vertex
        Output: 1 if it’s a vertex, 0 otherwise
        """
        if x in self._dictIn:
            return 1
        return 0

    def add_edge(self, x, y, c):
        """
         Input: an edge and its cost (ex: 2 3 100)
         Precondition: the edge has to not already exist in the graph and the vertices have to exist
         Output: will add the edge if everything is alright, the beginning and end vertices in each of
         the in and out dictionaries and then in the cost dictionary the tuple with its cost
        """
        if self.is_edge(x, y) == 0:
            self._dictIn[y].append(x)
            self._dictOut[x].append(y)
            self._dictCosts[(x, y)] = c

    def add_vertex(self, vertex):
        """
        Input: a vertex
        Precondition: the vertex can’t already exist
        Output: a new key that is the new vertex will be added in the in and out dictionaries with an
        empty list
        """
        if self.is_vertex(vertex) == 1:
            raise ValueError("Vertex already exits \n")
        self._dictOut[vertex] = []
        self._dictIn[vertex] = []
        print("Vertex added! \n")

    def parse_vert(self):
        """
        Output: iterates the set of vertices
        """
        return self._dictOut.keys()

    def parse_outbound(self, vertex):
        """
        Input: a vertex
        Precondition: the vertex has to exist in the graph
        Output: iterates the set of outbound edges of a given vertex
        """
        return [y for y in self._dictOut[vertex]]

    def parse_inbound(self, vertex):
        """
        Input: a vertex
        Precondition: the vertex has to exist in the graph
        Output: iterates the set of inbound edges of a given vertex
        """
        return [y for y in self._dictIn[vertex]]



def topological_sort(g):
    """
    Input: the graph
    Output: the topologically sorted vertices based on predecessor count
    """
    sorted = []
    q = []
    count = {}
    for x in g.parse_vert():
        count[x] = len(g.parse_inbound(x))
        if count[x] == 0:
            q.append(x)
    while len(q) > 0:
        x = q.pop(0)
        sorted.append(x)
        for y in g.parse_outbound(x):
            count[y] = count[y] - 1
            if count[y] == 0:
                q.append(y)
    if len(sorted) < len(g.parse_vert()):
        sorted = []
    return sorted

def print_walk(next, s, f):
    """
    Input: the list with the walk, starting vertex, finishing vertex
    Output: the printing of the walk
    """
    walk = []
    print_index = f
    while print_index != s:
        walk.append(print_index)
        print_index = next[print_index]
    walk.append(print_index)
    for i in range(len(walk) - 1, 0, -1):
        print(walk[i], "-> ", end="")
    print(walk[0])

def highest_cost_walk(g, sorted, s, f):
    """
    Input: the graph, the topologically sorted list, starting vertex, finishing vertex
    Output: the highest cost walk, which will e printed along with the walk
    """
    dist = {}
    next = {}
    for i in range(len(sorted) + 1):
        dist[i] = -1000000;
    dist[s] = 0
    for u in sorted:
        if u == f:
            break
        for v in g.parse_outbound(u):
            if dist[v] < (dist[u] + g._dictCosts[u, v]):
                dist[v] = dist[u] + g._dictCosts[u, v]
                next[v] = u
    print("The highest walk is:", dist[f])
    print_walk(next, s, f)


def file_graph_oriented():
    """
    Input: a file name
    Output: an oriented graph read from a file
    """
    file = input("Input your file name: ")
    if file == "exit":
        exit()
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


def run():
    while True:
        try:
            g = file_graph_oriented()
            sorted = []
            sorted = topological_sort(g)
            if len(sorted) != 0:
                print("The graph is a directed acyclic graph")
                print("And the topological sorting based on predecessor count will be: ")
                print(sorted)
                print("And for the highest cost walk please choose: ")
                s = int(input("Starting vertex: "))
                f = int(input("Finishing vertex: "))
                highest_cost_walk(g, sorted, s, f)
            else:
                print("The graph is not a directed acyclic graph")
        except Exception as ex:
            print(str(ex))


run()