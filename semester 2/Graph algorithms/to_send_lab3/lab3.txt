import random



class TripleDictGraph:

    def __init__(self, nr_of_vertices):
        """
        Input: number of vertices
        Output: creates the graph with the three empty dictionaries
        """
        self._dictOut = {}
        self._dictIn = {}
        self._dictCosts = {}
        for i in range(nr_of_vertices):
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
        print("Vertex added! \n")

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
        print("Vertex removed! \n")

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
        print("Edge removed! \n")

    def nr_of_vert(self):
        """
        Output: will return the number of vertices in the graph
        """
        return len(self._dictOut)

    def print_dicts(self):
        """
        Output: will print the dictionaries in the form that they are kept in the memory
        For in and out dictionaries: key + list
        For cost dictionary: the edge tuple and its cost
        """
        print(self._dictOut)
        print(self._dictIn)
        print(self._dictCosts)

    def degree(self, vertex):
        """
        Input: a vertex
        Precondition: the vertex has to exist in the graph
        Output the in and out degree of the vertex
        """
        if self.is_vertex(vertex) == 0:
            raise ValueError("Vertex doesn't exist! \n")
        print("The in degree of the vertex is: ", len(self._dictIn[vertex]))
        print("The out degree of the vertex is: ", len(self._dictOut[vertex]))

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

    def change_cost(self, x, y, c):
        """
        Input: an edge and the new cost (ex: 2 3 100)
        Precondition: the edge has to exist in the graph
        Output: the cost of the edge will be updated
        """
        e = (x, y)
        new_cost = c
        if self.is_edge(x, y) == 0:
            raise ValueError("Edge doesn't exist \n")
        for key in self._dictCosts.keys():
            if key == e:
                self._dictCosts[e] = new_cost
        print("The cost was changed! \n")

    def copy_graph(self):
        """
        Output: a copy of the graph
        """
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



class PriorityQueue:

    def __init__(self):
        """
        Output: an empty dictinary
        """
        self.__values = {}

    def is_empty(self):
        """
        Output: true if dictionary is empty, false otherwise
        """
        return len(self.__values) == 0

    def pop(self):
        """
        Output: the key with the smallest value, key which will then be deleted
        """
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
        """
        Input: the key in the dictionary (obj) and the value (priority)
        Output: value will be added in the list at the specified key
        """
        self.__values[obj] = priority



def dijkstra_back(g, s, t):
    """
    Input: the start and finish vertex of the walk
    Output: minimum cost walk and cost
    """
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

def print_dij(g, dist, next, s, t):
    """
    Input: the graph (g), the dictionary of distances (dist), the dictionary of the walk (next), the starting vertex (s), the terminal vertex (t)
    Output: printing the minimum cost walk with its cost
    """
    print("The cost of the minimum walk is:", dist[s])
    print_index = s
    while print_index != t:
        print(print_index, "(cost:", g._dictCosts[print_index, next[print_index]], ")->", end="")
        print_index = next[print_index]
    print(print_index)

def print_graph(g, comp, nr):
    """
    Input: the component, a list (comp), an index representing its number in the graph (nr)
    Output: printing the component
    """
    if len(comp) == 1:
        print("Component", nr, ":")
        print("     Vertex: ", comp[0])
    elif len(comp) == 2:
        print("Component", nr, ":")
        print("     Vertex: ", comp[0], ", ", comp[1])
        print("     Edges: ", comp[0], "-", comp[1])
    else:
        print("Component", nr, ":")
        print("     Vertices: ", end="")
        for i in range(len(comp) - 1):
            print(comp[i], ", ", end="")
        print(comp[len(comp) - 1])
        print("     Edges: ", end="")
        for i in comp:
            for key in g._dictCosts.keys():
                if key[0] == comp[i] and g._dictCosts[key] == 0:
                    print(key[0], "-", key[1], " ", end="")
    print("\n")

def dfs(g, comp, i, visited):
    """
    Input: a list containing the vertices of the component (comp), the vertex we want to check (i), a list that checks if a vertex was visited (visited)
    Output: the component
    """
    visited[i] = 1
    comp.append(i)
    for j in g.parse_outbound(i):
        if visited[j] == 0:
            comp = dfs(g, comp, j, visited)
    return comp

def connected_components(g):
    """
    Input: a graph
    Output: printing of the components of a graph
    """
    visited = []
    nr = 1
    for i in range(len(g._dictIn)):
        visited.append(0)
    for i in range(len(g._dictIn)):
        if visited[i] == 0:
            comp = []
            dfs(g, comp, i, visited)
            print_graph(g, comp, nr)
            nr += 1

def save_changes(g):
    """
    Input: a graph (g) and a file name
    Output: saving the graph in a file
    """
    file_name = input("Input the file name where you want changes saved: ")
    with open(file_name, "w") as f:
        for key in g._dictCosts.keys():
            line = str(key[0]) + " " + str(key[1]) + " " + str(g._dictCosts[key])
            f.write(line + "\n")
        i = 0
        for key in g._dictIn.keys():
            if key != i:
                line = str(i) + " " + "-1"
                f.write(line + "\n")
                i = i + 2
            else:
                i += 1

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
            g.add_edge(y, x, 10)
    return g

def file_graph_oriented():
    """
    Input: a file name
    Output: an oriented graph read from a file
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
            c = int(line[2])
            g.add_edge(x, y, c)
    return g

def random_graph():
    """
    Input: number of vertices and number of edges
    Output: an unoriented graph randomly generated
    """
    n = int(input("Input the number of vertices: "))
    m = int(input("Input the number of edges: "))
    g = TripleDictGraph(n);
    addedEdges = 0
    if m > n * n:
        raise ValueError("The graph cannot be generated with these, try again!\n")
    while addedEdges < m:
        x = random.randrange(0, n)
        y = random.randrange(0, n)
        if not g.is_edge(x, y):
            g.add_edge(x, y, 0)
            g.add_edge(y, x, 10)
            addedEdges += 1
    return g

def random_graph_oriented():
    """
    Input: number of vertices and number of edges
    Output: an oriented graph randomly generated
    """
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
    """
    Output: printing the menu
    """
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
    print("19 - Connected components of undirected graph")
    print("20 - Find the lowest cost walk between two given vertices")
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
                g = file_graph_oriented()
            elif nr == 18:
                g = random_graph_oriented()
            elif nr == 19:
                direction = input("Random of file graph? ")
                if direction == "random":
                    g = random_graph()
                else:
                    g = file_graph()
                connected_components(g)
            elif nr == 20:
                g = file_graph_oriented()
                s = int(input("Please input starting vertex: "))
                t = int(input("Please input terminal vertex: "))
                dist, next = dijkstra_back(g, s, t)
                print_dij(g, dist, next, s, t)
        except Exception as ex:
            print(str(ex))



run()