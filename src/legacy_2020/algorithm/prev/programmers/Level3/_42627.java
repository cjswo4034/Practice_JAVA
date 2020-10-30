package algorithm.prev.programmers.Level3;

import java.util.*;

public class _42627 {
    public static void main(String[] args) {
        _42627 prob = new _42627();
        System.out.println(prob.solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
    }

    public int solution(int[][] jobs){
        Arrays.sort(jobs, (arr1, arr2) -> {     // 작업 순서
            if (arr1[0] == arr2[0]) return arr1[1] - arr2[1];
            return arr1[0] - arr2[0];
        });

        Queue<int[]> q = new LinkedList<>(Arrays.asList(jobs));

        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> { // 현재 시간보다 앞선 작업 순서 중 가장 빨리 끝나는 작업
            if (arr1[1] == arr2[1]) return arr1[0] - arr2[0];
            return arr1[1] - arr2[1];
        });

        // 1. q에서 하나 제거
        // 2. time : 현재 작업 시간
        // 3. answer : 답변

        pq.add(q.poll());
        int time = 0, answer = 0;
        int[] curr;
        while (!pq.isEmpty()){
            curr = pq.poll();
            if (curr[0] > time) time += (curr[0] - time);
            time += curr[1];
            answer += time - curr[0];

            while (!q.isEmpty() && time > q.peek()[0]) pq.add(q.poll());
            if (pq.isEmpty() && !q.isEmpty()) pq.add(q.poll());
        }

        return answer / jobs.length;
    }
}