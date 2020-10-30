package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* [Gold 2] 구현, 시뮬레이션
 * */
public class 소코반_4577 {
    static final int[][] DIR = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static final String INCOMPLETE = "incomplete";
    static final String COMPLETE = "complete";

    static int n, m, r, c;
    static char[][] map;
    static List<Point> targets = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            map = new char[n][m];
            targets.clear();

            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.' || map[i][j] == '#') continue;
                    if (map[i][j] == 'w' || map[i][j] == 'W') {
                        r = i;
                        c = j;
                    }
                    if (map[i][j] == '+' || map[i][j] == 'B' || map[i][j] == 'W') {
                        targets.add(new Point(i, j));
                    }
                }
            }

            boolean isEnded = false;
            for (char cmd : br.readLine().toCharArray()) {
                move(r, c, cmdToIdx(cmd), false);
                if (isEnded()) {
                    isEnded = true;
                    break;
                }
            }

            sb.append("Game ").append(t++).append(": ");
            sb.append(isEnded ? COMPLETE : INCOMPLETE).append("\n");
            for (char[] row : map) sb.append(row).append("\n");
        }
        System.out.print(sb);
    }

    public static boolean move(int curR, int curC, int dir, boolean isBox) {
        // cmd에 따라 움직인다.
        int nr = curR + DIR[dir][0];
        int nc = curC + DIR[dir][1];

        // 1. 다음칸이 벽인 경우
        if (nr <= 0 || nc <= 0 || nr >= n || nc >= m || map[nr][nc] == '#') return false;

        // 2. 현재 이동하려는 객체가 사람이면서
        //    a) 다음 칸이 빈 공간이거나 목표칸일 경우
        //    b) 다음 칸이 박스이면서 박스를 밀 수 있는 경우
        if (!isBox) {
            if (map[nr][nc] == '.' || map[nr][nc] == '+') {
                map[curR][curC] = map[curR][curC] == 'w' ? '.' : '+';
                map[nr][nc] = map[nr][nc] == '.' ? 'w' : 'W';
                r = nr; c = nc;
                return true;
            } else if (map[nr][nc] == 'b' || map[nr][nc] == 'B') {
                if (move(nr, nc, dir, true)) {
                    map[curR][curC] = map[curR][curC] == 'w' ? '.' : '+';
                    r = nr;
                    c = nc;
                }
            }
        } else if (map[nr][nc] == '.' || map[nr][nc] == '+') {
            // 박스를 미는 중일 때 다음 칸이 빈 공간이거나 목표칸일 경우
            map[curR][curC] = map[curR][curC] == 'b' ? 'w' : 'W';
            map[nr][nc] = map[nr][nc] == '.' ? 'b' : 'B';
            return true;
        }
        return false;
    }

    // 모든 목표칸 위에 박스가 있으면 True
    public static boolean isEnded() {
        for (Point p: targets) {
            if (map[p.r][p.c] != 'B') return false;
        }
        return true;
    }

    static int cmdToIdx(char cmd) {
        switch (cmd) {
            case 'L': return 0;
            case 'R': return 1;
            case 'U': return 2;
            default: return 3;
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
