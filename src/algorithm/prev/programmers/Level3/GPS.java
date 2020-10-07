package algorithm.prev.programmers.Level3;

import java.util.Arrays;

// https://tech.kakao.com/2017/09/13/code-festival-round-2/
public class GPS {
    public static void main(String[] args) {
        GPS gpsX = new GPS();
        Solution solution = gpsX.new Solution();
        int res = solution.solution(7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[]{1, 2, 4, 6, 5, 7});
        System.out.println(res);
    }

    class Solution {
        private int INF = 987654321;
        public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
            boolean[][] list = new boolean[n + 1][n + 1];
            int[][] dp = new int[k][n + 1];

            for (int[] ints : edge_list) {
                list[ints[0]][ints[1]] = true;
                list[ints[1]][ints[0]] = true;
                list[ints[0]][ints[0]] = list[ints[1]][ints[1]] = true;
            }

            for (int[] ints : dp) Arrays.fill(ints, INF);
            dp[0][gps_log[0]] = 0;

            for (int i = 1; i < k; i++) {
                for (int j = 0; j <= n; j++) {
                    for (int l = 0; l <= n; l++) {
                        if (!list[l][j]) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][l] + (gps_log[i] == j ? 0 : 1));
                    }
                }
            }

            int res = dp[k - 1][gps_log[k - 1]];
            return res >= INF ? -1 : res;
        }
    }
}
