package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/// https://m.blog.naver.com/PostView.nhn?blogId=1ilsang&logNo=221461026692&proxyReferer=https%3A%2F%2Fwww.google.com%2F
public class _5607_x {
	static int n, r;
	static long [] num;
	static final long VALUE = 1234567891l;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			num = new long[n + 1];
			num[0] = 1;
			for (int i = 1; i <= n; i++) {
				num[i] = num[i - 1] * i % VALUE;
			}
			
			BigInteger P = BigInteger.valueOf(VALUE);
			BigInteger A = BigInteger.valueOf(num[n]);
			BigInteger B = BigInteger.valueOf(num[r]).modInverse(P).remainder(P);
			BigInteger C = BigInteger.valueOf(num[n - r]).modInverse(P).remainder(P);
			
			sb.append(A.multiply(B).multiply(C).remainder(P));
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
