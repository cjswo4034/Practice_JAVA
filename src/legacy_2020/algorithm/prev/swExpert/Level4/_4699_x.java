package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class _4699_x {
    static int L, K;
    static int[] prices;
    static int[][] dp;
    static char[] inputs;

    // https://sangdo913.tistory.com/81
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());   // 문자의 길이
            K = Integer.parseInt(st.nextToken());   // 문자의 개수
            inputs = br.readLine().toCharArray();
            dp = new int[L][L];
            prices = new int[K];

            int a, b;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                prices[i] = min(a, b);
            }

            for (int i = 0; i < L; i++) {
                for (int j = i + 1; j < L; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int k = 1; k < L; k++) {
                int i = 0, j = k;
                for (int l = 0; l < L - k; l++) {
                    dp[i][j] = min(dp[i][j], dp[i + 1][j] + prices[inputs[i] - 'a']);
                    dp[i][j] = min(dp[i][j], dp[i][j - 1] + prices[inputs[j] - 'a']);
                    if (inputs[i] == inputs[j]) dp[i][j] = min(dp[i][j], dp[i + 1][j - 1]);
                    i++; j++;
                }
            }

            sb.append("#").append(t).append(" ").append(dp[0][L-1]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}