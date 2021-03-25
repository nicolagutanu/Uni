import unittest
from entities import Orders
from services import Service


class MyTestCase(unittest.TestCase):

    def test_order(self):
        order = Orders(1, 10)
        self.assertEqual(order.get_id_d(), 1)
        self.assertEqual(order.get_dist(), 10)

    def test_add_ord(self):
        self.__srv = Service()
        self.__srv.add_order(1, 10)
        x = self.__srv.get_orders()
        self.assertEqual(x[0].get_id_d(), 1)
        self.assertEqual(x[0].get_dist(), 10)

if __name__ == '__main__':
    unittest.main()
