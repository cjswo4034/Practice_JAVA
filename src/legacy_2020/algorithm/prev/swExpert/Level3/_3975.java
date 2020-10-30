package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3975 {
	static int a, b, c, d;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			int mul = gcd(b, d) * b * d;
			
			a *= mul / b;
			c *= mul / d;
			
			if(a > c)
				sb.append("ALICE");
			else if(a < c)
				sb.append("BOB");
			else
				sb.append("DRAW");
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}

}
