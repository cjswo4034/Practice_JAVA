package algorithm.미해결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO 시간초과 도저히 어떻게 해결할지 모르겠음
public class 돌던지기_3025 {
    static int n, m;
    static char[][] board;
    static RockfallPoint[][] rockfallPoints;

    public static void main(String[] args) throws Exception {
//        File f = new File("C:\\Users\\cjswo\\Downloads\\contest6_testdata\\kamen.in.10");
//        FileReader fr = new FileReader(f);
//        BufferedReader br = new BufferedReader(fr);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        rockfallPoints = new RockfallPoint[n][m];

        for (int i = 0; i < n; i++) board[i] = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X') continue;
                rockfallPoints[i][j] = new RockfallPoint(i, j);
            }
        }

        for (int i = 0; i < m; i++) rockfallPoints[0][i].mid = rockfallPoints[findRockfallRow(0, i)][i];

        int t = Integer.parseInt(br.readLine()), col;
        while (t-- > 0) {
            col = Integer.parseInt(br.readLine()) - 1;
            drop(rockfallPoints[0][col].mid);
        }
        System.out.println(display());
    }

    static int findRockfallRow(int row, int col) {
        while (row < n && board[row][col] == '.') row++;
        return row - 1;
    }

    static String display() {
        StringBuilder sb = new StringBuilder();
        for (char[] row: board) {
            for (char e: row) sb.append(e);
            sb.append("\n");
        }
        return sb.toString();
    }

    // point: 열에서 놓을 수 있는 가장 아래에 있는 지점
    static void drop(RockfallPoint point) {
        int move = point.enableFallingRock();
        if (move == -1 && point.c > 0) {
            RockfallPoint left = point.left;
            if (left == null) {
                if (point.r < rockfallPoints[0][point.c - 1].mid.r) left = rockfallPoints[0][point.c - 1].mid;
                else left = rockfallPoints[findRockfallRow(point.r, point.c - 1)][point.c - 1];
            }

            drop(left);

            if (board[left.r][left.c] == 'O') left = rockfallPoints[left.r - 1][left.c];
            point.left = left;
        } else if (move == 1 && point.c + 1 < m) {
            RockfallPoint right = point.right;
            if (right == null) {
                if (point.r < rockfallPoints[0][point.c + 1].mid.r) right = rockfallPoints[0][point.c + 1].mid;
                else right = rockfallPoints[findRockfallRow(point.r, point.c + 1)][point.c + 1];
            }

            drop(right);

            if (board[right.r][right.c] == 'O') right = rockfallPoints[right.r - 1][right.c];
            point.right = right;
        } else {
            board[point.r][point.c] = 'O';
            if (point.left != null && point.left.right == point) point.left.right = rockfallPoints[point.r - 1][point.c];
            if (point.right != null && point.right.left == point) point.right.left = rockfallPoints[point.r - 1][point.c];
            if (rockfallPoints[0][point.c].mid == point) rockfallPoints[0][point.c].mid = rockfallPoints[point.r - 1][point.c];
        }
    }

    static class RockfallPoint {
        RockfallPoint left, mid, right;
        int r, c;

        public RockfallPoint(int r, int c) {
            this.r = r;
            this.c = c;
        }

        // res: -1=왼쪽, 0=가능, 1=오른쪽
        public int enableFallingRock() {
            if (r == n - 1 || board[r + 1][c] == 'X') return 0;
            else if (r + 1 < n) {   // board[r + 1][c] == 'O'
                if (enableFallingRockLeft()) return -1;
                if (enableFallingRockRight()) return 1;
            }
            return 0;
        }

        public boolean enableFallingRockLeft() {
            return (c > 0 && board[r][c - 1] == '.' && board[r + 1][c - 1] == '.');
        }

        public boolean enableFallingRockRight() {
            return (c + 1 < m && board[r][c + 1] == '.' && board[r + 1][c + 1] == '.');
        }

        @Override
        public String toString() {
            return "RockfallPoint{" +
                    "next=" + right +
                    ", r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
