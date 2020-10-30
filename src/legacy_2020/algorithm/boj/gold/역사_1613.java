package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 역사_1613 {
	static int n, k, s;
	static int[][] dist;
	
	public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
        	Arrays.fill(dist[i], 1000000000);
        }
        
        for (int i = 0, p, c; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			dist[p][c] = 1;
		}
        
        for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
        
        s = Integer.parseInt(br.readLine());
        for (int i = 0, p, c; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if (dist[p][c] == 1000000000 && dist[c][p] == 1000000000) System.out.println(0);
			else if (dist[p][c] != 1000000000 && dist[p][c] > 0) System.out.println(-1);
			else System.out.println(1);
		}
	}
}
