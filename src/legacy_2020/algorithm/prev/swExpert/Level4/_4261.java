package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _4261 {
	static int result;
	static int n;
	static char[] s;
	static boolean[] check;
	static String[] inputs;
	static char[][] key = {{}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
								{ 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's'},
								{ 't', 'u', 'v'}, { 'w', 'x', 'y', 'z'}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			s = st.nextToken().toCharArray();
			n = Integer.parseInt(st.nextToken());
			
			inputs = br.readLine().split(" ");
			check = new boolean[inputs.length];
			
			for (int i = 0; i < inputs.length; i++) {
				if(inputs[i].length() != s.length) {
					check[i] = true;
				}
			}
			
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < inputs.length; j++) {
					if(!check[j]) {
						boolean contain = false;
						for (int k = 0; k < key[s[i] - '0'].length; k++) {
							if (inputs[j].charAt(i) == key[s[i] - '0'][k]) {
								contain = true;
							}
						}
						
						if(!contain) {
							check[j] = true;
						}
					}
				}
			}
			
			result = 0;
			for (int i = 0; i < check.length; i++) {
				if(!check[i]) result++;
			}
			
			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
}
