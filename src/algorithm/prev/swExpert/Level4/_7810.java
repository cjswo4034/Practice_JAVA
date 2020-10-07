package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _7810 {
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int n, max, tmp, sum;
        int [] arr = new int[1000001];
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            max = sum = 0;
            Arrays.fill(arr, 0, n + 1, 0);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                tmp = Integer.parseInt(st.nextToken());
                max = Math.max(tmp, max);
                arr[tmp]++;
                sum++;
            }

            result = 0;
            for (int i = 0; i < max; i++) {
                if (i <= sum) result = i;
                else break;
                sum -= arr[i];
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
