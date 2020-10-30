package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Gold 3
public class 백터매칭_1007 {
	static int[][] arr;
	static int n, x, y;
	static double result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		boolean[] visited;
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			result = Double.MAX_VALUE;
			
			x = y = 0;
			visited = new boolean[n];
			arr = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
				x += arr[i][0];
				y += arr[i][1];
			}
			
			System.out.println(dfs(0, 0, 0, 0));
			System.out.println(dfs(visited, 0, 0));
		}
	}
	
	private static double dfs(boolean[] visited, int cnt, int pos) {
		if (cnt == n / 2) {
			long sumX, sumY;
			sumX = sumY = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					sumX += arr[i][0];
					sumY += arr[i][1];
				} else {
					sumX -= arr[i][0];
					sumY -= arr[i][1];
				}
			}
			return Math.sqrt(sumX * sumX + sumY * sumY);
		}
		
		double ans = Double.MAX_VALUE;
		if (pos == n) return ans;
		
		ans = Math.min(ans, dfs(visited, cnt, pos + 1));
		visited[pos] = true;
		ans = Math.min(ans, dfs(visited, cnt + 1, pos + 1));
		visited[pos] = false;
		
		return ans;
	}
	
	// 더 빠름
	private static double dfs(int count, int pos, int sumX, int sumY) {
		if (count == n / 2) {
			return distance(x - sumX - sumX, y - sumY - sumY);
		}
		
		double ans = Double.MAX_VALUE;
		for (; pos < n; pos++) {
			ans = Math.min(ans, dfs(count + 1, pos + 1, sumX + arr[pos][0], sumY + arr[pos][1]));
		}
		return ans;
	}
	
	private static double distance(long x, long y) {
		return Math.sqrt(x * x + y * y);
	}
}
