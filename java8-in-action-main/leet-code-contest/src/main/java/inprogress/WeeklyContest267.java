package inprogress;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WeeklyContest267 {
    //    https://leetcode.com/contest/weekly-contest-267/problems/time-needed-to-buy-tickets/
    public int timeRequiredToBuy(int[] tickets, int k) {
        int n = tickets.length;
        int ans = 0;
        out:
        while (tickets[k] > 0) {
            for (int i = 0; i < n; i++) {
                if (tickets[i] > 0) {
                    tickets[i]--;
                    ans++;
                    if (i == k && tickets[i] == 0) break out;
                }
            }
        }
        return ans;
    }

    //    https://leetcode.com/contest/weekly-contest-267/problems/process-restricted-friend-requests/
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        int Q = requests.length;
        boolean[] ans = new boolean[Q];
        DJSet ds = new DJSet(n);
        outer:
        for (int i = 0; i < Q; i++) {
            int[] r = requests[i];
            if (ds.equiv(r[0], r[1])) {
                ans[i] = true;
                continue;
            }
            for (int[] res : restrictions) {
                if (ds.equiv(r[0], res[0]) && ds.equiv(r[1], res[1])) continue outer;
                if (ds.equiv(r[0], res[1]) && ds.equiv(r[1], res[0])) continue outer;
            }
            ans[i] = true;
            ds.union(r[0], r[1]);
        }
        return ans;
    }

    public class DJSet {
        public int[] upper;

        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
        }

        public int root(int x) {
            return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
        }

        public boolean equiv(int x, int y) {
            return root(x) == root(y);
        }

        public boolean union(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (upper[y] < upper[x]) {
                    int d = x;
                    x = y;
                    y = d;
                }
                upper[x] += upper[y];
                upper[y] = x;
            }
            return x == y;
        }

        public int count() {
            int ct = 0;
            for (int u : upper) if (u < 0) ct++;
            return ct;
        }

        public int[][] toBucket() {
            int n = upper.length;
            int[][] ret = new int[n][];
            int[] rp = new int[n];
            for (int i = 0; i < n; i++) if (upper[i] < 0) ret[i] = new int[-upper[i]];
            for (int i = 0; i < n; i++) {
                int r = root(i);
                ret[r][rp[r]++] = i;
            }
            return ret;
        }
    }

    //    https://leetcode.com/contest/weekly-contest-267/problems/reverse-nodes-in-even-length-groups/
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode ret = head;
        ListNode cur = null;
        for (int k = 1; head != null; k++) {
            Deque<ListNode> q = new ArrayDeque<>();
            for (int i = 0; i < k && head != null; i++) {
                q.addLast(head);
                ListNode next = head.next;
                head.next = null;
                head = next;
            }
            if (q.size() % 2 == 1) {
                while (!q.isEmpty()) {
                    ListNode x = q.pollFirst();
                    if (cur != null) cur.next = x;
                    cur = x;
                }
            } else {
                while (!q.isEmpty()) {
                    ListNode x = q.pollLast();
                    if (cur != null) cur.next = x;
                    cur = x;
                }
            }
        }
        return ret;
    }

    //    https://leetcode.com/contest/weekly-contest-267/problems/decode-the-slanted-ciphertext/
    public String decodeCiphertext(String encodedText, int n) {
        StringBuilder sb = new StringBuilder();
        int m = encodedText.length() / n;
        if (m == 0) return "";
        for (int d = 0; d < m; d++) {
            for (int i = 0; i < n && i + d < m; i++) {
                sb.append(encodedText.charAt(i * m + i + d));
            }
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
