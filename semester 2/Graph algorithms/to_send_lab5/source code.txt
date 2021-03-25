import copy


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

    def print_edges(self):
        """
         Output: will print all edges existent in the graph
        """
        for key in self._dictCosts.keys():
            print(key)

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

    def remove_vertex(self, vertex):
        """
        Input: a vertex
        Precondition: the vertex has to exist in the graph
        Then the function will check if there are any edges that contain this vertex and delete them
        Output: will remove the vertex key along with its list from the in and out dictionaries
        """
        if self.is_vertex(vertex) == 0:
            raise ValueError("Vertex doesn't exist \n")
        for i in range(len(self._dictOut[vertex])):
            e = (vertex, self._dictOut[vertex][i])
            if self.is_edge(vertex, self._dictOut[vertex][i]) == 1:
                del self._dictCosts[e]
        for i in range(len(self._dictIn[vertex])):
            e = (self._dictIn[vertex][i], vertex)
            if self.is_edge(self._dictIn[vertex][i], vertex) == 1:
                del self._dictCosts[e]
        del self._dictOut[vertex]
        del self._dictIn[vertex]

    def remove_edge(self, x, y):
        """
        Input: an edge (ex: 2 3)
        Precondition: the edge has to exist in the graph
        Then for each of the in and out vertices, they will be removed from the in and out
        dictionaries
        Output: the tuple will be deleted from the cost dictionaries
        """
        if self.is_edge(x, y) == 0:
            raise ValueError("Edge doesn't exist \n")
        self._dictOut[x].remove(y)
        self._dictIn[y].remove(x)
        e = (x,y)
        del self._dictCosts[e]

    def print_dicts(self):
        """
        Output: will print the dictionaries in the form that they are kept in the memory
        For in and out dictionaries: key + list
        For cost dictionary: the edge tuple and its cost
        """
        print(self._dictOut)
        print(self._dictIn)
        print(self._dictCosts)

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


def max_degree(g):
    '''
    Input: g, the graph
    Output: the vertex having a maximum degree
    '''
    max = 0
    for i in g._dictOut.keys():
        total = len(g.parse_outbound(i)) + len(g.parse_inbound(i))
        if total > max:
            max = total
            vertex = i
    return vertex


def greedy_vertex_cover(g):
    '''
    Input: g, the graph
    Output: the minimum vertex cover along with its size, using greedy version
    '''
    c = []
    edges = []
    for i in g._dictCosts.keys():
        edges.append([i[0], i[1]])
    while len(edges) != 0:
        e = max_degree(g)
        print(e)
        c.append(e)
        for j in g.parse_outbound(e):
            edge = [e, j]
            if edge in edges:
                edges.remove(edge)
                g.remove_edge(e, j)
            edge = [j, e]
            if edge in edges:
                edges.remove(edge)
                g.remove_edge(j, e)
        for j in g.parse_inbound(e):
            edge = [e, j]
            if edge in edges:
                edges.remove(edge)
                g.remove_edge(e, j)
            edge = [j, e]
            if edge in edges:
                edges.remove(edge)
                g.remove_edge(j, e)
    print("Vertex cover length:", len(c))
    print("Vertex cover:", c)


def vertex_cover(g):
    '''
    Input: g, the graph
    Output: the minimum vertex cover along with its size
    '''
    c = []
    edges = []
    for i in g._dictCosts.keys():
        edges.append([i[0], i[1]])
    while len(edges) != 0:
        e = edges.pop(0)
        c.append(e)
        for j in g.parse_outbound(e[0]):
            edge = [e[0], j]
            if edge in edges:
                edges.remove(edge)
            edge = [j, e[0]]
            if edge in edges:
                edges.remove(edge)
        for j in g.parse_outbound(e[1]):
            edge = [e[1], j]
            if edge in edges:
                edges.remove(edge)
            edge = [j, e[1]]
            if edge in edges:
                edges.remove(edge)
        for j in g.parse_inbound(e[0]):
            edge = [e[0], j]
            if edge in edges:
                edges.remove(edge)
            edge = [j, e[0]]
            if edge in edges:
                edges.remove(edge)
        for j in g.parse_inbound(e[1]):
            edge = [e[1], j]
            if edge in edges:
                edges.remove(edge)
            edge = [j, e[1]]
            if edge in edges:
                edges.remove(edge)
    print("Vertex cover length:", len(c))
    print("Vertex cover:", c)


def file_graph():
    """
    Input: a file name
    Output: an unoriented graph read from a file
    """
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
            g.add_edge(x, y, 0)
    return g


def print_menu():
    """
    Output: printing the menu
    """
    print("1 - Add an edge")
    print("2 - Add a vertex")
    print("3 - Remove an edge")
    print("4 - Remove a vertex")
    print("5 - Print the edges")
    print("11 - Parse all vertices")
    print("12 - Parse outbound edges")
    print("13 - Parse inbound edges")
    print("14 - Get the minimun size vertex cover")
    print("15 - Read the graph from a file")
    print("16 - Get the minimun size vertex cover: bonus")
    print("21 - Exit")


def run():
    while True:
        try:
            print_menu()
            nr = int(input(">>>"))
            if nr == 21:
                break
            elif nr == 1:
                edge = input("Input the edge and cost: ")
                edge = edge.split()
                x = int(edge[0])
                y = int(edge[1])
                c = int(edge[2])
                g.add_edge(x, y, c)
            elif nr == 5:
                g.print_edges()
            elif nr == 2:
                vertex = int(input("Input your vertex: "))
                g.add_vertex(vertex)
            elif nr == 3:
                edge = input("Input your edge: ")
                edge = edge.split()
                x = int(edge[0])
                y = int(edge[1])
                g.remove_edge(x, y)
            elif nr == 4:
                vertex = int(input("Input your vertex: "))
                g.remove_vertex(vertex)
            elif nr == 11:
                print(g.parse_vert())
            elif nr == 12:
                vertex = int(input("Input a vertex: "))
                print(g.parse_outbound(vertex))
            elif nr == 13:
                vertex = int(input("Input your vertex: "))
                print(g.parse_inbound(vertex))
            elif nr == 14:
                vertex_cover(g)
            elif nr == 15:
                g = file_graph()
            elif nr == 16:
                greedy_vertex_cover(g)
        except Exception as ex:
            print(str(ex))


run()
