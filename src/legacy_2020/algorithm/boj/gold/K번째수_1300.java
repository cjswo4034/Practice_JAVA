package algorithm.boj.gold;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class K번째수_1300 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextInt();
		long k = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i * i; j++) {
				System.out.println(i + ", " + j + "===>");
				System.out.println(solve1(i, j));
				System.out.println(solve2(i, j));
				System.out.println(solve3(i, j));
			}
		}
	}
	
	// 통과
	// 효율: sum을 구할 때 대칭을 이용하여 구하
	static long solve3(long n, long k) {
		long l = 0, r = n * n + 1;
		while (l < r) {
			long mid = (l + r) >> 1;
			long sum = 0;
			for (int i = 1; i <= n; i++) {
				sum += Math.min(n,  mid / i);
			}
			if (sum >= k) r = mid;
			else l = mid + 1;
		}
		return r;
	}
	
	
	// 시간초과
	// 효율: 1부터 시작하므로 O(N^2)
	//       -> Parametric search
	static int solve2(int n, int k) {
		int sum = 0, res = 0;
		while (sum < k) {
			sum = 0; res++;
			for (int i = 1; i <= n; i++) {
				sum += Math.min(n, res / i);
			}
		}
		return res;
	}
	
	// 시간초과
	// 중복: Node.mul 이 같은 값일 때
	// 효율: i: sum(1 ~ i / i)번째 수 -> O(N^2)
	static int solve1(int n, int k) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 1; i <= n; i++) pq.add(new Node(i, i));
		
		Node current;
		while (!pq.isEmpty()) {
			current = pq.poll();
			if (current.maxEqualMin()) k--;
			else k -= 2;
			
			if (k <= 0) {
				return current.mul;
			}
			
			current.increaseMax();
			if (current.isAddable(n))
				pq.add(current);
		}
		return -1;
	}
	
	static class Node implements Comparable<Node>{
		int mul, max, min;
		Node(int max, int min) {
			this.mul = max * min;
			this.max = max;
			this.min = min;
		}
		void increaseMax() {
			this.max++;
			this.mul = max * min;
		}
		boolean maxEqualMin() {
			return max == min;
		}
		boolean isAddable(int n) {
			return max <= n;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.mul, o.mul);
		}
		@Override
		public String toString() {
			return "Node [mul=" + mul + ", max=" + max + ", min=" + min + "]";
		}
		
		
	}
}
