package algorithm.prev.programmers.Level2;

import java.util.*;

public class _42587 {
    public static void main(String[] args){
        System.out.println("#" + 1 + " : " + solution(new int[]{2,1,3,2}, 2));
        System.out.println("#" + 1 + " : " + solution(new int[]{1,1,9,1,1,1}, 0));
    }

    public static int solution(int[] priorities, int location) {
        Queue<Integer> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < priorities.length; i++) {
            q.add(i);
            pq.add(priorities[i]);
        }

        int answer = 1, curr;
        while(!q.isEmpty()){
            curr = q.poll();
            if (priorities[curr] == pq.peek()) pq.poll();
            else {
                q.add(curr);
                continue;
            }
            if (location == curr) break;
            answer++;
        }
        return answer;
    }
}