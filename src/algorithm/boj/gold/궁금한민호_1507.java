package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 궁금한민호_1507 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine()), answer = 0;
        int[][] arr = new int[n][n];
        boolean[][] res = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        boolean flag = false;
        THIS: for (int mid = 0; mid < n; mid++) {
			for (int from = 0; from < n; from++) {
				for (int to = from + 1; to < n; to++) {
					if (from == mid || to == mid || from == to) continue;
					if (arr[from][to] > arr[from][mid] + arr[mid][to]) {
						flag = true;
						break THIS;
					}
					if (arr[from][to] == arr[from][mid] + arr[mid][to])
						res[from][to] = true;
				}
			}
		}
        
        if (flag) System.out.println(-1);
        else {
	        for (int i = 0; i < n; i++) {
	        	for (int j = i + 1; j < n; j++) {
					if (!res[i][j]) answer += arr[i][j];
				}
			}
	        System.out.println(answer);
        }
	}
}
