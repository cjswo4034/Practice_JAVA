package algorithm.prev.programmers.Level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class _42628 {
    public static void main(String[] args) {
        _42628 prob = new _42628();
        System.out.println(Arrays.toString(prob.solution(new String[]{"I 16","D 1"})));
        System.out.println(Arrays.toString(prob.solution(new String[]{"I 7","I 5","I -5", "D -1"})));
        System.out.println(Arrays.toString(prob.solution(new String[]{"I 16","I -5643","D -1","D 1","D 1","I 123", "D -1"})));
    }

    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] answer = new int[2];

        String[] tmps;
        char cmd; int value;
        for (String str : operations){
            tmps = str.split(" ");
            cmd = tmps[0].charAt(0);
            value = Integer.parseInt(tmps[1]);

            if (cmd == 'I'){
                maxHeap.add(value);
                minHeap.add(value);
            } else {
                if (value > 0) minHeap.remove(maxHeap.poll());
                else maxHeap.remove(minHeap.poll());
            }
        }

        if (!maxHeap.isEmpty()){
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }

        return answer;
    }

    class Pair{
        int value;
        int idx;

        Pair(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", idx=" + idx +
                    '}';
        }
    }
}
