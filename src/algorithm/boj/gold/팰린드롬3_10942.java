package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 팰린드롬3_10942 {
	static int n;
	static int[] arr;
	static int[][] mem;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        mem = new int[n + 1][n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	mem[i][i] = 1;
        	if (arr[i - 1] == arr[i]) mem[i - 1][i] = 1;
        }
        
        // a ~ b가 팰린드롬이면 a - i ~ b - i도 팰린드롬이다.
        // -> a - i ~ b - i가 팰린드롬이면 a ~ b도 팰린드롬이다.
        
        // mem[i][j] => 부분 문자열 i부터 j가 팰린드롬인가?
        
        for (int size = 3; size <= n; size++) {
			for (int pos = 0; pos + size - 1 <= n; pos++) {
				if (arr[pos] == arr[pos + size - 1]) {
					mem[pos][pos + size - 1] = mem[pos + 1][pos + size - 2];
				}
			}
		}
        
        int m = Integer.parseInt(br.readLine());
        for (int i = 0, a, b; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(mem[a][b]).append("\n");
		}
        System.out.println(sb);
	}
}
