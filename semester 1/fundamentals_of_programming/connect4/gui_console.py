import tkinter as tk
import tkinter.messagebox as tkMB
#from PIL import Image, ImageTk

class Colour:
    p = 'skin'

    if p == 'blue':
        background = '#3333cc'
        square_background = '#7070db'
        piece1 = '#ff0000'
        piece2 ='#ffff00'

    elif p == 'yellow':
        background = '#f7e2ad'
        square_background = '#f4d68b'
        piece1 = '#189a73'
        piece2 ='#fd7568'

    elif p == 'skin':
        background = '#d9b09e'
        square_background = '#cd967e'
        piece1 = '#a02d4e'
        piece2 ='#423a63'


class App(tk.Tk):
    def __init__(self, srv_repo):
        tk.Tk.__init__(self)
        self._frame = None
        self._srv_repo = srv_repo
        self._score = [0, 0]
        self.switch_frame(Page)

    def switch_frame(self, frame_class):
        new_frame = frame_class(self)
        if self._frame is not None:
            self._frame.destroy()
        self._frame = new_frame
        self._frame.pack(fill = 'x')
        self._frame.place(relx = 0.1, rely = 0.1, relwidth = 0.8, relheigh = 0.8)


    #--------------------Movie--------------------------
    def add_on_column_player(self, y):
        try:
            self._srv_repo.add_on_column_player(self._srv_repo.get_table(), y-1)
        except Exception as ex:
           
            tkMB.showinfo('Error', str(ex))

    def add_on_column_computer(self):
        try:
            y = self._srv_repo.minimax(self._srv_repo.get_table(), 3, True)[0]
            self._srv_repo.add_on_column_computer(self._srv_repo.get_table(), y)
        except Exception as ex:
            tkMB.showinfo('Error', str(ex))

    def is_won_player(self):
        return self._srv_repo.is_won(self._srv_repo.get_table(), 'X')

    def is_won_computer(self):
        return self._srv_repo.is_won(self._srv_repo.get_table(), '0')

    def print_table(self):
        print(self._srv_repo.get_table())
    

class Page(tk.Frame):
    def __init__(self, master):
        tk.Frame.__init__(self, master)
        tk.Frame.configure(self, background = Colour.background)
        tk.Frame.columnconfigure(self, (0,1,2,3,4,5,6), weight = 1)
        tk.Frame.rowconfigure(self, (0,1,2,3,4,5,6), weight = 1)

        def call (number):
            master.add_on_column_player(number)
            if master.is_won_player(): 
                master.switch_frame(FinalPage)
            else: 
                master.add_on_column_computer()
                if master.is_won_computer():
                    master.switch_frame(FinalPage)
                else:
                    master.switch_frame(Page)

        button1 = tk.Button(self, text = ' ', command=lambda: call(1))
        button1.grid(row = 0, column = 0, sticky = 'nsew', padx = 2, pady = 2)
        if master._srv_repo.get_table().get_heigh()[0] == 5:
            button1.configure(state = 'disabled', text = '!')

        button2 = tk.Button(self, text = ' ', command=lambda: call(2))
        button2.grid(row = 0, column = 1, sticky = 'nsew', padx = 2, pady = 2)
        if master._srv_repo.get_table().get_heigh()[1] == 5:
            button2.configure(state = 'disabled', text = '!')

        button3 = tk.Button(self, text = ' ', command=lambda: call(3))
        button3.grid(row = 0, column = 2, sticky = 'nsew', padx = 2, pady = 2)
        if master._srv_repo.get_table().get_heigh()[2] == 5:
            button3.configure(state = 'disabled', text = '!')

        button4 = tk.Button(self, text = ' ', command=lambda: call(4))
        button4.grid(row = 0, column = 3, sticky = 'nsew', padx = 2, pady = 2)
        if master._srv_repo.get_table().get_heigh()[3] == 5:
            button4.configure(state = 'disabled', text = '!')

        button5 = tk.Button(self, text = ' ', command=lambda: call(5))
        button5.grid(row = 0, column = 4, sticky = 'nsew', padx = 2, pady = 2)
        if master._srv_repo.get_table().get_heigh()[4] == 5:
            button5.configure(state = 'disabled', text = '!')

        button6 = tk.Button(self, text = ' ', command=lambda: call(6))
        button6.grid(row = 0, column = 5, sticky = 'nsew', padx = 2, pady = 2)
        if master._srv_repo.get_table().get_heigh()[5] == 5:
            button6.configure(state = 'disabled', text = '!')

        button7 = tk.Button(self, text = ' ', command=lambda: call(7))
        button7.grid(row = 0, column = 6, sticky = 'nsew', padx = 2, pady = 2)
        if master._srv_repo.get_table().get_heigh()[6] == 5:
            button7.configure(state = 'disabled', text = '!')

        canvas = [[]]
        for i in range (1,7):
            canvas.append([])
            for j in range (7):
                if master._srv_repo.get_table().get_map()[5-(i-1)][j] == 'X':
                    canvas[i].append(tk.Canvas(self, width = 10, heigh = 10, bg = Colour.square_background))
                    canvas[i][j].grid(row = i, column = j, sticky = 'nsew', padx = 2, pady = 2)
                    canvas[i][j].create_oval(5,5,65,65, fill = Colour.piece1)
                elif master._srv_repo.get_table().get_map()[5-(i-1)][j] == '0':
                    canvas[i].append(tk.Canvas(self, width = 10, heigh = 10, bg = Colour.square_background))
                    canvas[i][j].grid(row = i, column = j, sticky = 'nsew', padx = 2, pady = 2)
                    canvas[i][j].create_oval(5,5,65,65, fill = Colour.piece2)
                else:
                    canvas[i].append(tk.Canvas(self, width = 10, heigh = 10, bg = Colour.square_background))
                    canvas[i][j].grid(row = i, column = j, sticky = 'nsew', padx = 2, pady = 2)
                    

