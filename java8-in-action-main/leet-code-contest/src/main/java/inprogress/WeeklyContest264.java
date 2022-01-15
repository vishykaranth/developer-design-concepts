package inprogress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://leetcode.com/contest/weekly-contest-264/
public class WeeklyContest264 {

    public int countValidWords(String sentence) {
        String[] sp = sentence.trim().split(" +");
        int cct = 0;
        if (sp == null) return 0;
        outer:
        for (String s : sp) {
            char[] u = s.toCharArray();
            for (char c : u) {
                if (c >= '0' && c <= '9') continue outer;
            }
            int h = -1;
            for (int i = 0; i < u.length; i++) {
                if (u[i] == '-') {
                    if (i == 0 || i == u.length - 1 || h != -1) continue outer;
                    if (u[i - 1] >= 'a' && u[i + 1] <= 'z' && u[i - 1] <= 'z' && u[i + 1] >= 'a') {
                    } else {
                        continue outer;
                    }
                    h = i;
                }
                if ("!.,".indexOf(u[i]) >= 0) {
                    if (i < u.length - 1) continue outer;
                }
            }
            cct++;
        }
        return cct;
    }

    public int nextBeautifulNumber(int n) {
        String enigma = "1 1\n" +
                "2 22\n" +
                "3 122\n" +
                "4 212\n" +
                "5 221\n" +
                "6 333\n" +
                "7 1333\n" +
                "8 3133\n" +
                "9 3313\n" +
                "10 3331\n" +
                "11 4444\n" +
                "12 14444\n" +
                "13 22333\n" +
                "14 23233\n" +
                "15 23323\n" +
                "16 23332\n" +
                "17 32233\n" +
                "18 32323\n" +
                "19 32332\n" +
                "20 33223\n" +
                "21 33232\n" +
                "22 33322\n" +
                "23 41444\n" +
                "24 44144\n" +
                "25 44414\n" +
                "26 44441\n" +
                "27 55555\n" +
                "28 122333\n" +
                "29 123233\n" +
                "30 123323\n" +
                "31 123332\n" +
                "32 132233\n" +
                "33 132323\n" +
                "34 132332\n" +
                "35 133223\n" +
                "36 133232\n" +
                "37 133322\n" +
                "38 155555\n" +
                "39 212333\n" +
                "40 213233\n" +
                "41 213323\n" +
                "42 213332\n" +
                "43 221333\n" +
                "44 223133\n" +
                "45 223313\n" +
                "46 223331\n" +
                "47 224444\n" +
                "48 231233\n" +
                "49 231323\n" +
                "50 231332\n" +
                "51 232133\n" +
                "52 232313\n" +
                "53 232331\n" +
                "54 233123\n" +
                "55 233132\n" +
                "56 233213\n" +
                "57 233231\n" +
                "58 233312\n" +
                "59 233321\n" +
                "60 242444\n" +
                "61 244244\n" +
                "62 244424\n" +
                "63 244442\n" +
                "64 312233\n" +
                "65 312323\n" +
                "66 312332\n" +
                "67 313223\n" +
                "68 313232\n" +
                "69 313322\n" +
                "70 321233\n" +
                "71 321323\n" +
                "72 321332\n" +
                "73 322133\n" +
                "74 322313\n" +
                "75 322331\n" +
                "76 323123\n" +
                "77 323132\n" +
                "78 323213\n" +
                "79 323231\n" +
                "80 323312\n" +
                "81 323321\n" +
                "82 331223\n" +
                "83 331232\n" +
                "84 331322\n" +
                "85 332123\n" +
                "86 332132\n" +
                "87 332213\n" +
                "88 332231\n" +
                "89 332312\n" +
                "90 332321\n" +
                "91 333122\n" +
                "92 333212\n" +
                "93 333221\n" +
                "94 422444\n" +
                "95 424244\n" +
                "96 424424\n" +
                "97 424442\n" +
                "98 442244\n" +
                "99 442424\n" +
                "100 442442\n" +
                "101 444224\n" +
                "102 444242\n" +
                "103 444422\n" +
                "104 515555\n" +
                "105 551555\n" +
                "106 555155\n" +
                "107 555515\n" +
                "108 555551\n" +
                "109 666666\n" +
                "110 1224444";
        Scanner sc = new Scanner(enigma);
        for (int i = 0; i < 110; i++) {
            sc.nextInt();
            int v = sc.nextInt();
            if (v > n) return v;
        }
        return -1;
    }

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        nn = n;
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            g.get(parents[i]).add(i);
        }
        dfs(0, g);
        return num;
    }


    int nn;
    long ans = -1;
    int num = 0;

    int dfs(int cur, List<List<Integer>> g) {
        int ret = 0;
        long mul = 1;
        for (int e : g.get(cur)) {
            int res = dfs(e, g);
            ret += res;
            mul = mul * res;
        }
        if (ret < nn - 1) {
            mul *= (nn - 1 - ret);
        }
        long val = mul;
        if (val > ans) {
            ans = val;
            num = 1;
        } else if (val == ans) {
            num++;
        }
        return ret + 1;
    }

    public int minimumTime(int n, int[][] relations, int[] time) {
        int m = relations.length;
        int[] from = new int[m];
        int[] to = new int[m];
        for(int i = 0;i < m;i++){
            from[i] = relations[i][0]-1;
            to[i] = relations[i][1]-1;
        }
        int[][] g = packD(n, from, to);

        int[] ord = sortTopologically(g);
        long[] dp = new long[n];
        long ans = 0;
        for(int i = 0;i < n;i++){
            int cur = ord[i];
            dp[cur] += time[cur];
            for(int e : g[cur]){
                dp[e] = Math.max(dp[e], dp[cur]);
            }
            ans = Math.max(ans, dp[cur]);
        }
        return (int)ans;
    }

    public int[] sortTopologically(int[][] g)
    {
        int n = g.length;
        int[] ec = new int[n];
        for(int i = 0;i < n;i++){
            for(int to : g[i])ec[to]++;
        }
        int[] ret = new int[n];
        int q = 0;

        // sources
        for(int i = 0;i < n;i++){
            if(ec[i] == 0)ret[q++] = i;
        }

        for(int p = 0;p < q;p++){
            for(int to : g[ret[p]]){
                if(--ec[to] == 0)ret[q++] = to;
            }
        }
        // loop
        for(int i = 0;i < n;i++){
            if(ec[i] > 0)return null;
        }
        return ret;
    }


    public int[][] packD(int n, int[] from, int[] to) {
        return packD(n, from, to, from.length);
    }

    public int[][] packD(int n, int[] from, int[] to, int sup) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < sup; i++) p[from[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < sup; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
    }
}
