package inprogress;

public class WeeklyContest266 {
//    https://leetcode.com/contest/weekly-contest-266/problems/count-vowel-substrings-of-a-string/
    public int countVowelSubstrings(String word) {
        int n = word.length();
        int ct = 0;
        for (int i = 0; i < n; i++) {
            int ptn = 0;
            for (int j = i; j < n; j++) {
                if ("aeiou".indexOf(word.charAt(j)) >= 0) {
                    ptn |= 1 << "aeiou".indexOf(word.charAt(j));
                } else {
                    break;
                }
                if (ptn == 31) ct++;
            }
        }
        return ct;
    }

//    https://leetcode.com/contest/weekly-contest-266/problems/vowels-of-all-substrings/
    public long countVowels(String word) {
        int n = word.length();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if ("aeiou".indexOf(word.charAt(i)) >= 0) {
                ans += (long) (i + 1) * (n - i);
            }
        }
        return ans;
    }


//    https://leetcode.com/contest/weekly-contest-266/problems/minimized-maximum-of-products-distributed-to-any-store/
    public int minimizedMaximum(int n, int[] quantities) {
        int low = 0, high = 100001;
        while (high - low > 1) {
            int h = high + low >> 1;
            long re = 0;
            for (int v : quantities) {
                re += (v + h - 1) / h;
            }
            if (re <= n) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

//    https://leetcode.com/contest/weekly-contest-266/problems/maximum-path-quality-of-a-graph/
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        int m = edges.length;
        int[] from = new int[m];
        int[] to = new int[m];
        int[] ws = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = edges[i][0];
            to[i] = edges[i][1];
            ws[i] = edges[i][2];
        }
        int[][][] g = packWU(n, from, to, ws);

        ans = 0;
        dfs(0, g, maxTime, 0, new int[n], values);
        return ans;
    }

    int ans;

    void dfs(int cur, int[][][] g, int rem, int s, int[] ved, int[] vs) {
        if (rem < 0) return;
        if (++ved[cur] == 1) {
            s += vs[cur];
        }
        if (cur == 0) {
            ans = Math.max(ans, s);
        }
        for (int[] e : g[cur]) {
            dfs(e[0], g, rem - e[1], s, ved, vs);
        }
        --ved[cur];
    }


    public int[][][] packWU(int n, int[] from, int[] to, int[] w) {
        return packWU(n, from, to, w, from.length);
    }

    public int[][][] packWU(int n, int[] from, int[] to, int[] w, int sup) {
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int i = 0; i < sup; i++) p[from[i]]++;
        for (int i = 0; i < sup; i++) p[to[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]][2];
        for (int i = 0; i < sup; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
            --p[to[i]];
            g[to[i]][p[to[i]]][0] = from[i];
            g[to[i]][p[to[i]]][1] = w[i];
        }
        return g;
    }
}
