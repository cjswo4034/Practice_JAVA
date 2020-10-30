package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _2004 {
	static long num(long val1, int val2) {
		long num = val1;
		long cnt = 0;
		
		while(num > 0) {
			num /= val2;
			cnt += num;
		}
		
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long two = num(n, 2) - num(m, 2) - num(n - m, 2);
		long five = num(n, 5) - num(m, 5) - num(n - m, 5);
		System.out.println(Math.min(two, five));
	}
}
