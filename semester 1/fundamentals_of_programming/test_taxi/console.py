class Console(object):

    def __ui_d_all_ord(self, params):
        orders = self.__serviceOrders.get_orders()
        for order in orders:
            print(order)

    def __ui_add_order(self, params):
        if len(params) != 2:
            raise ValueError("it requires exactly 2 params\n")
        id_d = int(params[0])
        dist = int(params[1])
        self.__serviceOrders.add_order(id_d, dist)

    def __ui_com_inc(self, params):
        if len(params) != 1:
            raise ValueError("it requires exactly 1 param\n")
        print(self.__serviceDrivers.search(int(params[0]), None))
        print(self.__serviceOrders.compute_income(int(params[0])))

    def __init__(self, serviceOrders, serviceDrivers):
        self.__serviceOrders = serviceOrders
        self.__serviceDrivers = serviceDrivers
        self.__commands = {
            "display_all_orders": self.__ui_d_all_ord,
            "add_order": self.__ui_add_order,
            "compute_income": self.__ui_com_inc
        }

    def run(self):
        while True:
            cmd = input(">>>")
            cmd = cmd.strip()
            if cmd == "":
                continue
            parts = cmd.split()
            name_cmd = parts[0]
            params = parts[1:]
            if name_cmd in self.__commands:
                try:
                    self.__commands[name_cmd](params)
                except ValueError as va:
                    print("UI Error:\n" + str(va))
            else:
                print("invalid command\n")
