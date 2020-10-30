package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1039 {
	static int n, k, state, leng, ans = -1, max;
	static boolean visited[];
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		while(!q.isEmpty() && state < k) {
			state++;
			int size = q.size();
			visited = new boolean[(int)Math.pow(10,leng) + 1];
			while(size-- > 0) {
				int current = q.remove();
				for(int i = 1; i <= leng ; i++) {
					for(int j = i + 1; j <= leng ; j++) {
						int next = change(current, i, j);
						if(next < (int)Math.pow(10,leng - 1))
							continue;
						if(!visited[next]) {
							if(state == k) {
								ans = Math.max(ans, next);
							}
							visited[next] = true;
							q.add(next);
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String tmp = st.nextToken();
		n = Integer.parseInt(tmp);
		k = Integer.parseInt(st.nextToken());
		leng = tmp.length();
				
		bfs();
		
		System.out.println(ans);
	}
	
	static int change(int num, int from, int to) {
		int from_num = num / (int)Math.pow(10, leng - from) % 10;
		int to_num = num / (int)Math.pow(10, leng - to) % 10;
		int result = num;
		result = result - from_num * (int)Math.pow(10, leng - from);
		result = result - to_num * (int)Math.pow(10, leng - to);
		result = result + to_num * (int)Math.pow(10, leng - from);
		result = result + from_num * (int)Math.pow(10, leng - to);
		
		return result;
	}
}
