package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 합이0인네정수_7453 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> map = new HashMap<>();
        StringTokenizer st;
        long answer = 0;
        
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        	arr[i][2] = Integer.parseInt(st.nextToken());
        	arr[i][3] = Integer.parseInt(st.nextToken());
		}
        
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map.compute(arr[i][2] + arr[j][3], (k, v) -> v == null ? 1 : v + 1);
			}
		}
        
        Integer tmp;
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmp = map.get(-arr[i][0] - arr[j][1]);
				answer += tmp == null ? 0 : tmp;
			}
		}
        
        System.out.println(answer);
	}
}
