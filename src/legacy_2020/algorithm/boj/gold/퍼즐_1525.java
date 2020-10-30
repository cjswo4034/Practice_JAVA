package algorithm.boj.gold;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class 퍼즐_1525 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = "";
		for (int i = 0; i < 9; i++) {
			input += sc.nextInt();
		}
		System.out.println(solution(input));
	}
	
	static int solution(String start) {
		String correct = "123456780";
		if (correct.equals(start)) return 0;
		
		int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		StringBuilder sb;
		Queue<String> q = new LinkedList<String>();
		q.add(start);
		Set<String> set = new HashSet<>();
		set.add(start);
		
		int idx, ans = 0;
		String curr, next;
		while(!q.isEmpty()) {
			ans++;
			int size = q.size();
			while (size-- > 0) {
				curr = q.poll();
				idx = curr.indexOf('0');
				int row = idx / 3;
				int col = idx % 3;
				for (int i = 0; i < 4; i++) {
					int nrow = row + direction[i][0];
					int ncol = col + direction[i][1];
					if (nrow < 0 || ncol < 0 || nrow >= 3 || ncol >= 3) continue;
					
					int nIdx = nrow * 3 + ncol;
					sb = new StringBuilder(curr);
					sb.setCharAt(idx, curr.charAt(nIdx));
					sb.setCharAt(nIdx, '0');
					next = sb.toString();
					
					if (set.contains(next)) continue;
					if (next.equals(correct)) return ans;
					q.add(next);
					set.add(next);
				}
			}
		}
		return -1;
	}
	
	// Wrapper 클래스 이용.
	static int solution1() {
		Scanner sc = new Scanner(System.in);
		
		Node start = null;
		String input = "";
		for (int i = 0, tmp; i < 9; i++) {
			tmp = sc.nextInt();
			if (tmp == 0) start = new Node(i, 0);
			input += tmp;
		}
		start.str = input.toCharArray();
		
		Node correct = new Node(8, 0);
		correct.str = "123456780".toCharArray();
		
		if (correct.equals(start)) return 0; 
		else return bfs(start, correct);
	}
	
	static int bfs(Node start, Node correct) {
		int[] direction = {1, 3, -1, -3};
		Set<Node> set = new HashSet<>();
		set.add(start);
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		
		Node curr, next;
		while (!q.isEmpty()) {
			curr = q.poll();
			int pos = curr.zeroIdx;
			for (int i = 0; i < 4; i++) {
				int npos = pos + direction[i];
				if (npos < 0 || npos >= 9) continue;
				else if (i == 0 && pos % 3 == 2) continue;
				else if (i == 2 && pos % 3 == 0) continue;
				
				next = getNext(pos, npos, curr);
				if (next.equals(correct)) return next.depth;
				if (set.contains(next)) continue;

				set.add(next);
				q.add(next);
			}
		}
		
		return -1;
	}
	
	static Node getNext(int pos, int npos, Node current) {
		Node res = new Node(npos, current.depth + 1);
		System.arraycopy(current.str, 0, res.str, 0, 9);
		swap(res.str, pos, npos);
		return res;
	}
	
	static void swap(char[] src, int x, int y) {
		char tmp = src[x];
		src[x] = src[y];
		src[y] = tmp;
	}
	
	static class Node {
		int zeroIdx, depth;
		char[] str = new char[9];
		public Node(int zeroIdx, int depth) {
			this.zeroIdx = zeroIdx;
			this.depth = depth;
		}

		@Override
		public boolean equals(Object obj) {
			Node node = (Node)obj;
			return node.hashCode() == hashCode();
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(new String(str));
		}
	}
}
