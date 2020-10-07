package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1225 {
	static String result;
	static int[] array = new int[8];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			Integer.parseInt(br.readLine());
			
			int min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				array[i] = Integer.parseInt(st.nextToken());
				if(min > array[i]) {
					min = array[i];
				}
			}
			
			if(min == 0) {
				result = result(0);
			} else {
				result = result(solve(min));
			}
			
			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
	
	static int solve(int min) {
		int idx = 0;
		int cnt = 1;
		while(true) {
			array[idx] -= cnt; 
			if(array[idx] < 0) {
				array[idx++] = 0;
				break;
			}
			
			cnt++;
			if(cnt > 5) cnt = 1;
			idx++;
			if(idx > 7) idx = 0;
			System.out.println("idx : " + idx + ", cnt : " + cnt + " : " +  result(0));
		}
		
		return idx;
	}
	
	static String result(int idx) {
		StringBuilder sb = new StringBuilder();
		for (int i = idx, leng = idx + 8; i < leng; i++) {
			sb.append(array[i % 8] + " ");
		}
		return sb.toString();
	}
}
