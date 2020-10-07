package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _7088 {
    static int n, q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[][] sum;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" \n");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            sum = new int[n + 1][4];

            int current;
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                current = Integer.parseInt(st.nextToken());
                System.arraycopy(sum[i - 1], 0, sum[i], 0, 4);
                sum[i][current]++;
            }

            int from, to;
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                for (int j = 1; j <= 3; j++) {
                    sb.append(sum[to][j] - sum[from - 1][j] + " ");
                }
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
