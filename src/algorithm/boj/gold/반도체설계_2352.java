package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 반도체설계_2352 {
	
	public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int size = 1;
		int[] lb = new int[n + 1];
		int[] arr = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lb[1] = arr[1];
		for (int i = 2; i <= n; i++) {
			if (lb[size] < arr[i]) {
				lb[++size] = arr[i];
				continue;
			}
			
			int idx = lowerBound(lb, size, arr[i]);
			lb[idx] = arr[i];
		}
		
		System.out.println(size);
	}
	
	// lb에 들어갈 수 있는 num의 위치를 구한다.
	static int lowerBound(int[] lb, int size, int num) {
		int l = 1, r = size, m = (l + r) >> 1;
		while (l <= r) {
			m = (l + r) >> 1;
			
			if (lb[m] < num) l = m + 1;
			else r = m - 1;
		}
		
		if (lb[m] < num) m++;
		return m;
	}
	
	// dp
	static int solution(int n, BufferedReader br) throws Exception{
		int answer = 0;
        int[] dp = new int[n];
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Arrays.fill(dp, 1);
        
        for (int i = 0; i < n; i++) {
        	dp[i] = 1;
        	arr[i] = Integer.parseInt(st.nextToken());
        	for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					answer = Math.max(answer, dp[i]);
				}
			}
		}
        
        return answer;
	}
}
