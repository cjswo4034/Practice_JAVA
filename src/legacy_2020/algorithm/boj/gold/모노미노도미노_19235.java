package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모노미노도미노_19235 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Domino domino = new Domino();

        for (int i = 0, type, x, y; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            domino.dropBlock(type, x, y);
        }

        System.out.println(domino.getPoint());
        System.out.println(domino.getCount());
    }

    static class Domino {
        Board green, blue;
        int point;

        public Domino() {
            this.green = new Board();
            this.blue = new Board();
        }

        void dropBlock(int type, int x, int y) {
            green.dropBlock(type, y);
            blue.dropBlock(type == 1 ? 1 : ((type - 2) ^ 1) + 2, x); // type 2 <-> 3 바꿈

            int res;
            while ((res = green.pang()) != 0) point += res;
            while ((res = blue.pang()) != 0) point += res;

            green.clear();
            blue.clear();
        }

        int getPoint() {
            return point;
        }

        int getCount() {
            return green.getCount() + blue.getCount();
        }

        static class Board {
            int[][] board = new int[6][4];

            // type 1: 1x1, type 2: 1x2, type 3: 2x1
            void dropBlock(int type, int col) {
                int row = 0;
                if (type == 2) {
                    while (row < 6 && board[row][col] == 0 && board[row][col + 1] == 0) row++;
                    board[row - 1][col] = board[row - 1][col + 1] = type;
                } else {
                    while (row < 6 && board[row][col] == 0) row++;
                    board[row - 1][col] = type;
                    if (type != 1) board[row - 2][col] = type;
                }
            }

            int pang() {
                int res = 0, bottom = -1;
                for (int row = 2; row < 6; row++) {
                    boolean flag = true;

                    for (int b: board[row])
                        flag &= (b != 0);

                    if (flag) {
                        remove(row);
                        res++;
                        if (bottom < row) bottom = row;
                    }
                }
                if (bottom != -1) arrange(bottom);
                return res;
            }

            void clear() {
                int cnt = 0;
                for (int row = 0; row < 2; row++) {
                    for (int b: board[row])
                        if (b != 0) {
                            cnt++;
                            break;
                        }
                }
                while (cnt-- > 0) move(cnt);
            }

            void remove(int row) {
                for (int col = 0; col < 4; col++)
                    board[row][col] = 0;
            }

            void arrange(int row) {
                for (int r = row - 1; r >= 0; r--) {
                    for (int c = 0; c < 4; c++) {
                        if (board[r][c] == 0) continue;

                        int bottom = r + 1; // 빈 공간이면서 가장 밑인 행
                        if (c < 3 && board[r][c] == 2 && board[r][c + 1] == 2) {
                            while (bottom < 6 && board[bottom][c] == 0 && board[bottom][c + 1] == 0) bottom++;
                            bottom--;
                            board[r][c] = board[r][c + 1] = 0;
                            board[bottom][c] = board[bottom][c + 1] = 2;
                        } else {
                            while (bottom < 6 && board[bottom][c] == 0) bottom++;
                            bottom--;
                            board[bottom][c] = board[r][c];
                            board[r][c] = 0;
                        }
                    }
                }
            }

            void move(int cnt) {
                for (int row = 4; row >= 0; row--) {
                    System.arraycopy(board[row], 0, board[row + 1], 0, 4);
                }
                remove(1 - cnt);
            }

            int getCount() {
                int res = 0;
                for (int[] row: board) {
                    for (int e: row) if (e != 0) res++;
                }
                return res;
            }
        }
    }
}
