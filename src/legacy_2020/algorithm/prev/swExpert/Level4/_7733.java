package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _7733 {
    static private int n;
    static private final int [][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int [][] map; boolean [][] check;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            int maxDay = 0;
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxDay = Math.max(maxDay, map[i][j]);
                }
            }

            int result = 0;
            for (int day = 1; day < maxDay; day++) {
                int tmp = 0;
                check = new boolean[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!check[i][j] && map[i][j] > day){
                            check[i][j] = true;
                            dfs(map, check, day, i, j);
                            tmp++;
                        }
                    }
                }
                result = Math.max(result, tmp);
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int [][] map, boolean [][] check, int day, int x, int y){
        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + x;
            int ny = dir[i][1] + y;

            if (nx >= n || ny >= n || nx < 0 || ny < 0) continue;
            if (map[nx][ny] <= day) continue;

            if (!check[nx][ny]){
                check[nx][ny] = true;
                dfs(map, check, day, nx, ny);
            }
        }
    }
}
