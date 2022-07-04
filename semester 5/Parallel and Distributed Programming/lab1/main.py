import random
import threading
import time


class Node:
    def __init__(self, index, value, parent, children):
        self.index = index
        self.value = value
        self.parent = parent
        self.children = children
        self.lock = threading.Lock()


class myThread(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self)

    def run(self):
        #get random leaf
        #get random value between 1 and 10 to add
        #then while you've not reached the root, keep going
        global leaves, nrThreads
        leaf = leaves[random.randint(0, len(leaves)-1)]
        newLeafValue = random.randint(1, 10)
        if newLeafValue == leaf.value:
            return
        changeValue = newLeafValue - leaf.value
        print("leaf " + str(leaf.index) + " changes from " + str(leaf.value) + " to " + str(newLeafValue))
        leaf.value = newLeafValue
        nextNode = leaf.parent
        while nextNode != None:
            #LOCK
            nextNode.lock.acquire()
            nextNode = nextNode.parent

        nextNode = leaf.parent
        while nextNode != None:
            nextNode.value += changeValue
            nextNode.lock.release()
            nextNode = nextNode.parent

        nrThreads -= 1
        time.sleep(0.5)


def sumSubtree(root):
    total = 0
    for i in root.children:
        total += getNodeByIndex(i).value
    if root.value != total:
        return False
    else:
        for i in root.children:
            if len(getNodeByIndex(i).children) != 0:
                return sumSubtree(getNodeByIndex(i))
    return True

def consistencyCheck():
    global root
    print("Consistency check: ")
    global tree
    for t in tree:
        t.lock.acquire()
    if sumSubtree(root):
        print("Check passed")
    else:
        print("Inconsistent")
        exit()
    for t in tree:
        t.lock.release()

def getNodeByIndex(index):
    global tree
    for t in tree:
        if t.index == index:
            return t

root = Node(1, 24, None, [2, 3])
node2 = Node(2, 6, root, [4, 5])
node3 = Node(3, 18, root, [6, 7])
node4 = Node(4, 1, node2, [])
node5 = Node(5, 5, node2, [8])
node6 = Node(6, 5, node3, [9])
node7 = Node(7, 13, node3, [10, 11])
node8 = Node(8, 5, node5, [12, 13])
node9 = Node(9, 5, node6, [])
node10 = Node(10, 6, node7, [])
node11 = Node(11, 7, node7, [])
node12 = Node(12, 2, node8, [])
node13 = Node(13, 3, node8, [])
tree = [root, node2, node3, node4, node5, node6, node7, node8, node9, node10, node11, node12, node13]
leaves = [node4, node9, node10, node11, node12, node13]
nrThreads = 0

if __name__ == '__main__':
    nrThreads = int(input("Nr of threads>>> "))
    threads = []
    for i in range(0, nrThreads):
        t = myThread()
        threads.append(t)
        t.start()
        if nrThreads % 2 == 0:
            consistencyCheck()

    for i in range(len(threads)):
        threads[i].join()

    print("------Final check------")
    consistencyCheck()
