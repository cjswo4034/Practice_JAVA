package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1860 {
	static int [] array;
	static int [] person;
	static int n, m, k, max;
	static boolean check;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t);
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			max = 0;
			check = false;
			person = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				person[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, person[i]);
			}
			
			array = new int[max + 1];			
			for (int i = 1; i <= max; i++) {
				array[i] = array[i - 1];
				if(i % m == 0) {
					array[i] += k;
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = person[i]; j <= max; j++) {
					array[j]--;
				}
			}
			
			for (int i = 1; i <= max; i++) {
				if(array[i] < 0) {
					check= false;
				}
			}
			
			if(check) {
				sb.append(" Possible\n");
			} else {
				sb.append(" Impossible\n");	
			}
		}
		System.out.println(sb);
	}
}
