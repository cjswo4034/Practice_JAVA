package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// gold 1
public class 책페이지_1019 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        
        long[] res = solution(n);
        for (long num : res) sb.append(num + " ");
        
        System.out.println(sb);
    }
    
    private static long[] solution(int num) {
		int len = String.valueOf(num).length();
		
		long[] count = new long[10];
		for (int i = 0, size = (int)Math.pow(10, len - 1); i < len; i++, size /= 10) {
			int l = num / (size * 10);
			int m = num / size % 10;
			int r = num % size;
			
			for (int j = 0; j < 10; j++) {
				count[j] += l * size;
				if (j < m) count[j] += size;
			}
			
			count[m] += r + 1;
			count[0] -= size;
		}
		
		return count;
	}
}
