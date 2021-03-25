import unittest

from domain import Table
from srv_repo import SrvRepo


class Test(unittest.TestCase):

    def test_table(self):
        t = Table()
        for i in range(6):
            for j in range(7):
                assert (t.get_map()[i][j] == ' ')

        for i in range(6):
            assert (t.get_heigh()[i] == -1)

    def test_add_on_column(self):
        srv_repo = SrvRepo(Table())

        srv_repo.add_on_column_player(srv_repo.get_table(), 0)
        assert (srv_repo.get_table().get_map()[0][0] == 'X')

        srv_repo.add_on_column_player(srv_repo.get_table(), 0)
        srv_repo.add_on_column_player(srv_repo.get_table(), 0)
        srv_repo.add_on_column_player(srv_repo.get_table(), 0)
        srv_repo.add_on_column_player(srv_repo.get_table(), 0)
        try:
            srv_repo.add_on_column_player(srv_repo.get_table(), 0)
        except Exception as ex:
            assert (str(ex) == '!Column is full! \n')


if __name__ == "__main__":
    unittest.main()
