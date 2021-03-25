from gui_console import GuiConsole
from console import Console
from srv_repo import SrvRepo
from domain import Table

srv_repo = SrvRepo(Table())
ui = Console(srv_repo)
gui = GuiConsole(srv_repo)

ui.run()
#gui.run()