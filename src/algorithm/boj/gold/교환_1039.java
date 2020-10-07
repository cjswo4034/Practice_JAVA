package algorithm.boj.gold;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

// Gold 3
public class 교환_1039 {
	static int n, k, len;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		len = String.valueOf(n).length();
		System.out.println(bfs(String.valueOf(n).toCharArray()));
		System.out.println(bfs(n));
	}

	static int bfs(char[] start) {
		Queue<String> q = new LinkedList<>();
		q.add(new String(start));
		Set<String> set;
		
		int step = 0, res = -1;
		String curr, next;
		while (!q.isEmpty() && step < k) {
			step++;
			int size = q.size();
			set = new HashSet<>();
			while (size-- > 0) {
				curr = q.poll();
				for (int i = 0; i < curr.length() - 1; i++) {
					for (int j = i + 1; j < curr.length(); j++) {
						next = replace(curr, i, j);
						if (set.contains(next) || next.charAt(0) == '0') continue;
						if (k == step && Integer.parseInt(next) > res) res = Integer.parseInt(next);
						set.add(next);
						q.add(next);
					}
				}
			}
		}
		
		return res;
	}
	
	static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		Set<Integer> set;
		
		int step = 0, res = -1, lowest = (int)Math.pow(10, len - 1);
		Integer curr, next;
		while (!q.isEmpty() && step++ < k) {
			int size = q.size();
			set = new HashSet<>();
			while (size-- > 0) {
				curr = q.poll();
				for (int i = 1; i < len; i++) {
					for (int j = i + 1; j <= len; j++) {
						next = replace(curr, i, j);
						if (set.contains(next) || lowest > next) continue;
						if (k == step && next > res) res = next;
						set.add(next);
						q.add(next);
					}
				}
			}
		}
		
		return res;
	}
	
	static String replace(String str, int old, int newChar) {
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(old, str.charAt(newChar));
		sb.setCharAt(newChar, str.charAt(old));
		return sb.toString();
	}
	
	static int replace(int num, int from, int to) {
		int from_idx = (int)Math.pow(10, len - from);
		int to_idx = (int)Math.pow(10, len - to);
		int from_num = num / from_idx % 10;
		int to_num = num / to_idx % 10;
		num = num - from_num * from_idx - to_num * to_idx;
		num = num + from_num * to_idx + to_num * from_idx;
		return num;
	}
}
