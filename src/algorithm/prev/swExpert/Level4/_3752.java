package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3752 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, result; int[] arr; boolean[] dp;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            n = Integer.parseInt(br.readLine());
            arr = new int[n];

            dp = new boolean[n * 100 + 1];
            dp[0] = true;

            int sum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
            }

            for (int i = 0; i < n; i++) {
                for (int j = sum; j >= 0 ; j--) {
                    if (dp[j]){
                        dp[j + arr[i]] = true;
                    }
                }
            }

            result = 0;
            for (int i = 0; i <= sum; i++) {
                if(dp[i]) result++;
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
