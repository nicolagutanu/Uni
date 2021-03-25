from console import Console
from repo_service import RepoSrv
from entities import Tabla

repo_srv = RepoSrv(Tabla())
ui = Console(repo_srv)
ui.run()