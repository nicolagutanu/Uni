class MatGraph:

    def __init__(self, n):
        self._mat = []
        for i in range(n):
            self._mat.append([])
            for j in range(n):
                self._mat[i].append(0)

    def parse_edges(self, x):
        list = []
        for i in range(len(self._mat[x])):
            if self._mat[x][i]:
                list.append(i)
        return list

    def is_edge(self, x, y):
        return self._mat[x][y]

    def add_edge(self, x, y):
        if self.is_edge(x, y) == 1:
            raise ValueError("Edge already exists!\n")
        self._mat[x][y] = 1
        self._mat[y][x] = 1



def depth_travel(g, vf, viz, depth_list):
    depth_list.append(vf)
    viz[vf] = 1
    for i in g.parse_edges(vf):
        if viz[i] == 0 and g.is_edge(vf, i) == 1:
            depth_travel(g, i, viz, depth_list)

def file_graph():
    file = input("Input your file name: ")
    with open(file, "r") as f:
        lines = f.readlines()
        line = lines[0].split()
        n = int(line[0])
        m = int(line[1])
        g = MatGraph(n)
        for i in range(1, m+1):
            line = lines[i].split()
            x = int(line[0])
            y = int(line[1])
            g.add_edge(x, y)
    return g

def run():
    g = file_graph()
    while True:
        print("Please input a vertex!")
        vf = input(">>>")
        if vf == "exit":
            exit()
        else:
            try:
                vf = int(vf)
                if vf < 0 or vf >= len(g._mat[0]):
                    raise ValueError("Vertex does not exits!")
                viz = []
                for i in range(len(g._mat[0])):
                    viz.append(0)
                depth_list = []
                depth_travel(g, vf, viz, depth_list)
                print(depth_list)
            except Exception as ex:
                print(str(ex))


run()