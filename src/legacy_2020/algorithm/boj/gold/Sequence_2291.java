package algorithm.boj.gold;

import java.util.Arrays;
import java.util.Scanner;

// gold 4
public class Sequence_2291 {
    private static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= m; i++) dp[1][i] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= m; j++) {
                if (i == j) dp[i][j] = 1;
                for (int l = 1; l <= i; l++) {
                    dp[i][j] += dp[l][j - i];
                }
            }
        }

        int[] res = recursive(n, m, k);
        StringBuilder sb = new StringBuilder();
        for (int i = res.length - 1; i >= 0; i--)
            sb.append(res[i]).append(" ");
        System.out.println(sb.toString().trim());
    }

    // 합이 m인 n 자리 수의 k번째 수를 반환
    private static int[] recursive(int n, int m, int k) {
        if (n == 1) return new int[]{m};

        int[] res = new int[n];
        Arrays.fill(res, 1);
        for (int i = 1, mem = m - n; i <= n; i++) {
            if (k - dp[i][mem] <= 0) {
                int[] ret = recursive(i, mem, k);
                for (int j = 0; j < ret.length; j++) res[j] += ret[j];
                break;
            }
            k -= dp[i][mem];
        }
        return res;
    }
}
