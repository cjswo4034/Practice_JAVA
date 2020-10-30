package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _2696 {
	static int A[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q;
		StringTokenizer st;
		
		while (t-- > 0) {
			q = new PriorityQueue<>();
			int M = Integer.parseInt(br.readLine());
			A = new int[M + 1];
			int line = M / 10;
			
			for(int i = 0 ; i <= line ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 10 ; j++) {
					if(st.hasMoreTokens()) {
						A[i * 10 + j + 1] = Integer.parseInt(st.nextToken());
					}
				}
			}

			System.out.println((M + 1) / 2);
			
			for(int i = 1 ; i <= M ; i++) {
				q.add(A[i]);
				if(i % 20 == 0)
					System.out.println();
				if(i % 2 == 1) {
					for(int j = 1 ; j <= i ; j++) {
						if((i + 1) / 2 == j)
							System.out.print(q.poll() + " ");
						else
							q.poll();
					}
					for(int j = 1 ; j <= i ; j++) {
						q.add(A[j]);
					}
				}
			}
			
			System.out.println();
		}
	}
}
