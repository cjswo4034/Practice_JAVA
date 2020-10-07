package algorithm.prev.programmers.level4;

import java.util.*;

public class 사이클제거 {
    public static void main(String[] args) {
        사이클제거 prob = new 사이클제거();
        System.out.println(prob.solution(5, new int[][]{}));
    }

    public int solution(int n, int[][] edges) {
        List<Integer>[] lists = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) lists[i] = new ArrayList<>();
        for (int[] ints : edges) {
            lists[ints[0]].add(ints[1]);
            lists[ints[1]].add(ints[0]);
        }
        boolean[] irremovable = new boolean[n + 1];
        for (List<Integer> list : lists) {
            if (list.size() == 1) {
                irremovable[list.get(0)] = true;
            }
        }

        int diff = Math.abs(n - edges.length) + 1; // 지워야 하는 갯수
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (lists[i].size() > diff && !irremovable[i]) answer += i;
        }
        return answer;
    }
}
