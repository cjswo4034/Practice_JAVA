package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1206 {
	static int a[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int k = 0; k < 10; k++) {
			int n = Integer.parseInt(br.readLine());
			a = new int[n];
			
			st = new StringTokenizer(br.readLine());
			int result = 0;
			
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 2; i < n - 2; i++) {
				int max = find(a, i);
				if(a[i] > max) {
					result += (a[i] - max);
				}
			}
			
			System.out.println("#" + (k + 1) + " " + result);
		}
	}
	
	public static int find(int[] a, int idx) {
		int left = Math.max(a[idx - 1], a[idx - 2]);
		int right = Math.max(a[idx + 1], a[idx + 2]);
		
		return Math.max(left, right);
	}

}
