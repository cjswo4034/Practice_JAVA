package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 낚시왕_17143 {
	static int n, m, k;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 위, 아래, 오른쪽, 왼쪽
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Shark> pq = new PriorityQueue<>();
		Queue<Shark> q = new LinkedList<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        Shark[][] map = new Shark[n + 1][m + 1];
        for (int i = 0; i < k; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r, c, s, d, z;
        	r = Integer.parseInt(st.nextToken());
        	c = Integer.parseInt(st.nextToken());
        	s = Integer.parseInt(st.nextToken());
        	d = Integer.parseInt(st.nextToken());
        	z = Integer.parseInt(st.nextToken());
        	map[r][c] = new Shark(r, c, s, d - 1, z);
		}
        
        System.out.println(solution(map));
	}
	
	static int solution(Shark[][] map) {
		int res = 0;
		for (int pos = 1; pos <= m; pos++) {
			res += catchShark(map, pos);
			map = moveSharks(map);
		}
		return res;
	}
	
	// 상어를 잡는다.
	static int catchShark(Shark[][] map, int pos) {
		int res = 0;
		for (int i = 1; i <= n; i++) {
			if (map[i][pos] != null) {
				res = map[i][pos].size;
				map[i][pos] = null;
				break;
			}
		}
		return res;
	}
	
	// 상어를 움직인다.
	static Shark[][] moveSharks(Shark[][] map) {
		Shark tmp;
		Shark[][] res = new Shark[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i][j] != null) {
					tmp = map[i][j];
					tmp.move();
					if (res[tmp.r][tmp.c] != null){
						if (res[tmp.r][tmp.c].size < tmp.size) 
							res[tmp.r][tmp.c] = tmp;
					} else res[tmp.r][tmp.c] = tmp;
				}
			}
		}
		return res;
	}
	
	static int bfs(PriorityQueue<Shark> pq) {
		int res = 0, pos = 0;
		Queue<Shark> temp = new LinkedList<>();
		while (!pq.isEmpty() && ++pos != m + 1) {
			int size = pq.size();
			boolean catched = false;
			// 사람과 가장 가까운 상어를 잡는다. 잡으면 이동한다.
			while (size-- > 0) {
				// 가장 왼쪽 위에 있는 상어부터 뽑는다.
				Shark s = pq.poll();
				
				// 같은 위치에 있으면 잡아먹는다. (크기가 큰 것부터 뽑으므로 큰 것이 작은 것을 잡는 걸 보장)
				while (!pq.isEmpty() && s.equals(pq.peek())) {
					pq.poll();
					size--;
				}
				
				// 사람과 같은 위치에 있으면 해당 상어를 잡고 끝낸다. (맨 위에 있는 것부터 뽑으므로 맨 위부터 잡는 걸 보장)
				if (!catched && pos == s.c) {
					res += s.size;
					catched = true;
					continue;
				}
				
				temp.add(s);
			}
			
			while (!pq.isEmpty()) temp.add(pq.poll());
			
			// 상어를 모두 이동시킨다.
			while (!temp.isEmpty()) {
				temp.peek().move();
				pq.add(temp.poll());
			}
		}
		return res;
	}
	
	static class Shark implements Comparable<Shark>{
		int r, c, s, d, size, cnt;
		
		public Shark(int r, int c, int s, int d, int size) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.size = size;
		}

		// speed만큼 방향으로 이동한다.
		// 수조의 끝에 닿으면 반대 방향으로 이동한다.
		// ***** 하나씩 이동하면 오래걸림
		public void move() {
			int tmp = s;
			while (tmp-- > 0) {
				// 위, 아래, 오른쪽, 왼쪽
				if (r == 1 && d == 0) d = 1;
				if (r == n && d == 1) d = 0;
				if (c == 1 && d == 3) d = 2;
				if (c == m && d == 2) d = 3;
				
				r += dir[d][0];
				c += dir[d][1];
			}
		}
		
		@Override
		public boolean equals(Object obj) {
			Shark s = (Shark) obj;
			return (r == s.r && c == s.c);
		}

		@Override
		public int compareTo(Shark o) { 
			if (c == o.c) {
				if (r == o.r) return -Integer.compare(size, o.size);
				return Integer.compare(r, o.r);
			}
			return Integer.compare(c, o.c);
		}
	}
}
