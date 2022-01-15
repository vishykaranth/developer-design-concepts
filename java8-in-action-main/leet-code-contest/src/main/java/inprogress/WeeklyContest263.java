package inprogress;

import java.util.Arrays;

public class WeeklyContest263 {

    public boolean areNumbersAscending(String t) {
        String[] sp = t.trim().split(" ");
        int num = -1;
        for (String s : sp) {
            try {
                int u = Integer.parseInt(s);
                if (num >= u) return false;
                num = u;
            } catch (Exception e) {
            }
        }
        return true;
    }

    class Bank {
        long[] bal;
        int n;

        public Bank(long[] balance) {
            bal = balance;
            n = balance.length;
        }

        public boolean transfer(int account1, int account2, long money) {
            account1--;
            account2--;
            if (!(0 <= account1 && account1 < n)) return false;
            if (!(0 <= account2 && account2 < n)) return false;
            if (!(bal[account1] >= money)) return false;
            bal[account1] -= money;
            bal[account2] += money;
            return true;
        }

        public boolean deposit(int account, long money) {
            account--;
            if (!(0 <= account && account < n)) return false;
            bal[account] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            account--;
            if (!(0 <= account && account < n)) return false;
            if (!(bal[account] >= money)) return false;
            bal[account] -= money;
            return true;
        }
    }

    /**
     * Your Bank object will be instantiated and called as such:
     * Bank obj = new Bank(balance);
     * boolean param_1 = obj.transfer(account1,account2,money);
     * boolean param_2 = obj.deposit(account,money);
     * boolean param_3 = obj.withdraw(account,money);
     */


    public int countMaxOrSubsets(int[] a) {
        int n = a.length;
        int all = 0;
        for (int v : a) {
            all |= v;
        }
        int ct = 0;
        for (int i = 0; i < 1 << n; i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                if (i << ~j < 0) {
                    s |= a[j];
                }
            }
            if (s == all) ct++;
        }
        return ct;
    }

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int m = edges.length;
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = edges[i][0] - 1;
            to[i] = edges[i][1] - 1;
        }
        int[][] g = packU(n, from, to);
        MinHeapL h = new MinHeapL(2 * n);
        h.add(0, 0L);
        long[] ds = new long[2 * n];
        Arrays.fill(ds, Long.MAX_VALUE / 2);

        while (h.size() > 0) {
            int cur = h.argmin();
            long d = h.remove(cur);
            int pos = cur % n;
            for (int e : g[pos]) {
                long q = d % (2 * change);
                long nd = (q < change ? d : (d / (2 * change) + 1) * (2 * change)) + time;
                if (nd < ds[e]) {
                    h.update(e, ds[e] = nd);
                } else if (nd == ds[e]) {
                } else if (nd < ds[e + n]) {
                    h.update(e + n, ds[e + n] = nd);
                }
            }
        }
        return (int) ds[2 * n - 1];
    }

    public int[][] packU(int n, int[] from, int[] to) {
        return packU(n, from, to, from.length);
    }

    public int[][] packU(int n, int[] from, int[] to, int sup) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < sup; i++) p[from[i]]++;
        for (int i = 0; i < sup; i++) p[to[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < sup; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }


    public class MinHeapL {
        public long[] a;
        public int[] map;
        public int[] imap;
        public int n;
        public int pos;
        public long INF = Long.MAX_VALUE;

        public MinHeapL(int m) {
            n = Integer.highestOneBit((m + 1) << 1);
            a = new long[n];
            map = new int[n];
            imap = new int[n];
            Arrays.fill(a, INF);
            Arrays.fill(map, -1);
            Arrays.fill(imap, -1);
            pos = 1;
        }

        public long add(int ind, long x) {
            int ret = imap[ind];
            if (imap[ind] < 0) {
                a[pos] = x;
                map[pos] = ind;
                imap[ind] = pos;
                pos++;
                up(pos - 1);
            }
            return ret != -1 ? a[ret] : x;
        }

        public long update(int ind, long x) {
            int ret = imap[ind];
            if (imap[ind] < 0) {
                a[pos] = x;
                map[pos] = ind;
                imap[ind] = pos;
                pos++;
                up(pos - 1);
            } else {
                a[ret] = x;
                up(ret);
                down(ret);
            }
            return x;
        }

        public long remove(int ind) {
            if (pos == 1) return INF;
            if (imap[ind] == -1) return INF;

            pos--;
            int rem = imap[ind];
            long ret = a[rem];
            map[rem] = map[pos];
            imap[map[pos]] = rem;
            imap[ind] = -1;
            a[rem] = a[pos];
            a[pos] = INF;
            map[pos] = -1;

            up(rem);
            down(rem);
            return ret;
        }

        public long min() {
            return a[1];
        }

        public int argmin() {
            return map[1];
        }

        public int size() {
            return pos - 1;
        }

        private void up(int cur) {
            for (int c = cur, p = c >>> 1; p >= 1 && a[p] > a[c]; c >>>= 1, p >>>= 1) {
                long d = a[p];
                a[p] = a[c];
                a[c] = d;
                int e = imap[map[p]];
                imap[map[p]] = imap[map[c]];
                imap[map[c]] = e;
                e = map[p];
                map[p] = map[c];
                map[c] = e;
            }
        }

        private void down(int cur) {
            for (int c = cur; 2 * c < pos; ) {
                int b = a[2 * c] < a[2 * c + 1] ? 2 * c : 2 * c + 1;
                if (a[b] < a[c]) {
                    long d = a[c];
                    a[c] = a[b];
                    a[b] = d;
                    int e = imap[map[c]];
                    imap[map[c]] = imap[map[b]];
                    imap[map[b]] = e;
                    e = map[c];
                    map[c] = map[b];
                    map[b] = e;
                    c = b;
                } else {
                    break;
                }
            }
        }
    }
}
