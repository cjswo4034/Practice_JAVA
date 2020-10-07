package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _2948 {
	static int result;
	static Map<String, Integer> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			map = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				map.put(st.nextToken(), 1);
			}
			
			result = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				if(map.containsKey(st.nextToken())) {
					result++;
				}
			}
			
			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
}
