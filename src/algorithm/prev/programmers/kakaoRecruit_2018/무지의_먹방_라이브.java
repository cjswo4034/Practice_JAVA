package algorithm.prev.programmers.kakaoRecruit_2018;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 무지의_먹방_라이브 {
    public static void main(String[] args) {
        
        무지의_먹방_라이브 prob = new 무지의_먹방_라이브();
        System.out.println(prob.solution1(new int[]{3, 1, 2}, 5));
        System.out.println(prob.solution2(new int[]{2, 2, 2, 2}, 5));
    }

    // 1. 하나씩 뺀다
    // 2. 길이만큼 뺀다.
    // 3. 하한 * 길이만큼 뺀다.

    public int solution1(int[] arr, long k){
        Queue<Node> q = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) q.add(new Node(i + 1, arr[i]));

        Node node;
        while (k-- > 0 && !q.isEmpty()){
            node = q.poll();
            node.value--;
            if (node.value > 0) q.add(node);
        }

        if (q.isEmpty()) return -1;
        return q.poll().idx;
    }

    public int solution2(int[] arr, long k){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.value));
        for (int i = 0; i < arr.length; i++) pq.add(new Node(i + 1, arr[i]));

        int cnt = 0;
        while (k > 0 && !pq.isEmpty()){
            if (pq.size() > k) break;
            k -= pq.size();
            cnt++;
            while (!pq.isEmpty() && pq.peek().value == cnt) pq.poll();
        }

        PriorityQueue<Node> ans = new PriorityQueue<>(Comparator.comparingInt(node -> node.idx));
        ans.addAll(pq);
        while (k-- > 0 && !ans.isEmpty()) ans.poll();

        if (ans.isEmpty()) return -1;
        return ans.peek().idx;
    }

    class Node{
        int idx, value;

        Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", value=" + value +
                    '}';
        }
    }
}
