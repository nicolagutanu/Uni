from entities import Drivers, Orders
from repos import Repo
from services import Service
from console import Console

repoOrders = Repo("orders", Orders.read_object)
repoDrivers = Repo("drivers", Drivers.read_object)
repoDrivers.read_all_from_file()
repoOrders.read_all_from_file()
serviceOrders = Service(repoOrders)
serviceDrivers = Service(repoDrivers)
ui = Console(serviceOrders, serviceDrivers)
ui.run()