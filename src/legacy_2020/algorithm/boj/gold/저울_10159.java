package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 저울_10159 {
	static final int INF = 1000000000;
	
	static int n, m;
	static boolean[][] map;
	
	public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new boolean[n + 1][n + 1];
        
        for (int i = 0, from, to; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	from = Integer.parseInt(st.nextToken());
        	to = Integer.parseInt(st.nextToken());
        	map[from][to] = true;
		}
        
        for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (!map[i][j]) map[i][j] = map[i][k] && map[k][j];
				}
			}
		}
        
        for (int i = 1; i <= n; i++) sb.append(getCount(i)).append("\n");
        System.out.println(sb);
	}
	
	static int getCount(int value) {
		int res = 0;
		for (int i = 1; i <= n; i++) {
			if (map[i][value] || map[value][i]) res++; 
		}
		return n - res - 1;
	}
	
	// 시간초과
	static void dfs(int root, int current) {
		for (int i = 1; i <= n; i++) {
			if (map[current][i]) {
				map[root][i] = true;
				dfs(root, i);
			}
		}
	}
}
