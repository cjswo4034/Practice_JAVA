package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1208 {
	static int x;
	static int [] n = new int[100];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			x = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				n[i] = Integer.parseInt(st.nextToken());
			}
			
			while(x-- > 0) {
				min();
				max();
			}
			sb.append(toString(t, max() - min()));
		}
		System.out.println(sb);
	}
	
	public static int min() {
		int min = n[0];
		int idx = 0;
		for (int i = 1; i < n.length; i++) {
			if(min > n[i]) {
				min = n[i];
				idx = i;
			}
		}
		n[idx]++;
		return min;
	}
	
	public static int max() {
		int max = n[0];
		int idx = 0;
		for (int i = 1; i < n.length; i++) {
			if(max < n[i]) {
				max = n[i];
				idx = i;
			}
		}
		n[idx]--;
		return max;
	}
	
	public static String toString(int t, int result) {
		return "#" + t + " " + result + "\n";
	}
}