class FinalPage(tk.Frame):
    def __init__(self, master):
        tk.Frame.__init__(self, master)
        tk.Frame.configure(self, background = Colour.background)
        tk.Frame.columnconfigure(self, (0,1,2,3,4,5,6), weight = 1)
        tk.Frame.rowconfigure(self, (0,1,2,3,4,5,6), weight = 1)

        def retry(winner):
            master._srv_repo.set_table()
            master.switch_frame(Page)
            master._score[winner] += 1

        if master.is_won_player():
            tk.Button(self, text = 'Player = {:<2}{:<39}Computer = {:<2}{:<37} > '.format(master._score[0]+1, '', master._score[1],''), command=lambda: retry(0)).grid(row = 0, columnspan = 7, sticky = 'nsew', padx = 2, pady = 2)
        else:
            tk.Button(self, text = 'Player = {:<2}{:<39}Computer = {:<2}{:<37} > '.format(master._score[0], '', master._score[1]+1,''), command=lambda: retry(1)).grid(row = 0, columnspan = 7, sticky = 'nsew', padx = 2, pady = 2)



        canvas = [[]]
        for i in range (1,7):
            canvas.append([])
            for j in range (7):
                if master._srv_repo.get_table().get_map()[5-(i-1)][j] == 'X':
                    canvas[i].append(tk.Canvas(self, width = 10, heigh = 10, bg = Colour.square_background))
                    canvas[i][j].grid(row = i, column = j, sticky = 'nsew', padx = 2, pady = 2)
                    canvas[i][j].create_oval(5,5,65,65, fill = Colour.piece1)
                elif master._srv_repo.get_table().get_map()[5-(i-1)][j] == '0':
                    canvas[i].append(tk.Canvas(self, width = 10, heigh = 10, bg = Colour.square_background))
                    canvas[i][j].grid(row = i, column = j, sticky = 'nsew', padx = 2, pady = 2)
                    canvas[i][j].create_oval(5,5,65,65, fill = Colour.piece2)
                else:
                    canvas[i].append(tk.Canvas(self, width = 10, heigh = 10, bg = Colour.square_background))
                    canvas[i][j].grid(row = i, column = j, sticky = 'nsew', padx = 2, pady = 2)

        w = master.is_won_player()
        if w:
            for i in range(4):
                canvas[5-(w[i][0]-1)][w[i][1]].configure(bg = Colour.piece1)

        w = master.is_won_computer()
        if w:
            for i in range(4):
                canvas[5-(w[i][0]-1)][w[i][1]].configure(bg = Colour.piece2)


class GuiConsole(object):
    def __init__(self, srv_repo):
        self._app = App(srv_repo)

    def run(self):
        self._app.geometry("700x700")
        self._app.geometry("+%d+%d" % (500, 200))
        self._app.resizable(0, 0)
        self._app.configure(background = Colour.background)
        self._app.mainloop()
