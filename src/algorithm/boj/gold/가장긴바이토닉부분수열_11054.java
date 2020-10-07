package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장긴바이토닉부분수열_11054 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int ans = 0;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        
        for (int i = 0; i < n; i++) {
			for (int j = 0, tmp; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp1[i] = Math.max(dp1[i], dp1[j] + 1);
				}
				
				tmp = n - i;
				if (arr[tmp + j] < arr[tmp]) {
					dp2[tmp] = Math.max(dp2[tmp], dp2[tmp + j] + 1);
				}
			}
		}
        
        if (n > 1 && arr[0] > arr[1]) {
        	dp2[0] = dp2[1] + 1;
        }
        
        ans = Math.max(dp1[n - 1], dp2[0]);
        for (int i = 0; i < n; i++) {
        	ans = Math.max(ans, dp1[i] + dp2[i] - 1);
		}
      
        System.out.println(Arrays.toString(dp1));
        System.out.println(Arrays.toString(dp2));
        System.out.println(ans);
	}
}
