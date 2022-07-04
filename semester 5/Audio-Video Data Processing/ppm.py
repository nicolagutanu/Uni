class PPM:
    def __init__(self):
        self.type = 'P3'
        self.width = 0
        self.height = 0
        self.max = 0

        self.red = []
        self.green = []
        self.blue = []

        self.y = []
        self.u = []
        self.v = []

    def read_file(self, name):
        file = open(name, 'r')
        lines = file.readlines()

        l = lines[2].split(" ")
        self.width = int(l[0])
        self.height = int(l[1])
        self.max = int(lines[3].replace("\n", ""))

        self.red = [[0 for x in range(self.width)] for y in range(self.height)]
        self.green = [[0 for x in range(self.width)] for y in range(self.height)]
        self.blue = [[0 for x in range(self.width)] for y in range(self.height)]
        l = 4
        for i in range(self.height):
            for j in range(self.width):
                l1 = lines[l]
                l2 = lines[l+1]
                l3 = lines[l+2]
                self.red[i][j] = int(lines[l].strip())
                self.green[i][j] = int(lines[l+1].strip())
                self.blue[i][j] = int(lines[l+2].strip())
                l += 3

    def convert_rgb_to_yuv(self):
        self.y = [[0 for x in range(self.width)] for y in range(self.height)]
        self.u = [[0 for x in range(self.width)] for y in range(self.height)]
        self.v = [[0 for x in range(self.width)] for y in range(self.height)]

        for i in range(self.height):
            for j in range(self.width):
                self.y[i][j] = float(format(0.299 * self.red[i][j] + 0.587 * self.green[i][j] + 0.114 * self.blue[i][j], ".2f"))
                self.u[i][j] = float(format(-0.169 * self.red[i][j] - 0.331 * self.green[i][j] + 0.500 * self.blue[i][j] + 128, ".2f"))
                self.v[i][j] = float(format(0.500 * self.red[i][j] - 0.419 * self.green[i][j] - 0.081 * self.blue[i][j] + 128, ".2f"))

    def convert_yuv_to_rgb(self, y, u, v):
        self.y = y
        self.u = u
        self.v = v
        for i in range(self.height):
            for j in range(self.width):
                self.red[i][j] = int(1.000 * self.y[i][j] + 1.400 * (self.v[i][j] - 128))
                if self.red[i][j] > self.max:
                    self.red[i][j] = self.max
                elif self.red[i][j] < 0:
                    self.red[i][j] = 0

                self.green[i][j] = int(1.000 * self.y[i][j] - 0.343 * (self.u[i][j] - 128) - 0.711 * (self.v[i][j] - 128))
                if self.green[i][j] > self.max:
                    self.green[i][j] = self.max
                elif self.green[i][j] < 0:
                    self.green[i][j] = 0

                self.blue[i][j] = int(1.000 * self.y[i][j] + 1.765 * (self.u[i][j] - 128))
                if self.blue[i][j] > self.max:
                    self.blue[i][j] = self.max
                elif self.blue[i][j] < 0:
                    self.blue[i][j] = 0

    def write_to_file(self, file_name):
        f = open(file_name, "w")
        f.write(self.type + "\n")
        f.write(str(self.width) + " " + str(self.height) + "\n")
        f.write(str(self.max) + "\n")
        for i in range(self.height):
            for j in range(self.width):
                f.write(str(self.red[i][j]) + "\n")
                f.write(str(self.green[i][j]) + "\n")
                f.write(str(self.blue[i][j]) + "\n")
        f.close()