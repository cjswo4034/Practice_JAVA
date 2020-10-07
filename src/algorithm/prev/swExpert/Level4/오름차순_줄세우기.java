package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 오름차순_줄세우기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		
		System.out.println(getLogest(new int[] {1, 3, 5, 7, 7}));

		int[] input;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1, n, result; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			n = Integer.parseInt(br.readLine());
			input = new int[n + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i < input.length; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			result = getLogest(input);
			
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static int getLogest(int[] input) {
		int[] arr = new int[input.length];
		int len = 1; 
		
		arr[len] = input[len];
		
		System.out.println(Arrays.toString(arr));
		
		for (int i = 2; i < arr.length; i++) { 
			//if (arr[len] == input[i]) continue;
			if (arr[len] < input[i]) arr[++len] = input[i]; 
			else arr[getIdx(arr, 1, len, input[i])] = input[i]; 
			
			System.out.println(Arrays.toString(arr));
		}
		return len;
	}
	
	private static int getIdx(int[] arr, int l, int r, int value) {
		int m = (l + r) / 2;

		while (r - l > 0) {
			if (value == arr[m]) return m;
			else if (value < arr[m]) r = m;
			else l = m + 1;
			m = (l + r) / 2;
		}
		
		return m;
	}
}
