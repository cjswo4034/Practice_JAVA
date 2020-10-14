package algorithm.미해결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO 시간초과 어떻게 해결할지 모르겠음
public class 돌던지기_3025_2_X {
    static int n, m;
    static int[] rockfallRow;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        rockfallRow = new int[m];

        for (int i = 0; i < n; i++) board[i] = br.readLine().toCharArray();
        for (int i = 0; i < m; i++) rockfallRow[i] = getRockfallRow(0, i);

        int t = Integer.parseInt(br.readLine());
        for (int i = 0, col; i < t; i++) {
            col = Integer.parseInt(br.readLine()) - 1;
            System.out.println(rockfallRow[col] + " : " + col);
            drop(rockfallRow[col], col);
        }

        display();
    }

    private static int getRockfallRow(int row, int col) {
        while (row < n && board[row][col] == '.') row++;
        return Math.max(0, row - 1);
    }

    private static void display() {
        StringBuilder sb = new StringBuilder();
        for (char[] row: board) {
            for (char e: row) sb.append(e);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void drop(int row, int col) {
        System.out.println("--> " + row + " : " + col);
        if (row + 1 < n && row >= 0 && board[row + 1][col] == 'O') {
            if (col > 0 && (board[row][col - 1] == '.' && board[row + 1][col - 1] == '.')) {
                drop(getRockfallRow(row, col - 1), col - 1);
                return;
            } else if (col + 1 < m && (board[row][col + 1] == '.' && board[row + 1][col + 1] == '.')) {
                drop(getRockfallRow(row, col + 1), col + 1);
                return;
            }
        }

        board[row][col] = 'O';
        rockfallRow[col] = getRockfallRow(rockfallRow[col], col);
    }
}