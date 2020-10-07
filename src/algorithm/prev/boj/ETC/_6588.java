package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _6588 {
	static boolean check[] = new boolean[1000001];
	static int num[] = new int[78497];
	static int cnt = 0;

	static void init() {
		check[1] = true;
		for (int i = 2; i <= 1000000; i++) {
			if (check[i])
				continue;
			for (int j = i * 2; j <= 1000000; j += i) {
				check[j] = true;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//StringTokenizer st = new StringTokenizer(br.readLine());

		init();
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			
			boolean flug = false;
			
			for(int i = 3, leng = (n / 2 + 1) ; i <= leng ; i += 2) {
				if(!check[n - i] && !check[i]) {
					bw.write(n + " = " + i + " + " + (n - i) + "\n");
					flug = true;
					break;
				}
			}
			
			if(!flug){
				bw.write("Goldbach's conjecture is wrong.\n");
			}
			bw.flush();
		}
	}
}
