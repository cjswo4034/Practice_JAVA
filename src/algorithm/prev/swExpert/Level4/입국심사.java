package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 입국심사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int n, m;
		int[] arr;
		String[] input;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			input = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);
			
			arr = new int[n];
			
			long l = 1, r = 0;
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(br.readLine());
				r = Math.max(r, arr[i]);
			}
			
			r = r * m;
			
			long mid = 0, sum = 0, ans = r;
			while (l <= r) {
				mid = (l + r) / 2;
				sum = getSum(arr, mid);
				
				if (m > sum) {
					l = mid + 1;
				} else {
					r = mid - 1;
					ans = Math.min(ans, mid);
				}
			}			
			
			sb.append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static long getSum(int[] arr, long value) {
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum = sum + (value / arr[i]);
		}
		
		return sum;
	}
}
