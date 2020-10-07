package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// gold1
public class 우수마을_1949 {
	static int n;
	static int[] arr;
	static int[][] dp;
	static boolean[] visited;
	static List<Integer>[] list, tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n][2];
		visited = new boolean[n];
		list = new ArrayList[n];
		tree = new ArrayList[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			dp[i][0] = dp[i][1] = -1;
			arr[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			list[from].add(to);
			list[to].add(from);
		}
		
		solution(0);
		System.out.println(Math.max(dp[0][0], dp[0][1]));
		System.out.println(Math.max(solution1(new boolean[n], true, 0), solution1(new boolean[n], false, 0)));
	}
	
	static void setChild(int curr) {
		visited[curr] = true;
		for (int child: list[curr]) {
			if (!visited[child]) {
				tree[curr].add(child);
				setChild(child);
			}
		}
	}
	
	static void solution(int root) {
		visited[root] = true;
		
		dp[root][0] = 0;
		dp[root][1] = arr[root];
		for (int next: list[root]) {
			if (visited[next]) continue;
			solution(next);
			dp[root][0] += Math.max(dp[next][0], dp[next][1]);
			dp[root][1] += dp[next][0];
		}
	}

	// 시간초과
	static int solution1(boolean[] visited, boolean prev, int pos) {
		visited[pos] = true;
		
		int res = 0;
		for (int next: list[pos]) {
			if (visited[next]) continue;
			int tmp = solution1(visited, false, next);
			if (!prev) tmp = Math.max(tmp, solution1(visited, true, next));
			res += tmp;
		}
		visited[pos] = false;
		return res += (prev ? arr[pos] : 0);
	}
}