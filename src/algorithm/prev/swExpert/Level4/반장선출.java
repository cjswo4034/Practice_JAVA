package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 반장선출 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		Pair[] pairs;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1, n; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			n = Integer.parseInt(br.readLine());
			
			pairs = new Pair[n];
			for (int i = 0; i < n; i++)
				pairs[i] = getPair(br.readLine());
			
			Arrays.sort(pairs);
			
			sb.append(pairs[0].str).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static Pair getPair(String str){
		Set<Character> set = new HashSet<>();
		for (char ch : str.toCharArray()) {
			if (ch != ' ') set.add(ch);
		}
		return new Pair(str, set.size());
	}
	
	static class Pair implements Comparable<Pair>{
		String str;
		int count;
		
		public Pair(String str, int count) {
			super();
			this.str = str;
			this.count = count;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (this.count == o.count) 
				return this.str.compareTo(o.str); 
			return -Integer.compare(this.count, o.count);
		}
	}
}
