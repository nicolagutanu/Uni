import unittest
from assg11 import Hope

class MyTestCase(unittest.TestCase):

    def setUp(self):
        self.__l = [3, 6, 2, 7, 0, 5]
        self.__index = 0
        self.__lis = Hope(self.__l, self.__index)

    def test_sort(self):
        lis = self.__lis.shell_sort(self.__l)
        self.assertEqual(lis[0], 0)
        self.assertEqual(lis[1], 2)
        self.assertEqual(lis[2], 3)
        self.assertEqual(lis[3], 5)
        self.assertEqual(lis[4], 6)
        self.assertEqual(lis[5], 7)

    def test_filter(self):
        self.__l = ["Top_Gun", "TATBILB", "F%F8", "312", "302112"]
        self.__index = 0
        self.__list = Hope(self.__l, self.__index)
        l = self.__list.filter(self.__l, "T")
        self.assertEqual(l[0], "Top_Gun")
        self.assertEqual(l[1], "TATBILB")



if __name__ == '__main__':
    unittest.main()
