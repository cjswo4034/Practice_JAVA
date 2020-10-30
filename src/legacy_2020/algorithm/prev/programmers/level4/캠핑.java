package algorithm.prev.programmers.level4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class 캠핑 {
	public static void main(String[] args) {
		캠핑 sol = new 캠핑();
		System.out.println(sol.solution(4, new int[][] {{1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}));
	}

	public int solution(int n, int[][] data) {
		Set<Point> set = new HashSet<>();
		
		// 왼쪽 아래, 우측 아래 쐐기가 있으면 해당 좌표에서 텐트칠 수 있음.
		Arrays.sort(data, (arr1, arr2) -> {
			if (arr1[0] == arr2[0]) return Integer.compare(arr1[1], arr2[1]);
			return Integer.compare(arr1[0], arr2[0]);
		});
		
		// 우측 대각선일 때, 좌측 대각선이 있는지 확인
		for (int i = 0, line; i < data.length - 1; i++) {
			boolean left = false, right = false;
			for (int j = i + 1; j < data.length; j++) {
				line = isLine(data[i], data[j]);
				System.out.println(line + "" + Arrays.toString(data[i]) + Arrays.toString(data[j]));
				if (!left && line == 1) {
					Point p = new Point(data[i], data[j]);
					if (check(data, j, p)) set.add(p);
					left = true;
				} else if (!right && line == 2) {
					Point p = new Point(data[i], data[j]);
					if (check(data, j, p)) set.add(p);
					right = true;
				}
				if (left && right) break;
			}
		}
		
		Iterator<Point> iter = set.iterator();
		while(iter.hasNext()) {
			Point p = iter.next();
			System.out.println(p + " : " + p.hashCode());
		}
		System.out.println(Arrays.toString(set.toArray()));

		return set.size();
	}
	
	public boolean check(int[][] data, int from, Point p) {
		for (int i = from; i < data.length; i++) {
			if (p.x1 < data[i][0] && data[i][0] < p.x2
					&& p.y1 < data[i][1] && data[i][1] < p.y2) return false;
		}
		return true;
	}
	
	// 1: left, 2: right
	public int isLine(int[] arr1, int[] arr2) {
		int horizental = arr2[0] - arr1[0];
		int vertical = arr2[1] - arr1[1];
		if (horizental + vertical == 0) return 1;
		if (horizental == vertical) return 2;
		return 0;
	}
	
	class Point {
		int x1, y1, x2, y2;
		public Point(int[] arr1, int[] arr2) {
			this.x1 = Math.min(arr1[0], arr2[0]);
			this.y1 = Math.min(arr1[1], arr2[1]);
			this.x2 = Math.max(arr1[0], arr2[0]);
			this.y2 = Math.max(arr1[1], arr2[1]);
		}
		
		@Override
		public String toString() {
			return "Point [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]";
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point)obj;
			return p.hashCode() == p.hashCode();
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return Objects.hash(x1, y1, x2, y2);
		}
	}
}