package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 준홍이의_네개의_제곱수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int[] arr = initArr();
		System.out.println("---------------------------");
		int T = Integer.parseInt(br.readLine());
		for (int t = 1, n; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			n = Integer.parseInt(br.readLine());
			
			sb.append(arr[n]).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	private static int[] initArr() {
		int[] arr = new int[32768];
		for (int i = 0; i < arr.length; i++) {
			int ii = i * i;
			for (int j = i; i < arr.length; j++) {
				int jj = ii + j * j;
				if (jj > 32767) break;
				for (int k = j; i < arr.length; k++) {
					int kk = jj + k * k;
					if (kk > 32767) break;
					for (int z = k; i < arr.length; z++) {
						int zz = kk + z * z;
						if (zz > 32767) break;
						arr[zz] += 1;
					}
				}
			}
		}
		
		return arr;
	}

}
