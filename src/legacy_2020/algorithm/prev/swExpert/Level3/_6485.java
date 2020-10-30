package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _6485 {
	static int [] array;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t);
			
			int n = Integer.parseInt(br.readLine());
			array = new int[5001];
		
			int left, right;
			StringTokenizer st;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				left = Integer.parseInt(st.nextToken());
				right = Integer.parseInt(st.nextToken());
				
				for(;left <= right;){
					array[left++]++;
				}
			}
			
			int p = Integer.parseInt(br.readLine());
			for (int i = 0; i < p; i++) {
				int tmp = Integer.parseInt(br.readLine());
				sb.append(" " + array[tmp]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
