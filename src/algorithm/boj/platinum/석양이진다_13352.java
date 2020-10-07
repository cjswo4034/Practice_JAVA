package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// * 4명 밑이면 무조건 가능함
// * 5명 이상이면 고려
// * 선분이 두 개 이하일 때 점이 5개면 무조건 하나 이상의 선분을 구할 수 있다.

// 1. 하나의 선분을 구한다. (못구하면 Fail)
// 2. 1.에서 구한 선분 밖에 있는 점 두 개로 다른 하나의 선분을 구한다.
// 3. 두 개의 선분 위에 없는 점이 있으면 Fail
// 4. 마지막까지 실패하지 않으면 성공

public class 석양이진다_13352 {
    static int n;
    static long[][] arr;
    static Line line1, line2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new long[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        if (n <= 4) System.out.println("success");
        else {
            boolean flag = setLine();

            if (flag) {
                int temp1 = -1, temp2 = -1;
                for (int i = 0; i < n; i++) {
                    if (i == line1.p1 || i == line1.p2) continue;
                    if (!line1.isPointOnLine(i)) {
                        if (line2 != null) {
                            if (!line2.isPointOnLine(i)) {
                                flag = false;
                                break;
                            }
                            continue;
                        }

                        if (temp1 == -1) temp1 = i;
                        else if (temp2 == -1) temp2 = i;

                        if (temp2 != -1) line2 = new Line(temp1, temp2);
                    }
                }
            }
            System.out.println(flag ? "success" : "failure");
        }
    }

    static boolean setLine() {
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 5; k++) {
                    if (arePointsInSameLine(i, j, k)) {
                        line1 = new Line(i, j);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean arePointsInSameLine(int i, int j, int k) {
        long a = arr[i][0] * arr[j][1] + arr[j][0] * arr[k][1] + arr[k][0] * arr[i][1];
        a -= arr[i][1] * arr[j][0] + arr[j][1] * arr[k][0] + arr[k][1] * arr[i][0];
        return a == 0;
    }

    static class Line {
        int p1, p2;

        public Line(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public boolean isPointOnLine(int p) {
            return arePointsInSameLine(p1, p2, p);
        }
    }
}