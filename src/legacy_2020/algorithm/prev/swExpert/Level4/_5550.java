package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class _5550 {
	static int result;
	static char[] chr = {' ', 'c', 'r', 'o', 'a', 'k'};
	static Map<Character, Integer> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		map = new HashMap<>();
		map.put('c', 0);
		map.put('r', 1);
		map.put('o', 2);
		map.put('a', 3);
		map.put('k', 4);
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			char[] input = br.readLine().toCharArray();
			boolean[] chk = new boolean[input.length];
			
			
			
			result = 0;
			if(input.length % 5 == 0) {
				calc(input, chk);
			} else {
				result = -1;
			}
			
			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
	
	static void calc(char[] input, boolean[] chk) {
		int[] count = new int[5];
		for (int i = 0; i < input.length; i++) {
			count[map.get(input[i])]++;
			
			if(chk[i]) continue;
			if(input[i] != 'c') {
				result = -1;
				return;
			}
			chk[i] = true;
			
			int cnt = 2;
			for (int j = i + 1; j < input.length; j++) {
				if(cnt == 6) {
					cnt = 1;
				}
				
				if(!chk[j] && input[j] == chr[cnt]) {
					chk[j] = true;
					cnt++;
				}
			}
			
			result++;
		}
		
		int correct = count[0];
		for (int i : count) {
			if(correct != i) {
				result = -1;
				return;
			}
		}
	}
}
