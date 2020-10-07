package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합_1806 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] sum = new int[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
			arr[i - 1] = Integer.parseInt(st.nextToken());
			sum[i] = arr[i - 1] + sum[i - 1];
		}

        int l = 0, r = n * 2, ans = 0;
        while (l < r) {
        	int mid = (l + r) >> 1;
        	int subTot = Integer.MAX_VALUE, tmp;
        	
        	for (int i = 0; i + mid <= n; i++) {
        		tmp = sum[i + mid] - sum[i];
				if (tmp >= s && subTot > tmp) subTot = tmp;
			}
        	
        	if (subTot < s || subTot == Integer.MAX_VALUE)
        		l = mid + 1;
        	else if (subTot >= s) {
        		r = mid;
        		ans = mid;
        	}
        }
        
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
	}
}