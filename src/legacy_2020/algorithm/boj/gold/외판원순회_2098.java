package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 외판원순회_2098 {
    static final int IMPOSSIBLE = 987654321;
    static int n, allVisited;
    static int[][] w, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = new int[n][n];
        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) w[i][j] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }

        allVisited = (1 << n) - 1;
        System.out.println(TSP(0, 1));

        for (int[] r: dp) System.out.println(Arrays.toString(r));
    }

    // visited -> O(2^N)
    // current -> O(N)
    // 부분 문제를 푸는데 O(N) -> O(N^2 * 2^N)
    static int TSP(int current, int visited) {
        if (dp[current][visited] != -1) return dp[current][visited];
        if (visited == allVisited) {
            if (w[current][0] != 0) return w[current][0];
            return IMPOSSIBLE;
        }

        dp[current][visited] = IMPOSSIBLE;
        for (int next = 0; next < n; next++) {
            if ((visited & (1 << next)) > 0 || w[current][next] == 0) continue;
            dp[current][visited] = Math.min(
                    dp[current][visited],
                    TSP(next, visited | (1 << next)) + w[current][next]);
        }

        return dp[current][visited];
    }
}