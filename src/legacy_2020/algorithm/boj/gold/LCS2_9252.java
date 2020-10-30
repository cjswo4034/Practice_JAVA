package algorithm.boj.gold;

import java.util.Scanner;

// Gold5
public class LCS2_9252 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars2 = sc.nextLine().toCharArray();
        char[] chars1 = sc.nextLine().toCharArray();
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];

        for (int row = 1; row <= chars1.length; row++) {
            for (int col = 1; col <= chars2.length; col++) {
                dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                if (chars1[row - 1] == chars2[col - 1])
                    dp[row][col] = dp[row - 1][col - 1] + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = chars1.length, j = chars2.length;
        while (dp[i][j] > 0) {
            if (dp[i][j] == dp[i - 1][j]) i--;
            else if (dp[i][j] == dp[i][j - 1]) j--;
            else if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                sb.append(chars1[--i]);
                j--;
            }
        }

        System.out.println(sb.length());
        if (sb.length() > 0) {
            System.out.println(sb.reverse().toString());
        }
    }

    private static boolean possible(int[][] dp, int row, int col) {
        if (dp[row - 1][col - 1] + 1 != dp[row][col]) return false;
        if (dp[row - 1][col] + 1 != dp[row][col]) return false;
        if (dp[row][col - 1] + 1 != dp[row][col]) return false;
        return true;
    }
}
