package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _3032 {
	static int a, b;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			/*if (gcd(a, b) != 1) {
				bw.write("#" + t + " " + (-1) + "\n");
				continue;
			}

			result = new int[2];
			func(a, b);
			if(Math.abs(result[0] * a + result[1] * b) == 1)
				bw.write("#" + t + " " + result[0] + " " + result[1] + "\n");
			else
				bw.write("#" + t + " " + result[1] + " " + result[0] + "\n");

			*/
			int [] result = xgcd(a, b); if(result[0] != 1) bw.write("#" + t + " " + (-1) + "\n");
			else bw.write("#" + t + " " + result[1] + " " + result[2] + "\n");
			
		}
		bw.flush();
	}

	static void func(int a, int b) {
		int q1 = 1, q2 = 1;
		while (true) {
			if ((long)q1 * a < (long)q2 * b) {
				int tmp = a;
				a = b;
				b = tmp;

				tmp = q1;
				q1 = q2;
				q2 = tmp;
			}
			if ((long)a * q1 - (long)b * q2 == 1) {
				result[0] = q1;
				result[1] = -q2;
				return;
			} else if ((long)-a * q1 + (long)b * q2 == 1) {
				result[0] = -q1;
				result[1] = q2;
				return;
			}
			q2 = (int)(((long)a * q1) / ((long)b * q2)) + 1;
			System.out.println(a + " * " + q1 + " - " + b + " * " + q2 + "\t" + " = ");
		}
	}

	static int gcd(int a, int b) {
		if (b == 0)
			return 1;
		return gcd(b, a % b);
	}

	static int[] xgcd(int a, int b) {
		int[] result = { 0, 0, 0 };
		int[] aa = { 1, 0 };
		int[] bb = { 0, 1 };
		int q = 0;
		while (true) {
			q = a / b;
			a = a % b;
			aa[0] = aa[0] - q * aa[1];
			bb[0] = bb[0] - q * bb[1];
			if (a == 0) {
				result[0] = b;
				result[1] = aa[1];
				result[2] = bb[1];
				return result;
			}
			q = b / a;
			b = b % a;
			aa[1] = aa[1] - q * aa[0];
			bb[1] = bb[1] - q * bb[0];
			if (b == 0) {
				result[0] = a;
				result[1] = aa[0];
				result[2] = bb[0];
				return result;
			}
		}
	}
}
