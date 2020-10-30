package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보석도둑_1202 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> ps = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Pair> js = new PriorityQueue<>();
        long answer = 0;
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] b = new int[k];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	js.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
        
        for (int i = 0; i < k; i++) b[i] = Integer.parseInt(br.readLine());
        
        Arrays.parallelSort(b);
        
        for (int i = 0; i < k; i++) {
        	while (!js.isEmpty() && b[i] >= js.peek().m) ps.add(js.poll().v);
        	
        	if (!ps.isEmpty()) answer += ps.poll();
		}
        
        System.out.println(answer);
	}
	
	static class Pair implements Comparable<Pair>{
		int m, v;

		public Pair(int m, int v) {
			this.m = m;
			this.v = v;
		}
		
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(m, o.m);
		}
	}
}
