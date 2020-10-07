package algorithm.prev.programmers.Level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 컬러링북 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString( solution.solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}})));
    }

    static class Solution {
        static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public int[] solution(int m, int n, int[][] picture) {
            boolean[][] visited = new boolean[m][n];

            int count = 0, maxSize = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (picture[i][j] != 0 && !visited[i][j]){
                        count++;
                        visited[i][j] = true;
                        //maxSize = Math.max(maxSize, bfs(i, j, visited, picture));
                        maxSize = Math.max(maxSize, dfs(new Pair(i, j), visited, picture));
                    }
                }
            }

            return new int[]{count, maxSize};
        }

        private int dfs(Pair curr, boolean[][] visited, int[][] map){
            int color = map[curr.x][curr.y];
            int size = 1;
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if (nx >= visited.length || ny >= visited[0].length || nx < 0 || ny < 0) continue;

                if (map[nx][ny] == color && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    size += dfs(new Pair(nx, ny), visited, map);
                }
            }
            return size;
        }

        public int bfs(int startX, int startY, boolean[][] visited, int[][] map){
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(startX, startY));
            visited[startX][startY] = true;
            int size = 1, color = map[startX][startY];
            while (!q.isEmpty()){
                Pair curr = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = curr.x + dir[i][0];
                    int ny = curr.y + dir[i][1];

                    if (nx >= visited.length || ny >= visited[0].length || nx < 0 || ny < 0) continue;

                    if (map[nx][ny] == color && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new Pair(nx, ny));
                        size++;
                    }
                }
            }
            return size;
        }
    }

    static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
