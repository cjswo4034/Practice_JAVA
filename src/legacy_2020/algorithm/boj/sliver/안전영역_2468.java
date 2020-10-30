package algorithm.boj.sliver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 안전영역_2468 {
    private static int n, result = 1;
    private static int[][] map;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int min = 100, max = 0;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(map[i][j], min);
                max = Math.max(map[i][j], max);
            }
        }

        int cnt = 0;
        boolean[][] visited;
        for (int i = min; i <= max; i++) {
            visited = new boolean[n][n];
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (!visited[row][col] && map[row][col] > i) {
                        bfs(map, visited, new Pair(row, col), i);
                        cnt++;
                    }
                }
            }
            result = Math.max(result, cnt);
            cnt = 0;
        }
        System.out.println(result);
    }

    private static void bfs(int[][] map, boolean[][] visited, Pair start, int criterion) {
        visited[start.row][start.col] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nRow = curr.row + direction[i][0];
                int nCol = curr.col + direction[i][1];
                if (nRow < 0 || nCol < 0 || nRow >= n || nCol >= n) continue;
                if (visited[nRow][nCol] || map[nRow][nCol] <= criterion) continue;

                visited[nRow][nCol] = true;
                q.add(new Pair(nRow, nCol));
            }
        }
    }

    static class Pair {
        int row, col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
