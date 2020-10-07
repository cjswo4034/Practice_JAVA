package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 시간초과. Set도 안되고 이전과 같을 때 넘기는 것도 안됨...
public class _2048_Hard_12094 {
    static int n, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int[][] origin = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                origin[i][j] = Integer.parseInt(st.nextToken());
        }

        answer = getMax(origin);
        backtracking(origin, 0);
        System.out.println(answer);
    }

    // 1. 현재 depth에서 최대값 * (2 ** 10 - depth) < answer 라면 갈 필요 없음
    // 2. origin과 move 이후 arr가 같다면 탐색 필요 없음 (merge나 move가 없다면 같은 걸로 봐도 됨)
    static void backtracking(int[][] origin, int depth) {
        if (depth == 10) return;

        int[][] copiedArr = new int[n][n];
        for (int direction = 0; direction < 4; direction++) {
            // origin 복사
            copyArr(origin, copiedArr);
            // 복사된 배열 움직이기
            move(copiedArr, direction);
            // 움직이기 전과 후가 같다면 건너뜀
            if (!isSame(origin, copiedArr)) backtracking(copiedArr, depth + 1);
        }
    }

    static boolean isSame(int[][] src, int[][] dest) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (src[i][j] != dest[i][j]) return false;
            }
        }
        return true;
    }

    static void copyArr(int[][] src, int[][] dest) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, n);
        }
    }

    static void move(int[][] map, int direction) {
        boolean flag = direction < 2;
        if (!flag) direction -= 2;

        // pointer: 0이 아닌 칸을 가리키는 포인터
        // zeroPointer: 시작위치부터 1씩 이동하는 포인터
        // runner: pointer 다음에 나오는 0이 아닌 칸
        // adder: 이동할 방향. (상,좌[1]: 0 -> n, 하,우[-1]: n -> 0)
        int pointer, zeroPointer, runner, adder = -direction | 1;

        if (flag) { // 상하
            for (int col = 0; col < n; col++) {
                zeroPointer = direction * (n - 1);
                pointer = getNextNoneZeroRow(map, zeroPointer, col, adder);
                if (pointer < 0 || pointer >= n) continue;  // 해당 열은 0만 있으니 건너뛰기

                while (pointer < n && pointer >= 0) {
                    map[zeroPointer][col] = map[pointer][col];
                    if (zeroPointer != pointer) map[pointer][col] = 0;  // pointer가 가리키는 수 지우기

                    runner = getNextNoneZeroRow(map,pointer + adder, col, adder);
                    if (runner < 0 || runner >= n) break;               // pointer 이후에 0이 없음

                    // pointer와 runner가 가리키는 수가 같다면
                    if (map[zeroPointer][col] == map[runner][col]) {
                        map[zeroPointer][col] *= 2;
                        map[runner][col] = 0;
                        if (answer < map[zeroPointer][col]) answer = map[zeroPointer][col];
                    }
                    zeroPointer += adder;

                    // pointer가 runner와 같다면 runner 다음에 나오는 0이 아닌 수를 가리킴
                    //     "        "     다르다면 runner를 가리킴
                    pointer = getNextNoneZeroRow(map, runner, col, adder);
                }
            }
        } else {    // 좌우
            for (int row = 0; row < n; row++) {
                zeroPointer = direction * (n - 1);
                pointer = getNextNoneZeroCol(map, row, zeroPointer, adder);
                if (pointer < 0 || pointer >= n) continue;

                while (pointer < n && pointer >= 0) {
                    map[row][zeroPointer] = map[row][pointer];
                    if (zeroPointer != pointer) map[row][pointer] = 0;

                    runner = getNextNoneZeroCol(map, row, pointer + adder, adder);
                    if (runner < 0 || runner >= n) break;

                    if (map[row][zeroPointer] == map[row][runner]) {
                        map[row][zeroPointer] *= 2;
                        map[row][runner] = 0;
                        if (answer < map[row][zeroPointer]) answer = map[row][zeroPointer];
                    }
                    zeroPointer += adder;
                    pointer = getNextNoneZeroCol(map, row, runner, adder);
                }
            }
        }
    }

    static int getNextNoneZeroRow(int[][] map, int row, int col, int adder) {
        while (row < n && row >= 0 && map[row][col] == 0) row += adder;
        return row;
    }

    static int getNextNoneZeroCol(int[][] map, int row, int col, int adder) {
        while (col < n && col >= 0 && map[row][col] == 0) col += adder;
        return col;
    }

    static int getMax(int[][] map) {
        int res = 0;
        for (int[] r : map)
            for (int e : r) res = Math.max(res, e);
        return res;
    }
}