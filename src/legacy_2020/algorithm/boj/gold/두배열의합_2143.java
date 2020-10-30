package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 두배열의합_2143 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int m1, m2;
        long t, answer = 0;
        long[] arr1, arr2, sum1, sum2;
        Long key;
        Integer value;
        Map<Long, Integer> map = new HashMap<>();
        
        t = Integer.parseInt(br.readLine());
        
        m1 = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        arr1 = new long[m1];
        sum1 = new long[m1 + 1];
        for (int i = 0; i < m1; i++) {
        	arr1[i] = Integer.parseInt(st.nextToken());
        	sum1[i + 1] = sum1[i] + arr1[i];
        }
        
        m2 = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr2 = new long[m2];
        sum2 = new long[m2 + 1];
        for (int i = 0; i < m2; i++) {
        	arr2[i] = Integer.parseInt(st.nextToken());
        	sum2[i + 1] = sum2[i] + arr2[i];
        }
        
        for (int i = 0; i <= m2; i++) {
			for (int j = i + 1; j <= m2; j++) {
				key = sum2[j] - sum2[i];
				map.compute(key, (k, v) -> v == null ? 1 : v + 1);
			}
		}
        
        for (int i = 0; i <= m1; i++) {
			for (int j = i + 1; j <= m1; j++) {
				key = sum1[j] - sum1[i];
				value = map.get(t - key);
				if (value != null) answer += value; 
			}
		}
        
        System.out.println(answer);
	}
}
