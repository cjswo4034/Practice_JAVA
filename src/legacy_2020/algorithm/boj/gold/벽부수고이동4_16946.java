package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/* [Gold 2]
 * map[i][j]가 1일 때 이동할 수 있는 칸(0)의 개수를 구한다.
 * map의 원소가 1인 영역은 이동할 수 없다.
 *
 * 1. 0인 영역의 0의 개수를 구해서 해당 영역에 할당한다.
 * 2. 1에서 0인 영역의 주변 칸이 1이라면 1에서 구한 0의 개수를 더한다. (동일한 칸은 한 영역에서 한번만 더함)
 * */
public class 벽부수고이동4_16946 {
    static final int[][] DIR = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int n, m;
    static int[][] cnt;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cnt = new int[n][m];
        map = new char[n][m];
        visited = new boolean[n][m];

        // 0인 곳의 인접한 0의 개수를 저장한다.
        for (int i = 0; i < n; i++) map[i] = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0' && cnt[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0') sb.append(0);
                else sb.append((cnt[i][j] + 1) % 10);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void bfs(int r, int c) {
        Queue<Pair> q = new LinkedList<>();
        List<Pair> pairs = new ArrayList<>();
        Pair cur = new Pair(r, c), next;

        q.add(cur);
        visited[r][c] = true;

        while (!q.isEmpty()) {
            cur = q.poll();
            pairs.add(cur);

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + DIR[i][0];
                int nc = cur.c + DIR[i][1];
                next = new Pair(nr, nc);

                if (nr < 0 || nc < 0 || nr >=n || nc >= m) continue;
                if (map[nr][nc] == '1') {       // 다음 순회에서 1인 곳의 위치를 찾아야 되므로 방문 표시 제거
                    visited[nr][nc] = false;
                    continue;
                }
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.add(next);
            }
        }

        int size = pairs.size();
        for (Pair p: pairs) {
            cnt[p.r][p.c] = size;

            for (int i = 0; i < 4; i++) {
                int nr = p.r + DIR[i][0];
                int nc = p.c + DIR[i][1];

                if (nr < 0 || nc < 0 || nr >=n || nc >= m) continue;
                if (map[nr][nc] == '0' || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                cnt[nr][nc] += size;
            }
        }
    }

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
