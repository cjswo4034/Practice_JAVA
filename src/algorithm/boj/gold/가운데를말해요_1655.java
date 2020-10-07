package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class 가운데를말해요_1655 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        
        // maxHeap : 중간값과 같거나 작은 값
        // minHeap : 중간값보다 큰 값
        
        // mid: maxHeap.peek()
        
        // if (tmp <= mid) max.add
        // else min.add
        
        // 민힙의 크기와 맥스힙의 크기는 맥스힙이 하나 더 크거나 같아야 한다.
        
        // max.size > min.size + 1 -> min.add(max.poll)
        // min.size > max.size -> max.add(min.poll)
        
        maxHeap.add(Integer.parseInt(br.readLine()));
        sb.append(maxHeap.peek()).append("\n");
        for (int i = 1, tmp; i < n; i++) {
			tmp = Integer.parseInt(br.readLine());
			
			if (tmp <= maxHeap.peek()) maxHeap.add(tmp);
			else minHeap.add(tmp);
				
			if (maxHeap.size() > minHeap.size() + 1) minHeap.add(maxHeap.poll());
			if (minHeap.size() > maxHeap.size()) maxHeap.add(minHeap.poll());
			
			sb.append(maxHeap.peek()).append("\n");
		}
        System.out.println(sb);
	}
}
