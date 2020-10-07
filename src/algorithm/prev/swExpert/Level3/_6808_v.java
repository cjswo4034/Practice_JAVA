package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _6808_v {
	static int result;
	static int array1[], array2[];
	static boolean check[];

	static int max = 362880;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t);

			result = 0;
			array1 = new int[9];
			array2 = new int[9];
			check = new boolean[19];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				array1[i] = Integer.parseInt(st.nextToken());
				check[array1[i]] = true;
			}

			for (int i = 1, cnt = 0; i <= 18; i++) {
				if (!check[i]) {
					array2[cnt++] = i;
				}
			}

			Arrays.sort(array2);

			do {
				if (calc()) {
					result++;
				}
			} while (next_permutation());
			
			sb.append(" " + result + " " + (max - result));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static boolean next_permutation() {
		int i = 8, j = 8;
		while (i > 0 && array2[i - 1] >= array2[i]) i--;
		if (i <= 0) return false; // 마지막 순열

		while (array2[j] <= array2[i - 1]) j--;
		swap(i - 1, j);
		
		j = 8;
		while (i < j) {
			swap(i, j);
			i++;
			j--;
		}

		return true;
	}

	static boolean calc() {
		int l = 0, r = 0;
		for (int i = 0; i < 9; i++) {
			if(array1[i] > array2[i]) {
				l += array1[i] + array2[i];
			}else if(array1[i] < array2[i]) {
				r += array1[i] + array2[i];
			}
		}

		return l > r ? true : false;
	}

	static void swap(int i, int j) {
		int tmp = array2[i];
		array2[i] = array2[j];
		array2[j] = tmp;
	}
}
