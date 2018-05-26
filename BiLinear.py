class BilinearInterpolation(object):
    """ Bilinear interpolation. """
    def __init__(self, x_index, y_index, values):
        self.x_index = x_index
        self.y_index = y_index
        self.values = values
        
    def bisect_left(self, a, x, lo=0, hi=None):
        if lo < 0:
            raise ValueError('lo must be non-negative')
        if hi is None:
            hi = len(a)
        while lo < hi:
            mid = (lo+hi)//2
            if a[mid] < x: lo = mid+1
            else: hi = mid
        return lo
    
    def __call__(self, x, y):
        # local lookups
        x_index, y_index, values = self.x_index, self.y_index, self.values

        i = self.bisect_left(x_index, x) - 1
        j = self.bisect_left(y_index, y) - 1

        x1, x2 = x_index[i:i + 2]
        y1, y2 = y_index[j:j + 2]
        z11, z12 = values[j][i:i + 2]
        z21, z22 = values[j + 1][i:i + 2]

        return (z11 * (x2 - x) * (y2 - y) +
                z21 * (x - x1) * (y2 - y) +
                z12 * (x2 - x) * (y - y1) +
                z22 * (x - x1) * (y - y1)) / ((x2 - x1) * (y2 - y1))

if __name__ == "__main__":
    table = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=((100.05, 200.0), (150.0, 250.0))
        )
    print(table(4.95, 4.95))





