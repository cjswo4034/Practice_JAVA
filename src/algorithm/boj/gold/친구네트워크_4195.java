package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 친구네트워크_4195 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()), n, size;
        int[] union;
        String[] input;
        Map<String, Integer> map;
        while (t-- > 0) {
        	size = 0;
        	n = Integer.parseInt(br.readLine());
        	union = new int[n * 2];
        	map = new HashMap<>();
        	Arrays.fill(union, -1);
        	
        	for (int i = 0, a, b; i < n; i++) {
				input = br.readLine().split(" ");
				if (!map.containsKey(input[0])) map.put(input[0], size++);
				if (!map.containsKey(input[1])) map.put(input[1], size++);
				a = map.get(input[0]);
				b = map.get(input[1]);
				merge(union, a, b);
				sb.append(-union[find(union, a)]).append("\n");
			}
        }
        System.out.println(sb);
	}
	
	static int find(int[] union, int v) {
		if (union[v] < 0) return v;
		return union[v] = find(union, union[v]);
	}
	
	static void merge(int[] union, int a, int b) {
		a = find(union, a);
		b = find(union, b);
		if (a == b) return;
		if (union[a] < union[b]) {
			union[a] += union[b];
			union[b] = a;
		} else {
			union[b] += union[a];
			union[a] = b;
		}
	}
}
