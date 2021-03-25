import unittest
from entities import Tabla
from repo_service import RepoSrv

class MyTestCase(unittest.TestCase):

    def test_tabla(self):
        t = Tabla()
        for i in range(0, 6):
            for j in range(0,7):
                assert (t.get_mat()[i][j] == ' ')
        for i in range (0,6):
            assert (t.get_height()[i] == -1)

    def test_add(self):
        repo_srv = RepoSrv(Tabla())
        repo_srv.add_player(repo_srv.get_tabla(), 0)
        assert (repo_srv.get_tabla().get_mat()[0][0] == 'P')
        repo_srv.add_player(repo_srv.get_tabla(), 0)
        repo_srv.add_player(repo_srv.get_tabla(), 0)
        repo_srv.add_player(repo_srv.get_tabla(), 0)
        repo_srv.add_player(repo_srv.get_tabla(), 0)
        try:
            repo_srv.add_player(repo_srv.get_tabla(), 0)
        except Exception as ex:
            assert  (str(ex) == 'This column is full \n')

if __name__ == '__main__':
    unittest.main()
