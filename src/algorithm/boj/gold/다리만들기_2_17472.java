package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 다리만들기_2_17472 {
	static final int INF = 987654321;
	
	static int n, m, result = INF;
	static int[][] map;
	static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static boolean[][] visited;
	static List<Island> isLands;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        isLands = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
        
        // 1. 섬 외곽들의 좌표를 딴다
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] || map[i][j] == 0) continue;
				visited[i][j] = true;
				isLands.add(new Island());
				findIsland(i, j);
			}
		}
        
        for (int i = 0; i < isLands.size(); i++)
			isLands.get(i).setBridgeCount(isLands.size());
        
        // 2. 섬에서 다른 섬으로 가는 최소 길이의 다리를 구한다.
        for (int i = 0; i < isLands.size(); i++) {
			for (int j = i + 1; j < isLands.size(); j++)
				setMinimunBridge(i, j);
		}
        
        visited = new boolean[isLands.size()][isLands.size()];
        
        // DFS로 다리 개수 + 1 == 섬의 개수 일 때 모든 섬이 연결되는지 확인 후 결과값 비교
        // 	OR
        // 2.에서 int형으로 섬 간의 간선을 초기화하고 MST로 결과 계산
        minimumCostConnection(0, 0, 0);
        System.out.println(result == INF ? -1 : result);
	}
	
	// 건설된 다리가 isLands.size - 1이면 비용을 확인한다.
	static void minimumCostConnection(int from, int buildedBridgeCount, int cost) {
		if (buildedBridgeCount == isLands.size() - 1) {
			// 모든 섬이 연결됐는지 확인. 더 효율적인 방법?
			boolean[] connection = new boolean[visited.length];
			connect(connection, 0);
			if (isAllIslandConnected(connection)) result = Math.min(result, cost);
			return;
		}
		if (from == isLands.size()) return;
		// 남은 섬의 개수 + 건설된 다리 수 < 건설되야하는 다리의 수
		if (from - buildedBridgeCount > 1) return;

		// 현재 다리를 건설하지 않고 건너뛴다.
		minimumCostConnection(from + 1, buildedBridgeCount, cost);
		
		for (int to = 0; to < isLands.size(); to++) {
			if (from == to) continue;
			if (visited[from][to] || visited[to][from]) continue;
			if (isLands.get(from).bridges[to] == null) continue;
			
			visited[from][to] = visited[to][from] = true;
			
			// 새로 지은 다리를 cost에 포함하여 다음 다리를 건설한다.
			minimumCostConnection(from + 1, buildedBridgeCount + 1, cost + isLands.get(from).bridges[to].distance);
			
			visited[from][to] = visited[to][from] = false;
		}
	}

	// 1번 섬에서 2번 ~ n번 섬까지 방문할 수 있다.
	static boolean isAllIslandConnected(boolean[] connection) {
		for (boolean c: connection) if (!c) return false;
		return true;
	}
	
	static void connect(boolean[] connection, int idx) {
		for (int i = 0; i < visited.length; i++) {
			if (visited[idx][i] && !connection[i]) {
				connection[i] = true;
				connect(connection, i);
			}
		}
	}
	
	static void setMinimunBridge(int from, int to) {
		int minDistance = INF;
		Node nI = isLands.get(from).border.get(0), nJ = isLands.get(to).border.get(0);
		
		for (Node n1: isLands.get(from).border) {
			for (Node n2: isLands.get(to).border) {
				int distance = getDistance(n1, n2);
				if (distance != -1 && minDistance > distance) {
					minDistance = distance;
					nI = n1;
					nJ = n2;
				}
			}
		}
		
		if (minDistance == INF) return;
		isLands.get(from).setNthMinBridge(to, new Bridge(minDistance, nI, nJ));
		isLands.get(to).setNthMinBridge(from, new Bridge(minDistance, nJ, nI));
	}
	
	static int getDistance(Node n1, Node n2) {
		// 일직선만 가능
		if (!(n1.r == n2.r || n1.c == n2.c)) return -1;

		int distance = 0;
		if (n1.c == n2.c) distance = buildable(true, n1.c, n1.r, n2.r);
		if (n1.r == n2.r) distance = buildable(false, n1.r, n1.c, n2.c);
		
		return distance < 2 ? -1 : distance;
	}
	
	// v1과 v2 사이에 1이 있으면 X. 없으면 사이 거리 반환
	static int buildable(boolean checkRow, int v, int v1, int v2) {
		int minV = Math.min(v1, v2) + 1;
		int maxV = Math.max(v1, v2);
		while (minV < maxV) {
			if (checkRow && map[minV][v] == 1) return -1;
			if (!checkRow && map[v][minV] == 1) return -1;
			minV++;
		}
		return Math.abs(v1 - v2) - 1;
	}
	
	// 섬 외곽 좌표 따기
	static void findIsland(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			
			if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
			if (visited[nr][nc]) continue;
			
			if (map[nr][nc] == 0) {
				Node n = new Node(r, c);
				if (!isLands.get(isLands.size() - 1).border.contains(n)) 
					isLands.get(isLands.size() - 1).addBorder(n);
				continue;
			}
			visited[nr][nc] = true;
			findIsland(nr, nc);
		}
	}
	
	static class Island {
		List<Node> border = new ArrayList<>();
		Bridge[] bridges;
		void addBorder(Node n) { this.border.add(n); }
		void setBridgeCount(int n) { bridges = new Bridge[n]; }
		void setNthMinBridge(int n, Bridge b) {
			bridges[n] = b;
		}
	}
	
	static class Bridge {
		int distance;
		Node from, to;
		
		public Bridge(int distance, Node from, Node to) {
			this.distance = distance;
			this.from = from;
			this.to = to;
		}
		
		void setFrom(Node from) { this.from = from; }
		void setTo(Node to) { this.to = to; }
	}
	
	static class Node {
		int r, c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			Node n = (Node)obj;
			return this.r == n.r && this.c == n.c;
		}
	}
}
