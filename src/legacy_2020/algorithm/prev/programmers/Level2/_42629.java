package algorithm.prev.programmers.Level2;

import java.util.*;

public class _42629 {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{1, 5, 7, 10, 15}, new int[]{1, 2, 3, 4, 5}, 15));
    }

    public static int solution(int stock, int[] dates, int[] supplies, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int curr = stock, answer = 0, i;
        for (i = 0; i < dates.length; i++) {
            if (curr >= dates[i]){
                pq.add(supplies[i]);
            } else break;
        }

        while (!pq.isEmpty() && curr < k){
            curr += pq.poll();
            for (; i < dates.length; i++) {
                if (curr >= dates[i]){
                    pq.add(supplies[i]);
                } else break;
            }
            answer++;
        }

        return answer;
    }
}