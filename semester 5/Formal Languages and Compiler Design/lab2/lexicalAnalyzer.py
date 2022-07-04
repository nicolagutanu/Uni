from symbolsTable import SymbolTable
import re


def read_keywords(key_file):
    global keywords
    with open(key_file) as f:
        lines = f.readlines()
    for l in lines:
        keywords.append(l.replace("\n", ""))
    keywords.append("\n")


def read_from_file():
    global initial_file
    initial_file = input("input file>>> ")
    f = open(initial_file, "r")
    prog = f.read()
    f.close()
    return prog


def write_to_file():
    global pif, st_id, st_const, initial_file
    f_pif = input("file for pif>>>")
    f_pif = open(f_pif, "a")
    f_pif.write("Analyzed file: " + initial_file + "\n")
    for p in pif:
        f_pif.write("(" + str(p[0]) + ", " + str(p[1]) + ")\n")
    f_pif.write("\n")
    f_pif.close()

    f_st = input("file for symbols table>>>")
    f_st = open(f_st, "a")
    f_st.write("Analyzed file: " + initial_file + "\n")
    f_st.write("Symbols table for identifiers:")
    st_id.write_to_file(f_st)
    f_st.write("\n")
    f_st.write("Symbols table for constants:")
    st_const.write_to_file(f_st)
    f_st.write("\n")
    f_st.write("\n")
    f_st.close()


def get_location(err):
    global initial_file
    with open(initial_file) as f:
        lines = f.readlines()
    for i in range(len(lines)):
        if err in lines[i]:
            return i + 1, lines[i].find(err) + 1


def separate(prog):
    global keywords
    lexeme = ''
    separated = []
    for i, char in enumerate(prog):
        if char != ' ':
            lexeme += char
        if i + 1 < len(prog):
            if prog[i + 1] == ' ' or prog[i + 1] in keywords or lexeme in keywords:
                if lexeme != '' and prog[i + 1] != '=' and prog[i] not in ['-', '+']:
                    separated.append(lexeme)
                    lexeme = ''
    return separated


def check_identifier(identifier):
    regex = '^[a-z][A-Za-z0-9_]*'
    if re.search(regex, identifier) and identifier != "false" and identifier != "true":
        return True
    else:
        return False


def check_constant(const):
    regex_nr = '^[-+]?[0-9]+$'
    regex_char = '^[a-zA-Z0-9]$'
    regex_string = '^[a-zA-Z0-9]+$'
    if const == 'true' or const == 'false':
        return True
    elif re.search(regex_nr, const.replace("'", '')):
        return True
    elif const[0] == "'" and const[2] == "'" and re.search(regex_char, const.replace("'", '')):
        return True
    elif const[0] == "'" and const[len(const) - 1] == "'" and re.search(regex_string, const.replace("'", '')):
        return True
    else:
        return False


def analyze(program):
    global pif, st_id, st_const
    for t in program:
        if t in keywords and t != '\n':
            pif.append([t, 0])
        elif check_identifier(t):
            if st_id.search(t):
                tr, pos = st_id.search(t)
                pif.append(["id", pos])
            else:
                st_id.insert(t, st_id.size() + 1)
                pif.append(["id", st_id.size()])
        elif check_constant(t):
            if st_const.search(t):
                tr, pos = st_const.search(t)
                pif.append(["const", pos])
            else:
                st_const.insert(t, st_const.size() + 1)
                pif.append(["const", st_const.size()])
        elif t != '\n':
            line, char = get_location(t)
            print("lexical error:", line, ":", char, ":", t)
            exit()


keywords = []
pif = []
st_id = SymbolTable('', 0)
st_const = SymbolTable('', 0)
initial_file = ""

if __name__ == '__main__':
    read_keywords("token.in")
    while True:
        p = read_from_file()
        separated_p = separate(p)
        analyze(separated_p)
        print("lexically correct")
        write_to_file()
        print("---------------------------------------------------")
        pif = []
        st_id = SymbolTable('', 0)
        st_const = SymbolTable('', 0)
