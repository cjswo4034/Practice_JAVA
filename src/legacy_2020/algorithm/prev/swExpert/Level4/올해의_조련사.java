package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 올해의_조련사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		char[] input;
		StringBuffer result;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1, n; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			n = Integer.parseInt(br.readLine());
			result = new StringBuffer();
			input = new char[n];
			for (int i = 0; i < n; i++) {
				input[i] = br.readLine().charAt(0);
			}
			
			int l = 0, r = input.length - 1;
			while(l < r) {
				if (input[l] == input[r])
					if (getPriority(l, r, input)) result.append(input[l++]);
					else result.append(input[r--]);
				else if (input[l] < input[r])
					result.append(input[l++]);
				else 
					result.append(input[r--]);
			}
			if (l == r) result.append(input[l]);
			sb.append(result.toString()).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static boolean getPriority(int l, int r, char[] input) {
		if (l > r) return true;
		if (input[l] < input[r])
			return true;
		else if (input[l] > input[r])
			return false;
		return getPriority(++l, --r, input);
	}
}
