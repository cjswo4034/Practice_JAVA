package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 부분수열의합2_1208 {
	static int n, k, sum, threshold;
	static int[] arr;
	static long result;
	static Map<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) 
        	arr[i] = Integer.parseInt(st.nextToken());
        
        threshold = n / 2;
        dfs(0);
        dfs2(threshold);
        if (k == 0) result--;
        System.out.println(result);
	}
	
	static void dfs(int pos) {
		if (pos == threshold) {
			map.put(sum, map.getOrDefault(sum, 0) + 1);
			return;
		}
		
		dfs(pos + 1);
		sum += arr[pos];
		dfs(pos + 1);
		sum -= arr[pos];
	}
	
	static void dfs2(int pos) {
		if (pos == n) {
			result += map.getOrDefault(k - sum, 0);
			return;
		}
		
		dfs2(pos + 1);
		sum += arr[pos];
		dfs2(pos + 1);
		sum -= arr[pos];
	}
}
