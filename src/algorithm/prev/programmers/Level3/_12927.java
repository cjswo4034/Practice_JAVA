package algorithm.prev.programmers.Level3;

import java.util.Collections;
import java.util.PriorityQueue;

public class _12927 {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{4,3,3}));
        System.out.println(solution(1, new int[]{2,1,2}));
        System.out.println(solution(3, new int[]{1,1}));
    }

    public static long solution(int n, int[] works) {
        long answer = 0, tmp;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : works){
            pq.add(num);
        }

        while (n-- > 0){
            pq.add(pq.poll() - 1);
        }

        while (!pq.isEmpty()){
            tmp = pq.poll();
            if (tmp < 0) continue;
            answer += (tmp * tmp);
        }

        return answer;
    }
}
