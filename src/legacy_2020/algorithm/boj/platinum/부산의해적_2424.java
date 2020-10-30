package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1. 수아와 해적은 동시에 움직인다.
// 2. 해적과 수아가 일직선상에 있으면 수아는 죽음

public class 부산의해적_2424 {
    static int n, m;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Node> q = new LinkedList<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        char ch;
        int vR = 0, vC = 0;
        int destR = 0, destC = 0;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                ch = map[i][j];
                if (ch == '.' || ch == 'I') continue;
                if (ch == 'T') {
                    destR = i;
                    destC = j;
                } else if (ch == 'V') {
                    vR = i;
                    vC = j;
                } else q.add(new Node(i, j, false));
                map[i][j] = '.';
            }
        }

        q.add(new Node(vR, vC, true));
        System.out.println(bfs(q, destR, destC) ? "YES" : "NO");
    }

    static boolean bfs(Queue<Node> q, int destR, int destC) {
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (!node.isPirate) {
                if (isInRangeOfPirates(node.r, node.c)) continue;
                if (node.r == destR && node.c == destC) return true;
            }

            for (int i = 0; i < 4; i++) {
                int nr = node.r + dir[i][0];
                int nc = node.c + dir[i][1];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (map[nr][nc] == 'I') continue;
                if (!node.isPirate && map[nr][nc] != '.') continue;
                if (node.isPirate && map[nr][nc] == 'V') continue;

                map[nr][nc] = node.isPirate ? 'V' : 'Y';
                q.add(new Node(nr, nc, node.isPirate));
            }
        }
        return false;
    }

    // 단점: 같은 칸을 중복해서 찾는다. -> 해적의 범위를 설정하는 배열로 바꾸기
    static boolean isInRangeOfPirates(int r, int c) {
        for (int i = r + 1; i < n && map[i][c] != 'I'; i++)
            if (map[i][c] == 'V') return true;
        for (int i = r; i >= 0 && map[i][c] != 'I'; i--)
            if (map[i][c] == 'V') return true;
        for (int i = c; i < m && map[r][i] != 'I'; i++)
            if (map[r][i] == 'V') return true;
        for (int i = c; i >= 0 && map[r][i] != 'I'; i--)
            if (map[r][i] == 'V') return true;
        return false;
    }

    static class Node {
        int r, c;
        boolean isPirate;

        public Node(int r, int c, boolean isPirate) {
            this.r = r;
            this.c = c;
            this.isPirate = isPirate;
        }
    }
}