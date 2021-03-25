import unittest
from entities import *
from repo_service import *

class MyTestCase(unittest.TestCase):

    def test_board(self):
        board = Earth()
        for i in range(7):
            for j in range(7):
                if i == 3 and j == 3:
                    assert (board.get_mat()[i][j] == "E")
                else:
                    assert (board.get_mat()[i][j] == " ")

    def test_fire(self):
        repo_srv = Repo_Service(Earth(), Earth())
        repo_srv.place_ast(repo_srv.get_alien(), repo_srv.get_player())
        repo_srv.place_ali(repo_srv.get_alien())
        try:
            repo_srv.play(repo_srv.get_alien(), repo_srv.get_player(), "A1")
        except Exception as ex:
            assert (str(ex) == "asteroid hit\n")
        try:
            repo_srv.play(repo_srv.get_alien(), repo_srv.get_player(), "B1")
        except Exception as ex:
            assert (str(ex) == "empty space\n")

if __name__ == '__main__':
    unittest.main()
