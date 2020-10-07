package algorithm.programmers.kakao;

import java.util.Arrays;

public class 외벽점검 {
	int result = 987654321;
	public int solution(int n, int[] weak, int[] dist) {
		int[] newWeak = new int[weak.length * 2];
		boolean[] visited = new boolean[weak.length];

		for (int i = 0; i < weak.length; i++) {
			newWeak[i] = weak[i];
			newWeak[i + weak.length] = weak[i] + n;
		}

		for (int i = 0; i < dist.length; i++) dist[i] *= -1;
		Arrays.sort(dist);
		for (int i = 0; i < dist.length; i++) dist[i] *= -1;

		dfs(visited, weak, dist, 0);

		return result == 987654321 ? -1 : result;
	}

	void dfs(boolean[] visited, int[] weak, int[] dist, int depth) {
		if (depth >= result) return;
		if (check(visited)) {
			result = Math.min(result, depth);
			return;
		}
		if (depth == dist.length) return;

		for (int i = 0, pos; i < visited.length; i++) {
			if (visited[i]) continue;

			pos = i;
			while (pos < weak.length && !visited[pos % visited.length] && weak[i] <= weak[pos] + dist[depth]) {
				visited[pos % visited.length] = true;
				pos++;
			}

			dfs(visited, weak, dist, depth + 1);

			for (int j = i; j < pos; j++) visited[pos % visited.length] = false;
		}
	}

	boolean check(boolean[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) return false;
		}
		return true;
	}
}
