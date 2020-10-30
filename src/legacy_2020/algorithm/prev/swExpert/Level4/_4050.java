package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _4050 {
	static int n, cnt, result;
	static int[] array;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			array = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());	
			}
			
			Arrays.sort(array);

			cnt = 3;
			result = 0;
			for (int i = n - 1; i >= 0; i--) {
				if(cnt == 1) {
					cnt = 3;
					continue;
				}
				result += array[i];
				cnt--;
			}

			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}

}
