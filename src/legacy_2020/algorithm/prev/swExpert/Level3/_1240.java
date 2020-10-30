package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1240 {
	static int n, m;
	static String [] map;
	static String [] co = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = Integer.parseInt(br.readLine());
		for (int t = 1; t <= k; t++) {
			StringBuilder code = new StringBuilder();
			boolean sign = false;
			scan(br);
			
			for (int i = 0; i < n; i++) {
				int start = map[i].lastIndexOf('1');
				for (int j = start + 1; j >= 6; j--) {
					for (int j2 = 0; j2 < co.length; j2++) {
						if(map[i].substring(j - 6, j + 1).equals(co[j2])) {
							code.append(j2);
							sign = true;
							j -= 6;
							if(j - 7 < 0) break;
						}
					}
				}
				if(sign) break;
			}
			code.reverse();
			//System.out.println(code);
			sb.append(toString(t, check(code.toString().toCharArray())));
		}
		System.out.println(sb);
	}
	
	public static int check(char [] code) {
		int sum = 0;
		int odd = 0;
		for(int i = 0 ; i < code.length ; i++) {
			if(i % 2 == 0) {
				odd += (code[i] - '0') * 3;
			} else {
				sum += (code[i] - '0');
			}
		}
		
		if((sum + odd) % 10 == 0) {
			return sum + (odd / 3);
		} else {
			return 0;
		}
	}
	
	public static void scan(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new String[n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine();
		}
	}
	
	public static String toString(int t, int result) {
		return "#" + t + " " + result + "\n";
	}
}
