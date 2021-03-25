from entities import Orders, Drivers


class Service(object):

    def __init__(self, repoOrders):
        self.__repoOrders = repoOrders

    def get_orders(self):
        return self.__repoOrders.get_all()

    def add_order(self, id_d, dist):
        order = Orders(id_d, dist)
        self.__repoOrders.add(order)

    def search(self, id_d, name):
        driver = Drivers(id_d, name)
        return self.__repoOrders.search(driver)

    def compute_income(self, id_dr):
        orders = self.__repoOrders.get_all()
        sum = 0
        for order in orders:
            if order.get_id_d() == id_dr:
                sum = sum + order.get_dist()
        sum = sum * 2.5
        return sum