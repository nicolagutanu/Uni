import threading
import time


class MyQueue():
    def __init__(self):
        self.queue = []
        self.lock = threading.Lock()


class Producer(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self)

    def run(self):
        global v1, v2, q, done
        for i in range(len(v1)):
            conditionVar.acquire()
            prod = v1[i] * v2[i]
            q.lock.acquire()
            q.queue.append(prod)
            q.lock.release()
            print("Producer: ", str(prod))
            conditionVar.notify()
            print("Consumer notified")
            conditionVar.release()
            time.sleep(0.5)
        done = True


class Consumer(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self)

    def run(self):
        global q, scalar_prod, done
        while not done:
            conditionVar.acquire()
            while len(q.queue) < 1:
                print("Consumer waiting")
                conditionVar.wait()
            print("Consumer received product")
            q.lock.acquire()
            num = q.queue.pop(0)
            q.lock.release()
            scalar_prod = scalar_prod + num
            print("Scalar product produced: ", scalar_prod)
            conditionVar.notify()
            conditionVar.release()
            time.sleep(0.5)


q = MyQueue()
conditionVar = threading.Condition()
v1 = [1, 2, 3, 4, 5]
v2 = [5, 4, 3, 2, 1]
scalar_prod = 0
done = False
if __name__ == '__main__':
    producer = Producer()
    consumer = Consumer()

    producer.start()
    consumer.start()

    while not done:
        pass

    producer.join()
    consumer.join()

    print("Final sum: ", scalar_prod)
