package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _11286 {
	
	static class R{
		int abs;
		int num;
		R(int abs, int num){
			this.abs = abs;
			this.num = num;
		}
	}
	
	static class Compare implements Comparator<R>{
		public int compare(R one, R two) {
			if(one.abs == two.abs)
				return one.num < two.num ? -1 : 1;
			return one.abs < two.abs ? -1 : 1;
		}
	} 

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Compare cmp = new Compare();
		PriorityQueue<R> q = new PriorityQueue<R>(1, cmp);
		int N = Integer.parseInt(br.readLine());
		
		for(int n = 0; n < N ; n++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(q.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(q.poll().num);
				}
			} else {
				q.add(new R(Math.abs(num), num));
			}
		}
	}
}
