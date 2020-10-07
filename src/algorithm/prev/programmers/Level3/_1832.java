package algorithm.prev.programmers.Level3;

import java.util.Arrays;

public class _1832 {
    int MOD = 20170805;
    public static void main(String[] args) {
        _1832 prob = new _1832();
        System.out.println(prob.solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(prob.solution(3, 3, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));
    }

    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[cityMap.length][cityMap[0].length];
        dp[0][0] = 1;

        for (int i = 0; i < cityMap.length; i++) {
            for (int j = 0; j < cityMap[i].length; j++) {
                if (cityMap[i][j] == 1) {
                    dp[i][j] = 0;continue;
                }
                if (i - 1 >= 0) {
                    if (cityMap[i - 1][j] == 0) dp[i][j] += dp[i - 1][j];
                    else if (cityMap[i - 1][i] == 2) {
                        int tmpI = i - 1;
                        while (tmpI >= 0 && dp[tmpI][j] > 0) tmpI--;
                        if (tmpI >= 0) dp[i][j] += dp[tmpI][j];
                    }
                }
                if (j - 1 >= 0) {
                    if (cityMap[i][j - 1] == 0) dp[i][j] += dp[i][j - 1];
                    else if (cityMap[i][j - 1] == 2) {
                        int tmpJ = j - 1;
                        while (tmpJ >= 0 && dp[i][tmpJ] > 0) tmpJ--;
                        if (tmpJ >= 0) dp[i][j] += dp[i][tmpJ];
                    }
                }
            }
        }
        for (int[] arr : cityMap){
            System.out.println(Arrays.toString(arr));
        }

        for (int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }

        return dp[m - 1][n - 1];
    }
}