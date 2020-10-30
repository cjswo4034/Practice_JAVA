package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _5432 {
	static int result, cnt;
	static char[] input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input = br.readLine().toCharArray();
			
			cnt = 0;
			result = 0;
			for (int i = 0; i < input.length; i++) {
				if(input[i] == '(') {
					cnt++;
				} else {
					cnt--;
					if(input[i - 1] == '(')
						result += cnt;
					else
						result++;
				}
			}
			
			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
}
