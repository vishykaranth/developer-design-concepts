package inprogress;

public class WeeklyContest268 {
    //https://leetcode.com/contest/weekly-contest-268/problems/two-furthest-houses-with-different-colors/
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (colors[i] != colors[j]) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    //    https://leetcode.com/contest/weekly-contest-268/problems/watering-plants/
    public int wateringPlants(int[] plants, int capacity) {
        int can = capacity;
        int ret = plants.length;
        for (int i = 0; i < plants.length; i++) {
            int p = plants[i];
            if (can - p < 0) {
                can = capacity;
                ret += i * 2;
            }
            can -= p;
        }
        return ret;
    }

    //    https://leetcode.com/contest/weekly-contest-268/problems/range-frequency-queries/
    class RangeFreqQuery {
        int[][] b;

        public RangeFreqQuery(int[] arr) {
            b = makeBuckets(arr, 10001);
        }

        public int query(int left, int right, int value) {
            return lowerBound(b[value], right + 1) - lowerBound(b[value], left);
        }

        public int[][] makeBuckets(int[] a, int sup) {
            int n = a.length;
            int[][] bucket = new int[sup + 1][];
            int[] bp = new int[sup + 1];
            for (int i = 0; i < n; i++) bp[a[i]]++;
            for (int i = 0; i <= sup; i++) bucket[i] = new int[bp[i]];
            for (int i = n - 1; i >= 0; i--) bucket[a[i]][--bp[a[i]]] = i;
            return bucket;
        }


        public int lowerBound(int[] a, int v) {
            return lowerBound(a, 0, a.length, v);
        }

        public int lowerBound(int[] a, int l, int r, int v) {
            if (l > r || l < 0 || r > a.length) throw new IllegalArgumentException();
            int low = l - 1, high = r;
            while (high - low > 1) {
                int h = high + low >>> 1;
                if (a[h] >= v) {
                    high = h;
                } else {
                    low = h;
                }
            }
            return high;
        }

    }

    //    https://leetcode.com/contest/weekly-contest-268/problems/sum-of-k-mirror-numbers/
    public long kMirror(int k, int n) {
        long ans = 0;
        for (int len = 1; ; len++) {
            int x = (int) Math.pow(10, (len - 1) / 2);
            int y = (int) Math.pow(10, (len + 1) / 2);

            for (int i = x; i < y; i++) {
                long b = i;
                for (int j = len % 2 == 1 ? i / 10 : i; j > 0; j /= 10) {
                    b = b * 10 + j % 10;
                }
                String h = Long.toString(b, k);
                if (ispal(h.toCharArray())) {
                    ans += b;
                    if (--n == 0) {
                        return ans;
                    }
                }
            }
        }
    }

    public boolean ispal(char[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            if (a[i] != a[j]) return false;
        }
        return true;
    }
}
