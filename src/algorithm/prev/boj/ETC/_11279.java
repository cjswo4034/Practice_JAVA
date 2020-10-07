package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _11279 {
	static class Compare implements Comparator<Integer>{
		public int compare(Integer one, Integer two) {
			return two.compareTo(one);
		}
	} 

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Compare cmp = new Compare();
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(1, cmp);
		int N = Integer.parseInt(br.readLine());
		
		for(int n = 0; n < N ; n++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(q.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(q.poll());
				}
			} else {
				q.add(num);
			}
		}
	}
}
