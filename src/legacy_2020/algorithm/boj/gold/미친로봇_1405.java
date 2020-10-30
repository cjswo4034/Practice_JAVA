package algorithm.boj.gold;

import java.util.Scanner;

// gold 5
public class 미친로봇_1405 {
    private static int n;
    private static double east, west, south, north;
    private static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        east = sc.nextInt() * 0.01;
        west = sc.nextInt() * 0.01;
        south = sc.nextInt() * 0.01;
        north = sc.nextInt() * 0.01;
        visited = new boolean[n * 2 + 1][n * 2 + 1];
        System.out.println(dfs(visited, n, n, n));
    }

    private static double dfs(boolean[][] visited, int depth, int row, int col) {
        if (visited[row][col]) return 0;
        if (depth == 0) return 1;

        double res = 0;
        visited[row][col] = true;
        res += east * dfs(visited, depth - 1, row, col + 1);
        res += west * dfs(visited, depth - 1, row, col - 1);
        res += south * dfs(visited, depth - 1, row + 1, col);
        res += north * dfs(visited, depth - 1, row - 1, col);
        visited[row][col] = false;
        return res;
    }
}
