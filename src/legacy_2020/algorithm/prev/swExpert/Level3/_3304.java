package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3304 {
	static char[] chs1, chs2;
	static int[][] array;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			String[] input = br.readLine().split(" ");
			if(input[1].length() > input[0].length()) {
				String tmp = input[0];
				input[0] = input[1];
				input[1] = tmp;
			}
			chs1 = input[0].toCharArray();
			chs2 = input[1].toCharArray();
			array = new int[chs1.length + 1][chs2.length + 1];
			
			result = 0;
			for (int i = 0; i < chs1.length; i++) {
				for (int j = 0; j < chs2.length; j++) {
					if(chs1[i] == chs2[j]) {
						array[i + 1][j + 1] = array[i][j] + 1;
						continue;
					}
					array[i + 1][j + 1] = Math.max(array[i][j + 1], array[i + 1][j]);
					result = Math.max(result, array[i + 1][j + 1]);
				}
			}
			
			sb.append(result);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
