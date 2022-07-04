class FiniteAutomata:
    def __init__(self):
        self.finite_states = []
        self.alphabet = []
        self.initial_state = ""
        self.final_states = []
        self.transitions = []

    def read_from_file(self, file):
        f = open(file, 'r')
        lines = f.readlines()

        finites = lines[0]
        finites = finites.split(" ")
        self.finite_states = finites
        self.finite_states[len(self.finite_states)-1] = self.finite_states[len(self.finite_states)-1].replace("\n", "")

        a = lines[1]
        a = a.split(" ")
        self.alphabet = a
        self.alphabet[len(self.alphabet)-1] = self.alphabet[len(self.alphabet)-1].replace("\n", "")

        self.initial_state = lines[2].replace("\n", "")

        finals = lines[3]
        finals = finals.split(" ")
        self.final_states = finals
        self.final_states[len(self.final_states)-1] = self.final_states[len(self.final_states)-1].replace("\n", "")

        for i in range(4, len(lines)):
            l = lines[i].split("=")
            help = l[0].split(",")
            self.transitions.append([(help[0], help[1]), l[1].replace("\n", "")])

    def display_finite_states(self):
        str = ""
        for finites in self.finite_states:
            str += finites + ","
        str = str[:-1]
        print("Q={", str, "}")

    def display_alphabet(self):
        str = ""
        for a in self.alphabet:
            str += a + ","
        str = str[:-1]
        print("Σ={", str, "}")

    def display_initial_state(self):
        print("Initial state:", self.initial_state)

    def display_final_states(self):
        str = ""
        for finals in self.final_states:
            str += finals + ","
        str = str[:-1]
        print("F={", str, "}")

    def display_transitions(self):
        for t in self.transitions:
            print("δ(", t[0][0], ",", t[0][1], ")=", t[1])


def check_dfa(fa, dfa):
    current_state = fa.initial_state
    total = 0
    for c in dfa:
        for t in fa.transitions:
            if t[0][0] == current_state and t[0][1] == c:
                current_state = t[1]
                total += 1
                break
    if current_state in fa.final_states and total == len(dfa):
        return True
    else:
        return False


def menu():
    print("______MENU______")
    print("1. Finite set of states")
    print("2. Alphabet")
    print("3. Initial state")
    print("4. Final set of states")
    print("5. Transitions")
    print("6. Evaluate sequence")


if __name__ == '__main__':
    fa = FiniteAutomata()
    file_name = input("file name >>> ")
    fa.read_from_file(file_name)
    while True:
        menu()
        cmd = int(input("command >>> "))
        if cmd == 1:
            fa.display_finite_states()
        if cmd == 2:
            fa.display_alphabet()
        if cmd == 3:
            fa.display_initial_state()
        if cmd == 4:
            fa.display_final_states()
        if cmd == 5:
            fa.display_transitions()
        if cmd == 6:
            dfa = str(input("input your dfa >>> "))
            if check_dfa(fa, dfa):
                print("ACCEPTED")
            else:
                print("REJECTED")