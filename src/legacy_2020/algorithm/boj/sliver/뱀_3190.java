package algorithm.boj.sliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Silver 1
public class 뱀_3190 {
	private static int direction;
	private static int[][] map;	// 0: 이동 가능, 1: 뱀 있음, 2: 사과 있음
	private static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 2;
		}
		
		Queue<Command> commands = new LinkedList<>();
		int l = Integer.parseInt(br.readLine());
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			commands.add(new Command(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		
		Point start = new Point(0, 0);
		System.out.println(playGame(commands, start));
	}
	
	static int playGame(Queue<Command> commands, Point start) {
		Deque<Point> snake = new LinkedList<>();
		snake.add(start);
		map[start.row][start.col] = 1;
		
		int time = 0;
		while (++time <= 10100) {
			Command cmd = null;
			if (!commands.isEmpty() && commands.peek().time + 1 == time) cmd = commands.poll();
			
			// 이동
			int movable = moveHead(cmd, snake);
			
			// 꼬리 제거
			if (movable < 0) return time;
			else if (movable == 0) removeTail(snake);
		}
		return time;
	}
	
	static int moveHead(Command command, Deque<Point> snake) {
		Point head = snake.getFirst();
		if (command != null) {
			if (command.command == 'L') direction = (direction + 3) % 4;
			else direction = (direction + 1) % 4;
		}
		int nrow = head.row + dir[direction][0];
		int ncol = head.col + dir[direction][1];

		if (nrow < 0 || ncol < 0 || nrow >= map.length || ncol >= map[0].length || map[nrow][ncol] == 1) return -1;
		boolean isApple = map[nrow][ncol] == 2;
		
		map[nrow][ncol] = 1;
		snake.addFirst(new Point(nrow, ncol));
		return isApple ? 1 : 0;
	}
	
	static void removeTail(Deque<Point> snake) {
		Point tail = snake.pollLast();
		map[tail.row][tail.col] = 0;
	}
	
	static class Command {
		int time;
		char command;
		public Command(int time, char command) {
			this.time = time;
			this.command = command;
		}
	}
	
	static class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
