package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1244_v {
	static boolean [][] check;
	static char [] num;
	static int max, result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int k = 1; k <= t; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num = st.nextToken().toCharArray();
			max = Integer.parseInt(st.nextToken());
			check = new boolean[max + 1][1000000];
			result = 0;
			
			DFS(0);
			sb.append(toString(k));
		}
		System.out.println(sb.toString());
	}
	
	public static void DFS(int depth) {
		if(max == depth) {
			result = Math.max(parseInt(), result);
			return;
		}
		
		for (int i = 0; i < num.length - 1; i++) {
			for (int j = i + 1; j < num.length; j++) {
				swap(i, j);
				if(!check[depth + 1][parseInt()]) {
					check[depth + 1][parseInt()] = true;
					DFS(depth + 1);
				}
				swap(i, j);
			}
		}
	}
	
	public static String toString(int t) {
		return "#" + t + " " + result + "\n";
	}
	
	public static int parseInt() {
		return Integer.parseInt(String.valueOf(num));
	}
	
	public static void swap(int i, int j) {
		char tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
}
