package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DLSR_9019 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			bfs(start, target);
		}
	}

	static void bfs(int start, int target) {
		boolean[] visited = new boolean[10000];
		Queue<Command> q = new LinkedList<>();

		visited[start] = true;
		q.add(new Command(start, ""));
		while (!q.isEmpty()) {
			Command curr = q.poll(), next;
			int n = curr.n;
			
			if (n == target) {
				System.out.println(curr.cmds);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				if (i == 0) next = new Command((2 * n) % 10000, curr.cmds + 'D');
				else if (i == 1) next = new Command((n + 9999) % 10000, curr.cmds + 'S');
				else if (i == 2) next = new Command(n % 1000 * 10 + n / 1000, curr.cmds + 'L');
				else next = new Command(n / 10 + (n % 10) * 1000, curr.cmds + 'R');

				if (visited[next.n]) continue;

				visited[next.n] = true;
				q.add(next);
			}
		}
	}

	static int getD(int n) {
		return (2 * n) % 10000;
	}

	static int getS(int n) {
		return (n + 9999) % 10000;
	}

	static int getL(int n) {
		return n % 1000 * 10 + n / 1000;
	}

	static int getR(int n) {
		return n / 10 + (n % 10) * 1000;
	}

	static class Command {
		int n;
		String cmds;

		Command(int n, String cmds) {
			this.n = n;
			this.cmds = cmds;
		}
	}
}
