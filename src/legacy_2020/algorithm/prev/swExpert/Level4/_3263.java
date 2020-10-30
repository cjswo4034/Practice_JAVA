package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3263 {
    static int n, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
 
        int[] dp, pos;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            dp = new int[n + 1];
            pos = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                pos[Integer.parseInt(st.nextToken())] = i;
                dp[i] = 1;
            }

            result = 1;
            for (int i = 2; i <= n; i++) {
                if (pos[i] > pos[i - 1]) {
                    dp[i] = dp[i - 1] + 1;
                    result = Math.max(result, dp[i]);
                }
            }
            sb.append("#").append(t).append(" ").append(n - result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}