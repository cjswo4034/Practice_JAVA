package algorithm.prev.programmers.Level2;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _42626 {
    public static void main(String[] args){
        System.out.println("#" + 1 + " : " + solution(new int[]{12, 9, 10, 3, 2, 5,8,7,6,2,1}, 300));
        System.out.println("#" + 1 + " : " + solution2(new int[]{12, 9, 10, 3, 2, 5,8,7,6,2,1}, 300));
    }

    public static int solution(int[] input, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < input.length; i++) pq.add(input[i]);

        int cnt = 0;
        while(pq.size() > 1 && pq.peek() < k){
            int num = pq.poll() + pq.poll() * 2;
            pq.add(num);
            cnt++;
            System.out.println(Arrays.toString(pq.toArray()));
        }

        return pq.peek() > k ? cnt : -1;
    }

    public static int solution2(int[] input, int k) {
        int pos = 1;
        int[] minHeap = new int[(int)Math.pow(2, getLength(input.length)) + 1];
        for (int i = 0; i < input.length; i++) {
            minHeap[pos] = input[i];
            add(minHeap, pos++);
        }

        int cnt = 0;
        while(pos > 2 && minHeap[1] < k){
            int first = pop(minHeap, --pos);
            int second = pop(minHeap, --pos);
            int tmp = first + second * 2;
            minHeap[pos] = tmp;
            add(minHeap, pos++);
            cnt++;
        }

        return minHeap[1] >= k ? cnt : -1;
    }

    public static int pop(int[] minHeap, int pos){
        int value = minHeap[1];
        int index = 1;
        minHeap[index] = minHeap[pos];
        minHeap[pos--] = 0;
        if (pos <= 2 && minHeap[1] > minHeap[2]){
            if (pos != 1) swap(minHeap, 1, 2);
        }
        while((index * 2 + 1) <= pos){
            int l = minHeap[index * 2];
            int r = minHeap[index * 2 + 1];
            if (minHeap[index] < l && minHeap[index] < r) break;
            if (l < r){
                swap(minHeap, index * 2, index);
                index = index * 2;
            } else{
                swap(minHeap, index * 2 + 1, index);
                index = index * 2 + 1;
            }
        }

        return value;
    }

    public static void add(int[] minHeap, int pos){
        if (pos <= 0) return;
        if (minHeap[pos / 2] > minHeap[pos]){
            swap(minHeap, pos, pos / 2);
            add(minHeap, pos / 2);
        }
    }

    public static void swap(int[] minHeap, int pos, int target){
        int tmp = minHeap[pos];
        minHeap[pos] = minHeap[target];
        minHeap[target] = tmp;
    }

    public static int getLength(int num){
        int count = 1;
        while(Math.pow(2, count++) <= num);
        return --count;
    }
}