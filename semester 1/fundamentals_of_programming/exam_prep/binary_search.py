def search(x, v):
    st = 1
    dr = len(x)
    while st < dr:
        mid = int((st + dr) / 2)
        if x[mid] == v:
            return mid
        elif x[mid] > v:
            dr = mid - 1
        else:
            st = mid + 1

if __name__ == "__main__":
    x = [1, 5, 7, 10, 90, 103, 2000, 8900]
    print(str(search(x, 90)))