package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* [Gold2]
* 모든 더러운 칸을 최소한으로 이동하여 청소했을 때 이동거리를 구해야 된다.
* 모든 더러운 칸을 청소할 수 없다면 -1을 반환한다.
*  -> 최소거리, 배열 탐색: BFS
*  -> 적은 개수의 특정 지점들을 방문해야됨: 비트마스킹
* */
public class 로봇청소기_4991 {
    static final int[][] DIR = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static final int INF = 987654321;
    static int n, m;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if (m + n == 0) break;

            int startR = 0, startC = 0, k = 0;

            map = new char[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.') continue;
                    if (map[i][j] == '*') map[i][j] = (char) k++;
                    else if (map[i][j] != 'x') {
                        startR = i;
                        startC = j;
                        map[i][j] = '.';
                    }
                }
            }
            sb.append(bfs(startR, startC, k)).append("\n");
        }
        System.out.println(sb);
    }

    /* *
     * visit[i][j][visited]: visited에 표시된 더러운 칸을 청소하고 i행 j열을 방문했을 때 최소 이동 회수
     * visited: 더러운 칸을 방문하면 (1 << 더러운 칸의 Index)의 위치에 1 마킹
     *
     * @param startR 시작 row
     * @param startC 시작 column
     * @param k 더러운 칸의 개수
     * @return 더러운 칸을 깨끗한 칸으로 바꾸는 이동 횟수의 최소값
    * */
    static int bfs(int startR, int startC, int k) {
        Queue<Point> q = new LinkedList<>();
        int[][][] visit = new int[n][m][1 << k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                Arrays.fill(visit[i][j], INF);
        }

        q.add(new Point(startR, startC, 0));
        visit[startR][startC][0] = 0;

        int step = 0, res = INF, allVisited = (1 << k) - 1;
        Point cur;
        while (!q.isEmpty()) {
            step++;
            for (int i = 0, size = q.size(); i < size; i++) {
                cur = q.poll();

                for (int[] d: DIR) {
                    int nr = cur.r + d[0];
                    int nc = cur.c + d[1];
                    int nk = cur.visited;

                    if (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 'x') continue;
                    if (map[nr][nc] != '.' && (nk & (1 << k)) <= 0) {
                        nk |= (1 << map[nr][nc]);
                        if (nk == allVisited) {
                            if (res > step) res = step;
                            continue;
                        }
                    }

                    // 동일한 더러운 칸을 청소하고 다음 칸을 지금보다 적은 이동횟수로 갈 수 있다면 건너뛴다.
                    if (visit[nr][nc][nk] <= step) continue;

                    visit[nr][nc][nk] = step;
                    q.add(new Point(nr, nc, nk));
                }
            }
        }
        return res != INF ? res : -1;
    }

    static class Point {
        int r, c, visited;
        // visited -> 지금까지 청소한 구역을 비트마스트로 표시

        public Point(int r, int c, int visited) {
            this.r = r;
            this.c = c;
            this.visited = visited;
        }
    }
}
