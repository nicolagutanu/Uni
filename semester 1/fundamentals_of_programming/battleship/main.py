from entities import *
from repo_service import *
from console import *

repo_srv = Repo_Service(Ship(), Ship())
ui = Console(repo_srv)
ui.run()