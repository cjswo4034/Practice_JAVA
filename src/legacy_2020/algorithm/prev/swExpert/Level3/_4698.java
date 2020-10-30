package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4698 {
	static boolean[] check;
	static int a, b, d, total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		init();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			total = 0;
			
			for(int i = a == 1 ? 2 : a ; i <= b; i++) {
				if(!check[i] && func(i)) {
					total++;
				}
			}
			
			sb.append(total);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static boolean func(int num) {
		while(num > 0) {
			if(num % 10 == d) return true;
			num /= 10;
		}
		return false;
	}

	static void init() {
		check = new boolean[1000001];
		for (int i = 2; i <= 1000000; i++) {
			if (!check[i]) {
				for (int j = i * 2; j <= 1000000; j += i) {
					check[j] = true;
				}
			}
		}
	}
}
