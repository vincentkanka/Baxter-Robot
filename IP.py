import math

class InputParameters(object):

    def __init__(self, x, y, z):
        self.__X = x
        self.__Y = y
        self.__Z = z

    def setX(self, x):
        self.__X = x

    def setY(self, y):
        self.__Y = y

    def setZ(self, z):
        self.__Z = z

    def getX(self):
        return self.__X

    def getY(self):
        return self.__Y

    def getZ(self):
        return self.__Z

    def dist(self, ip):
        v1 = self.__X-ip.__X
        v2 = self.__Y-ip.__Y
        v3 = self.__Z-ip.__Z
        v1 *=             v1
        v2 *=             v2
        v3 *=             v3
        s  =    v1 + v2 + v3
        d  =    math.sqrt(s)
        return             d

    def dump(self):
        print ((self.__X),
               (self.__Y),
               (self.__Z))


def main():
        i1 = InputParameters(0.0, 0.0, 0.0)
        i2 = InputParameters(1.0, 1.0, 1.0)
        i1.dump()
        i2.dump()
        t = i1.dist(i2)
        print(t)

if __name__ == '__main__':
    main()
