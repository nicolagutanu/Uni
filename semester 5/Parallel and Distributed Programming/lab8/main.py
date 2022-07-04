import threading
from mpi4py import MPI
from DSM import DSM


class Listener(threading.Thread):
    def __init__(self, dsm):
        threading.Thread.__init__(self)
        self.dsm = dsm

    def run(self):
        while True:
            print("Rank:", MPI.COMM_WORLD.Get_rank(), "is waiting for a message")

            message = MPI.COMM_WORLD.recv(source=MPI.ANY_SOURCE, tag=0)
            if message[0] == "close":
                print("Rank:", MPI.COMM_WORLD.Get_rank(), "closed communication")
                return
            elif message[0] == "subscribe":
                print("Rank:", MPI.COMM_WORLD.Get_rank(), "subscribed to variable: ", message[1], "having rank:", message[2])
                self.dsm.sync_subscribers(message[1], message[2])
            elif message[0] == "value":
                print("Rank:", MPI.COMM_WORLD.Get_rank(), "updated variable", message[1], "to", message[2])
                self.dsm.set_variable(message[1], message[2])
            write_all(self.dsm)


def write_all(dsm):
    print()
    print("Current rank:", MPI.COMM_WORLD.Get_rank(), "a:", dsm.a, "b:", dsm.b, "c:", dsm.c)
    for s in dsm.subscriptions:
        print(s, ":", dsm.subscriptions.get(s))
    print()


if __name__ == "__main__":
    dsm = DSM()
    my_process = MPI.COMM_WORLD.Get_rank()
    if my_process == 0:
        thread = Listener(dsm)
        thread.start()

        dsm.process_subscribes_to("a")
        dsm.process_subscribes_to("b")
        dsm.process_subscribes_to("c")

        dsm.compare_and_exchange("c", 3, 3000)

        dsm.close()

        thread.join()
    elif my_process == 1:
        thread = Listener(dsm)
        thread.start()

        dsm.process_subscribes_to("b")

        thread.join()
    elif my_process == 2:
        thread = Listener(dsm)
        thread.start()

        dsm.process_subscribes_to("a")
        dsm.process_subscribes_to("b")

        dsm.compare_and_exchange("a", 1, 1000)

        thread.join()

    MPI.Finalize()