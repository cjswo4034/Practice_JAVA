package algorithm.prev.programmers.level4;

import java.util.Arrays;

public class 지형편집 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(new int[][] {{1, 2}, {2, 3}}, 3, 2));
	}
	
	static class Solution {
		public long solution(int[][] land, int P, int Q) {
			long answer = -1, l = Integer.MAX_VALUE, r = 0;
			
			// 1차원으로 바꾼다.
			int[] lands = new int[land.length * land[0].length];
			for (int i = 0; i < land.length; i++) {
				for (int j = 0; j < land.length; j++) {
					lands[i * land.length + j] = land[i][j];
					if (l > land[i][j]) l = land[i][j];
					if (r < land[i][j]) r = land[i][j];
				}
			}
			
			int a, b, len = lands.length;
			long sum, m, p, pp;
			while (l <= r) {
				m = (l + r) >> 1;
				sum = a = b = 0;
				for (int v: lands) {
					if (v < m) {
						sum += (m - v) * P;
						b++;
					} else if(v != m) {
						sum += (v - m) * Q;
						a++;
					}
				}
				
				p = sum - a * Q + (len - a) * P;
				pp = sum - b * P + (len - b) * Q;
				
				if (p < sum && sum < pp) l = m + 1;
				else if (p > sum && sum > pp) r = m - 1;
				else {
					answer = sum;
					break;
				}
			}
			
			return answer;
		}
	}
}
