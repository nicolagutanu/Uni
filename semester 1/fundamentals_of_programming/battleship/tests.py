import unittest
from entities import *
from repo_service import *


class MyTestCase(unittest.TestCase):

    def test_board(self):
        b = Ship()
        for i in range(0, 5):
            for j in range(0, 5):
                assert (b.get_ocean()[i][j] == ".")

    def test_placement(self):
        repo_srv = Repo_Service(Ship(), Ship())
        repo_srv.place_ship_player(repo_srv.get_player(), "A",0,"A",1,"A",2)
        #print(repo_srv.get_player())
        assert (repo_srv.get_player().get_ocean()[0][0] == "+")
        assert (repo_srv.get_player().get_ocean()[1][0] == "+")
        assert (repo_srv.get_player().get_ocean()[2][0] == "+")
        try:
            repo_srv.place_ship_player(repo_srv.get_player(), "A",0,"B",0,"C",0)
        except Exception as ex:
            assert (str(ex) == "ships overlap\n")


if __name__ == '__main__':
    unittest.main()
