package algorithm.boj.sliver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* [Silver 1] DP
 * 두 개의 나무 중 하나의 나무에서 초당 하나의 사과가 떨어질 때 30번 이하로 움직이면서 얻을 수 있는 사과의 최대값을 구해라.
 * 시작 위치는 1번나무.(0초일 때 1번)
 * - dp[i][j][type] = i초에서 이동횟수가 j번 남고 위치가 type 일 때 얻을 수 있는 사과의 최대값
 * - dp[i][j][type] = 최대값(이전 위치에서 그대로 있기, 이전 위치에서 움직이기)
 * - i초에 사과가 type에 떨어지면 위의 값에 + 1
 * */
public class 자두_2240 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[n + 1][m + 1][2];

        // 시작할 때 1번 나무 위치에 있으므로 0초에 이동횟수가 m번이고 1번 위치일 때를 제외한 나머지는 -1
        for (int i = 0; i <= m; i++) Arrays.fill(dp[0][i], -1);
        dp[0][m][0] = 0;

        for (int i = 1, type, a, b; i <= n; i++) {
            type = Integer.parseInt(br.readLine()) - 1;
            a = b = 0;
            if (type == 0) a = 1; else b = 1;
            for (int j = 0; j <= m; j++) {
                dp[i][j][0] = dp[i - 1][j][0] + a;
                dp[i][j][1] = dp[i - 1][j][1] + b;

                if (j + 1 <= m) {
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][j + 1][1] + a);
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j + 1][0] + b);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= m; i++) {
            if (ans < dp[n][i][0]) ans = dp[n][i][0];
            if (ans < dp[n][i][1]) ans = dp[n][i][1];
        }
        System.out.println(ans);
    }
}