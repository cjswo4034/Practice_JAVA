package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _1715 {	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		for(int n = 0; n < N ; n++) {
			q.add(Integer.parseInt(br.readLine()));
		}
		
		for(int n = 0; n < N - 1 ; n++) {
			if(!q.isEmpty()) {
				int one = q.poll();
				int two = q.poll();
				q.add(one + two);
				ans += one + two;
			}
		}
		
		System.out.println(ans);
	}
}
