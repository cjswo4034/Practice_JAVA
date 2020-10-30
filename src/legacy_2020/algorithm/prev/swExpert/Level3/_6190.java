package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _6190 {
	static int result;
	static int[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			array = new int[n];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}

			result = 0;
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					int value = array[i] * array[j];
					if (value > result && func(value)) {
						result = value;
					}
				}
			}
			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
	}

	static boolean func(int num) {
		int tmp;

		while (num > 0) {
			tmp = num % 10;
			num /= 10;
			if (tmp < num % 10)
				return false;
		}

		return true;
	}
}
