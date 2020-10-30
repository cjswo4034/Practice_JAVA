package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 한솔이의_택배_아프바이트 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		Integer[] arr;
		int T = Integer.parseInt(br.readLine()), n;
		for (int t = 1, answer; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			n = Integer.parseInt(br.readLine());
			
			arr = new Integer[n];
			for (int i = 0; i < n; i++)
				arr[i] = Integer.parseInt(br.readLine());
			
			Arrays.sort(arr, (x, y) -> -Integer.compare(x, y));
			
			answer = 0;
			for (int l = 0, r = arr.length - 1, cnt; l < arr.length && l <= r; l++) {
				cnt = (int)Math.ceil(50.0 / arr[l]) - 1;
				if (cnt > 0) {
					if (cnt <= r- l) while (cnt-- > 0) r--;
					else break;
				}
				answer++;
			}
			
			sb.append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
