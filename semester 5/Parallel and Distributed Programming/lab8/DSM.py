import threading
from mpi4py import MPI


class DSM:
    def __init__(self):
        # predefined integer variables
        self.a = 1
        self.b = 2
        self.c = 3
        # subscriptions are static, we have lists so that we know what process/node subscribed to what variable
        self.subscriptions = dict()
        self.subscriptions["a"] = []
        self.subscriptions["b"] = []
        self.subscriptions["c"] = []

        # lock for sending messages correctly
        self.lock = threading.Lock()

    # callback for notifications
    def send_message_to_all(self, message):
        for i in range(MPI.COMM_WORLD.Get_size()):
            if MPI.COMM_WORLD.Get_rank() == i and message[0] != "close":
                continue
            MPI.COMM_WORLD.send(message, i, 0)

    # notification just for subscribers
    def send_to_subscribers(self, var, message):
        for i in range(MPI.COMM_WORLD.Get_size()):
            if MPI.COMM_WORLD.Get_rank() == i or i not in self.subscriptions.get(var):
                continue
            MPI.COMM_WORLD.send(message, dest=i, tag=0)

    def process_subscribes_to(self, var):
        subs = self.subscriptions.get(var)
        subs.append(MPI.COMM_WORLD.Get_rank())
        self.subscriptions[var] = subs
        message = ["subscribe", var, MPI.COMM_WORLD.Get_rank()]
        self.send_message_to_all(message)

    def set_variable(self, var, value):
        if var == "a":
            self.a = value
        elif var == "b":
            self.b = value
        elif var == "c":
            self.c = value

    def write_value_to_var(self, var, value):
        self.lock.acquire()
        self.set_variable(var, value)
        message = ["value", var, value]
        self.send_to_subscribers(var, message)
        self.lock.release()

    def compare_and_exchange(self, var, old_value, new_value):
        if var == "a" and self.a == old_value:
            self.write_value_to_var("a", new_value)
        if var == "b" and self.b == old_value:
            self.write_value_to_var("b", new_value)
        if var == "c" and self.c == old_value:
            self.write_value_to_var("c", new_value)

    def close(self):
        self.send_message_to_all(["close", "closed", True])

    def sync_subscribers(self, var, rank):
        subs = self.subscriptions.get(var)
        subs.append(rank)
        self.subscriptions[var] = subs