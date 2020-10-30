package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3750 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			long input = Long.parseLong(br.readLine());
			
			sb.append(func(input));			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static int func(long num) {
		if(num < 10) return (int)num;
		
		int sum = 0;
		while(num > 0) {
			sum += (num % 10);
			num /= 10l;
		}
		
		return func(sum);
	}
}
