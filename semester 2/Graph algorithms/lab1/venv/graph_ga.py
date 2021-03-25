import random


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
        if self.is_edge(x, y) == 1:
            raise ValueError("The edge already exists \n")
        if self.is_vertex(x) == 0 or self.is_vertex(y) == 0:
            raise ValueError("One of the vertexes does not exist, try again \n")
        self._dictIn[y].append(x)
        self._dictOut[x].append(y)
        self._dictCosts[(x, y)] = c
        print("Edge added!")

    def print_edges(self):
        for key in self._dictCosts.keys():
            print(key)

    def add_vertex(self, vertex):
        if self.is_vertex(vertex) == 1:
            raise ValueError("Vertex already exits \n")
        self._dictOut[vertex] = []
        self._dictIn[vertex] = []
        print("Vertex added! \n")

    def remove_vertex(self, vertex):
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
        print("Vertex removed! \n")

    def remove_edge(self, x, y):
        if self.is_edge(x, y) == 0:
            raise ValueError("Edge doesn't exist \n")
        self._dictOut[x].remove(y)
        self._dictIn[y].remove(x)
        e = (x,y)
        del self._dictCosts[e]
        print("Edge removed! \n")

    def nr_of_vert(self):
        return len(self._dictOut)

    def print_dicts(self):
        print(self._dictOut)
        print(self._dictIn)
        print(self._dictCosts)

    def degree(self, vertex):
        if self.is_vertex(vertex) == 0:
            raise ValueError("Vertex doesn't exist! \n")
        print("The in degree of the vertex is: ", len(self._dictIn[vertex]))
        print("The out degree of the vertex is: ", len(self._dictOut[vertex]))

    def parse_vert(self):
        return self._dictOut.keys()

    def parse_outbound(self, vertex):
        if self.is_vertex(vertex) == 0:
            raise ValueError("Vertex doesn't exist! \n")
        return [y for y in self._dictOut[vertex]]

    def parse_inbound(self, vertex):
        if self.is_vertex(vertex) == 0:
            raise ValueError("Vertex doesn't exist! \n")
        return [y for y in self._dictIn[vertex]]

    def change_cost(self, x, y, c):
        e = (x, y)
        new_cost = c
        if self.is_edge(x, y) == 0:
            raise ValueError("Edge doesn't exist \n")
        for key in self._dictCosts.keys():
            if key == e:
                self._dictCosts[e] = new_cost
        print("The cost was changed! \n")

    def copy_graph(self):
        g2 = TripleDictGraph(len(self._dictOut))
        for i in range(len(self._dictOut)):
            for j in range(len(self._dictOut[i])):
                g2._dictOut[i].append(self._dictOut[i][j])
        for i in range(len(self._dictIn)):
            for j in range(len(self._dictIn[i])):
                g2._dictIn[i].append(self._dictIn[i][j])
        for key in self._dictCosts.keys():
            g2._dictCosts[key] = self._dictCosts[key]
        return g2



def save_changes(g):
    file_name = input("Input the file name where you want changes saved: ")
    with open(file_name, "w") as f:
        first_line = str(len(g._dictIn)) + " " + str(len(g._dictCosts))
        f.write(first_line + "\n")
        for key in g._dictCosts.keys():
            line = str(key[0]) + " " + str(key[1]) + " " + str(g._dictCosts[key])
            f.write(line + "\n")

def file_graph():
    file = input("Input your file name: ")
    with open(file, "r") as f:
        lines = f.readlines()
        line = lines[0].split()
        n = int(line[0])
        m = int(line[1])
        g = TripleDictGraph(n)
        for i in range(1, m):
            line = lines[i].split()
            x = int(line[0])
            y = int(line[1])
            c = int(line[2])
            g.add_edge(x, y, c)
    return g

def random_graph():
    n = int(input("Input the number of vertices: "))
    m = int(input("Input the number of edges: "))
    g = TripleDictGraph(n);
    addedEdges = 0
    if m > n * n:
        raise ValueError("The graph cannot be generated with these, try again!\n")
    while addedEdges < m:
        x = random.randrange(0, n)
        y = random.randrange(0, n)
        c = random.randrange(0, 100)
        if not g.is_edge(x, y):
            g.add_edge(x, y, c)
            addedEdges += 1
    return g

def print_menu():
    print("1 - Add an edge")
    print("2 - Add a vertex")
    print("3 - Remove an edge")
    print("4 - Remove a vertex")
    print("5 - Print the edges")
    print("6 - Is vertex")
    print("7 - Is edge")
    print("8 - Get number of vertices")
    print("9 - Print successors and predecessors and costs")
    print("10 - Get the in degree and out degree of a vertex")
    print("11 - Parse all vertices")
    print("12 - Parse outbound edges")
    print("13 - Parse inbound edges")
    print("14 - Save changes")
    print("15 - Copy graph")
    print("16 - Change cost of an edge")
    print("17 - File graph")
    print("18 - Random graph")
    print("20 - Exit")

def run():
    while True:
        try:
            print_menu()
            nr = int(input(">>>"))
            if nr == 20:
                break
            elif nr == 1:
                edge = input("Input the edge and cost: ")
                edge = edge.split()
                x = int(edge[0])
                y = int(edge[1])
                c = int(edge[2])
                g.add_edge(x, y, c)
            elif nr == 6:
                vertex = int(input("Input your vertex: "))
                if g.is_vertex(vertex) == 1:
                    print("Is vertex \n")
                else:
                    print("Is not vertex \n")
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
            elif nr == 7:
                edge = input("Input your edge: ")
                edge = edge.split()
                x = int(edge[0])
                y = int(edge[1])
                if g.is_edge(x, y) == 1:
                    print("Is edge \n")
                else:
                    print("Is not edge \n")
            elif nr == 4:
                vertex = int(input("Input your vertex: "))
                g.remove_vertex(vertex)
            elif nr == 8:
                print("The number of vertices is: ", g.nr_of_vert())
            elif nr == 9:
                g.print_dicts()
            elif nr == 10:
                vertex = int(input("Please input your vertex: "))
                g.degree(vertex)
            elif nr == 11:
                print(g.parse_vert())
            elif nr == 12:
                vertex = int(input("Input a vertex: "))
                print(g.parse_outbound(vertex))
            elif nr == 13:
                vertex = int(input("Input your vertex: "))
                print(g.parse_inbound(vertex))
            elif nr == 14:
                save_changes(g)
            elif nr == 15:
                g2 = g.copy_graph()
                g2.print_dicts()
            elif nr == 16:
                edge = input("Input the edge and cost: ")
                edge = edge.split()
                x = int(edge[0])
                y = int(edge[1])
                c = int(edge[2])
                g.change_cost(x, y, c)
            elif nr == 17:
                g = file_graph()
            elif nr == 18:
                g = random_graph()
        except Exception as ex:
            print(str(ex))

run()