package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _5688 {
	static long n;
	static long[] array = new long[1000001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		init();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			n = Long.parseLong(br.readLine());
			
			sb.append(func2());
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() {
		for (int i = 1; i < array.length; i++) {
			array[i] = (long)i * i * i;
		}
	}
	static int func2() {
		int result = -1;
		/*for (int i = 1; i < array.length; i++) {
			if(array[i] == n)
				result = i;
		}*/
		
		int l = 1;
		int r = array.length;
		int m = (l + r) / 2;
		while(l <= r) {
			if(array[m] == n) {
				result = m;
				break;
			}
			else if(array[m] < n) {
				l = m + 1;
			} else {
				r = m - 1;
			}
			m = (l + r) / 2;
		}
		
		return result;
	}
	
	static int func() {
		int result = -1;
		int start = (int)Math.pow(n, 9.0/30.0);
		int leng = (int)Math.pow(n, 11.0/30.0);
		System.out.println(start + " : " + leng + " : " + (leng - start));
		for (int i = start; i <= leng; i++) {
			if((int)Math.pow(i, 3) == n) {
				result = i;
			}
		}
		return result;
	}
}
