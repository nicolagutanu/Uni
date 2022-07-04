class SymbolTable:

    def __init__(self, identifier, pos):
        self.left = None
        self.right = None
        self.identifier = identifier
        self.pos = pos

    def insert(self, identifier, pos):
        if self is None:
            return SymbolTable(identifier, pos)
        else:
            if identifier < self.identifier:
                if self.left is None:
                    self.left = SymbolTable(identifier, pos)
                else:
                    self.left.insert(identifier, pos)
            elif identifier > self.identifier:
                if self.right is None:
                    self.right = SymbolTable(identifier, pos)
                else:
                    self.right.insert(identifier, pos)
            else:
                return

    def search(self, identifier):
        if self is None or self.identifier == identifier:
            return True, self.pos
        if self.identifier < identifier:
            if self.right is None:
                return False
            return self.right.search(identifier)
        elif self.identifier > identifier:
            if self.left is None:
                return False
            return self.left.search(identifier)

    def print(self):
        if self.left:
            self.left.print()
        print(self.identifier + " " + str(self.pos))
        if self.right:
            self.right.print()

    def size(self):
        if self.left is None and self.right is None:
            return 0
        elif self.right is None:
            return self.left.size() + 1
        elif self.left is None:
            return self.right.size() + 1
        else:
            return self.left.size() + 1 + self.right.size()

    def write_to_file(self, file):
        if self.left:
            self.left.write_to_file(file)
        file.write(self.identifier + " " + str(self.pos) + "\n")
        if self.right:
            self.right.write_to_file(file)