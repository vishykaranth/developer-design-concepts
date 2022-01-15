package inprogress;

import java.util.*;

//https://leetcode.com/contest/weekly-contest-265/problems/smallest-index-with-equal-value/
//https://leetcode.com/contest/weekly-contest-265/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
//https://leetcode.com/contest/weekly-contest-265/problems/minimum-operations-to-convert-number/

public class WeeklyContest265 {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) return i;
        }
        return -1;
    }


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

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        List<Integer> a = new ArrayList<>();
        while (head != null) {
            a.add(head.val);
            head = head.next;
        }
        int n = a.size();
        List<Integer> inds = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (a.get(i - 1) < a.get(i) && a.get(i) > a.get(i + 1)) {
                inds.add(i);
            }
            if (a.get(i - 1) > a.get(i) && a.get(i) < a.get(i + 1)) {
                inds.add(i);
            }
        }
        if (inds.size() < 2) {
            return new int[]{-1, -1};
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < inds.size() - 1; i++) {
            min = Math.min(min, inds.get(i + 1) - inds.get(i));
        }
        int max = inds.get(inds.size() - 1) - inds.get(0);
        return new int[]{min, max};
    }

    public int minimumOperations(int[] nums, int start, int goal) {
        int[] ds = new int[1001];
        Arrays.fill(ds, 99999999);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        ds[start] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == goal) return ds[cur];
            for (int v : nums) {
                for (int ne : new int[]{cur + v, cur - v, cur ^ v}) {
                    if (ne >= 0 && ne <= 1000) {
                        if (ds[ne] > ds[cur] + 1) {
                            ds[ne] = ds[cur] + 1;
                            q.add(ne);
                        }
                    } else {
                        if (ne == goal) {
                            return ds[cur] + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
