package algorithm.미해결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 전투시뮬레이션_19537 {
    static final int[][] DIR = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static final int INF = Integer.MAX_VALUE;
    // 이동 시 험준도만큼 스테미너 소모. 일부 지형 이동 불가.
    // 세력이 다른 유닛이 인접해 있다면 교전상태
    // 한 번의 약진에 최대로 소모할 수 있는 스테미너 총량이 있음 -> 이동력
    // 약진: 특정 목표지점까지 한번에 이동하는 것
    //       - 중간에 같은 세력 있으면 통과, 적대 세력 있으면 그 전에 멈춤. 교전 상태라면 통과
    // 임의의 유닛을 선택하여 약진 명령을 내리는 봇
    static int n, h, w, m, k;
    static int[][] map, unitMap, visit;
    static Unit[] units;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        visit = new int[h][w];
        unitMap = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] cost = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = cost[map[i][j]];
            }
        }

        m = Integer.parseInt(br.readLine());
        units = new Unit[m + 1];
        units[0] = new Unit();
        for (int i = 1, r, c; i <= m; i++) {
            units[i] = new Unit(i, new StringTokenizer(br.readLine()));
            r = units[i].r;
            c = units[i].c;
            unitMap[r][c] = i;
            units[i].isAtWar = units[i].findEnemies(r, c, true);
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0, u, a, b; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            units[u].move(a, b);
        }

        for (int i = 1; i <= m; i++)
            sb.append(units[i]).append("\n");

        System.out.print(sb);
    }

    static class Unit {
        int idx, m, t, r, c;     // 이동력, 세력, y, x;
        boolean isAtWar;

        public Unit() {
        }

        public Unit(int idx, StringTokenizer st) {
            this.idx = idx;
            this.m = Integer.parseInt(st.nextToken());
            this.t = Integer.parseInt(st.nextToken());
            this.r = Integer.parseInt(st.nextToken());
            this.c = Integer.parseInt(st.nextToken());
        }

        public void move(int nr, int nc) {
            if (!movable(nr, nc)) return;

            // 교전 상태라면 약진할 때 벗어날 수 있다. (같은 상대를 조우하면 교전 상태가 됨)
            // 1. 교전 이전에 적대 적보를 지움
            // 2. 목적지까지 이동할 수 있으면 이동함
            // 3. 목적지에서 주변에 적 정보를 찾아본다.

            Queue<Point> q = initBeferMove();
            if (move(q, nr, nc)) {
                unitMap[r][c] = 0;
                r = nr;
                c = nc;
                unitMap[r][c] = idx;
            }

            isAtWar = findEnemies(r, c, true);
        }

        private Queue<Point> initBeferMove() {
            Queue<Point> q = new LinkedList<>();

            // 1. 주변 이동 가능 지역을 q에 담는다.
            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i][0];
                int nc = c + DIR[i][1];

                if (nr < 0 || nc < 0 || nr >= h || nc >= w || map[nr][nc] == -1) continue;
                if (unitMap[nr][nc] != 0 && (units[unitMap[nr][nc]].t != t && !units[unitMap[nr][nc]].isAtWar)) continue;
                if (map[nr][nc] > m) continue;

                visit[nr][nc] = m - map[nr][nc];
                q.add(new Point(nr, nc, m - map[nr][nc]));
            }
            return q;
        }

        // 주변에 적이 있으면 찾음
        private boolean findEnemies(int r, int c, boolean flag) {
            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i][0];
                int nc = c + DIR[i][1];

                if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                if (map[nr][nc] == -1 || unitMap[nr][nc] == 0) continue;
                if (units[unitMap[nr][nc]].t != t && !units[unitMap[nr][nc]].isAtWar) {
                    units[unitMap[nr][nc]].isAtWar = flag;
                    return true;
                }
            }
            return false;
        }

        // 명령 수행이 불가능한 경우
        private boolean movable(int nr, int nc) {
            // 1. 험준한 지형
            if (map[nr][nc] == -1) return false;
            // 2. 다른 유닛이 있음
            if (unitMap[nr][nc] != 0) return false;
            // 3. 이동력의 한계
            return map[nr][nc] <= m;
        }

        /**
         * * 다음과 같은 상황에서는 이동이 불가능하다.
         * 1. 다음 지역이 이동 불가능한 지역이라면 건너뛴다.
         * 2. 현재 지점이 적대 세력과 인접한 지역이라면 이동할 수 없다.
         * 3. 다음 지역으로 이동할 수 있는 이동력이 부족하다면 건너뛴다.
         * 4. 다음 지역으로 이동력을 덜 소모하면서 이동할 수 있는 방법이 있다면 건너뛴다.
         * <p>
         * * 다음 지역이 목적지라면 정보를 담고 반환한다.
         *
         * @param destR 이동할 행
         * @param destC 이동할 열
         * @return 목적지까지 도착할 수 있으면 목적지를 반환하고,
         * 목적지까지 이동할 방법이 없고 가는 도중 적대세력을 조우했다면 해당 지점을 반환하고,
         * 이동력이 부족하거나 이동할 수 없다면 null을 반환한다.
         */
        private boolean move(Queue<Point> q, int destR, int destC) {
            List<Point> routes = new ArrayList<>(q);
            boolean res = false;

            Point cur, next;
            THIS:
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    cur = q.poll();

                    if (!findEnemies(cur.r, cur.c, false)) {
                        for (int i = 0; i < 4; i++) {
                            int nr = cur.r + DIR[i][0];
                            int nc = cur.c + DIR[i][1];

                            // 1. 이동이 불가능한 지역.
                            if (nr < 0 || nc < 0 || nr >= h || nc >= w || map[nr][nc] == -1) continue;

                            // 2. 적대세력이 존재함. 같은 팀이면 이동 가능하다.
                            if (unitMap[nr][nc] != 0 && (units[unitMap[nr][nc]].t != t && !units[unitMap[nr][nc]].isAtWar)) continue;

                            int nS = cur.stamina - map[nr][nc];

                            // * 다음 지역이 도착지라면 반환한다.
                            if (nS >= 0 && nr == destR && nc == destC) {
                                res = true;
                                break THIS;
                            }

                            // 3. 이동력이 부족하거나
                            // 4. 더 효율적으로 올 수 있다면 건너뛴다.
                            if (nS < 0 || visit[nr][nc] >= nS) continue;

                            // visit을 초기화하는 모든 지역을 담는다.
                            visit[nr][nc] = nS;
                            next = new Point(nr, nc, nS);
                            q.add(next);
                            routes.add(next);
                        }
                    }
                }
            }

            processBeforeExit(routes);
            return res;
        }

        private void processBeforeExit(List<Point> route) {
            for (Point p : route) visit[p.r][p.c] = 0;
        }

        @Override
        public boolean equals(Object o) {
            Unit unit = (Unit) o;
            return idx == unit.idx;
        }

        @Override
        public int hashCode() {
            return idx;
        }

        @Override
        public String toString() {
            return r + " " + c;
        }
    }

    static class Point {
        int r, c, stamina;

        public Point(int r, int c, int stamina) {
            this.r = r;
            this.c = c;
            this.stamina = stamina;
        }

        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;
            if (r != point.r) return false;
            return c == point.c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", stamina=" + stamina +
                    '}';
        }
    }
}
