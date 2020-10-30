package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _6781 {
	static int result;
	static int[] r, g, b;
	static char[] array1, array2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		r = new int[10];
		g = new int[10];
		b = new int[10];
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			array1 = br.readLine().toCharArray();
			array2 = br.readLine().toCharArray();
			
			for(int i = 0 ; i < 9 ; i++) {
				r[i] = g[i] = b[i] = 0;
			}
			
			for(int i = 0 ; i < 9 ; i++) {
				if(array2[i] == 'R')
					r[array1[i] - '0']++;
				else if(array2[i] == 'G')
					g[array1[i] - '0']++;
				else
					b[array1[i] - '0']++;
			}
			
			result = 0;
			for (int i = 1; i <= 9; i++) {
                int tmp;
				if(i <= 7) {
					tmp = func1(i);
					if(tmp > 0) {
						result += tmp;
					}
				}
				tmp = func0(i);
				result += tmp;
			}
			sb.append(result >= 3 ? "Win" : "Continue");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static int func0(int idx) {
		int check = 0;
		
		if(r[idx] >= 3) {
			check++;
			r[idx] -= 3;
		}

		if(g[idx] >= 3) {
			check++;
			g[idx] -= 3;
		}
		
		if(b[idx] >= 3) {
			check++;
			b[idx] -= 3;
		}
		
		return check;
	}
	
	static int func1(int start) {
		int countR = Math.min(r[start], Math.min(r[start + 1], r[start + 2]));
		int countG = Math.min(g[start], Math.min(g[start + 1], g[start + 2]));
		int countB = Math.min(b[start], Math.min(b[start + 1], b[start + 2]));
		
		if(countR > 0) {
			sub(r, start);
		}
		if(countG > 0) {
			sub(g, start);
		}
		if(countB > 0) {
			sub(b, start);
		}
		
		return countR + countG + countB;
	}
	
	static void sub(int[] array, int idx) {
		for(int i = idx ; i < idx + 3 ; i++) {
			array[i]--;
		}
	}
}
