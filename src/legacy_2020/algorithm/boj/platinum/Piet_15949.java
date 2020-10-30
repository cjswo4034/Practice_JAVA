package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// * 블록 내에서
// 1. dp 방향으로 가장 멀리 위치한 코델들을 찾는다.
// 2. cc 방향으로 가장 멀리 위치한 코델을 선택한다.
// 3. 맞닿은 블록들 중 dp 방향으로 맞닿은 블록이 다음 블록이 된다. (예외)

// * 예외 (검은 블록 영역 'X', 영역 외)
// 1. cc 값을 바꾼 뒤 1.로 돌아감
// 2. cc 값을 바꿔도 예외상황이면 dp 값을 바꾼 뒤 1.로 돌아감
// 3. dp 값을 바꿔도 예외상황이면 예외 1번으로 돌아감
// 4. 예외 3.에 두 번 도달할 경우 종료

// * 영역을 나누고 각 영역에서 8 방향의 블록들의 위치를 미리 구한다.
public class Piet_15949 {
    static int n, m, dp, cc;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static char[][] map;
    static Area[][] areas;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        areas = new Area[n][m];
        for (int i = 0; i < n; i++) map[i] = br.readLine().toCharArray();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (areas[i][j] != null) continue;
                areas[i][j] = new Area(map[i][j]);
                setArea(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        Area area = areas[0][0];

        do {
            sb.append(area.color);
            area = nextArea(area, 0);
        } while (area != null);

        System.out.println(sb);
    }

    static Area nextArea(Area area, int cnt) {
        if (cnt == 8) return null;
        Point nextPoint = new Point(area.getPoint(dp, cc));
        nextPoint.r += dir[dp][0];
        nextPoint.c += dir[dp][1];
        if (nextPoint.validation() || map[nextPoint.r][nextPoint.c] == 'X') {
            if (cnt % 2 == 0) cc ^= 1;
            else dp = (dp + 1) % 4;
            return nextArea(area, cnt + 1);
        }
        return areas[nextPoint.r][nextPoint.c];
    }

    static void setArea(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));

        char color = areas[r][c].color;

        Point p, n;
        while (!q.isEmpty()) {
            p = q.poll();
            areas[p.r][p.c].setPoint(p);

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dir[i][0];
                int nc = p.c + dir[i][1];
                n = new Point(nr, nc);

                if (n.validation()) continue;
                if (areas[nr][nc] != null || color != map[nr][nc]) continue;

                areas[nr][nc] = areas[r][c];
                q.add(n);
            }
        }
    }

    static class Area {
        char color;
        Point[][] points;

        public Area(char color) {
            this.color = color;
            this.points = new Point[4][2];

            int minV = 0, maxV = 987654321;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    points[i][j] = new Point(i + j <= 2 && i + j >= 1 ? minV : maxV, i + j <= 3 && i + j >= 2 ? maxV : minV);
                }
            }
        }

        public void setPoint(Point p) {
            // (0, 0) 가장 오른쪽(Max Col)의 왼쪽(Min Row)
            // (0, 1) 가장 오른쪽(Max Col)의 오른쪽(Max Row)
            if (points[0][0].c < p.c || (points[0][0].c == p.c && points[0][0].r >= p.r)) points[0][0] = p; // 왼쪽
            if (points[0][1].c < p.c || (points[0][1].c == p.c && points[0][1].r <= p.r)) points[0][1] = p; // 오른쪽

            // (2, 0) 가장 왼쪽(Min Col)의 왼쪽(Max Row)
            // (2, 1) 가장 왼쪽(Min Col)의 오른쪽(Min Row)
            if (points[2][0].c > p.c || (points[2][0].c == p.c && points[2][0].r <= p.r)) points[2][0] = p; // 왼쪽
            if (points[2][1].c > p.c || (points[2][1].c == p.c && points[2][1].r >= p.r)) points[2][1] = p; // 오른쪽

            // (1, 0) 가장 아래쪽(Max Row)의 왼쪽(Max Col)
            // (1, 1) 가장 아래쪽(Max Row)의 오른쪽(Min Col)
            if (points[1][0].r < p.r || (points[1][0].r == p.r && points[1][0].c <= p.c)) points[1][0] = p; // 왼쪽
            if (points[1][1].r < p.r || (points[1][1].r == p.r && points[1][1].c >= p.c)) points[1][1] = p; // 오른쪽

            // (3, 0) 가장 위쪽(Min Row)의 왼쪽(Min Col)
            // (3, 1) 가장 위쪽(Min Row)의 오른쪽(Max Col)
            if (points[3][0].r > p.r || (points[3][0].r == p.r && points[3][0].c >= p.c)) points[3][0] = p; // 왼쪽
            if (points[3][1].r > p.r || (points[3][1].r == p.r && points[3][1].c <= p.c)) points[3][1] = p; // 오른쪽
        }

        public Point getPoint(int dp, int cc) {
            return points[dp][cc];
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(Point p) {
            this(p.r, p.c);
        }

        public boolean validation() {
            return r < 0 || c < 0 || r >= n || c >= m;
        }
    }
}
