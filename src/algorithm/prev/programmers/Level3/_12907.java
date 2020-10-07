package algorithm.prev.programmers.Level3;

import java.util.stream.IntStream;

public class _12907 {
    public static void main(String[] args) {
        _12907 prob = new _12907();
        System.out.println(prob.solution(6, new int[]{1, 2, 5}));
    }

    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        /*for (int i = 0; i < money.length; i++) {
            for (int j = money[i]; j <= n; j++) {
                dp[j] += dp[j - money[i]];
            }
        }*/

        /*IntStream.of(money)
                .forEach(value -> IntStream.range(value, n + 1)
                        .forEach(i -> dp[i] = dp[i] + dp[i - value]));*/

        return IntStream.of(money)
                .reduce(0, (left, right) -> IntStream.range(right, n + 1)
                .reduce(0, (left1, right1) -> dp[right1] = dp[right1] + dp[right1 - right]));
//        return dp[n];
    }
}