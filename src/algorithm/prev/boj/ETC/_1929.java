package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1929 {
	static boolean check[] = new boolean[1000001];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		check[1] = true;

		for (int i = 2; i <= m; i++) {
			if (check[i])
				continue;
			for (int j = i * 2; j <= m; j += i) {
				check[j] = true;
			}
			if(i >= n)
				bw.write(i + "\n");
		}
		
		bw.flush();
	}
}
