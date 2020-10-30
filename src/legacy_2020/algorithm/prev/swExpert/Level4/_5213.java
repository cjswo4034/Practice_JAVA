package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _5213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        long[] array = new long[1000001];
        init(array);

        long[] sum = new long[1000001];

        sum[1] = array[1];
        for (int i = 2; i < 1000001; i++) {
            sum[i] = sum[i - 1] + array[i - 1];
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            long result = sum[r] - sum[l];

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void init(long[] array){
        for (int i = 1; i <= 1000000; i += 2) {
            for (int j = 1, leng = 1000000 / i; j <= leng; j++) {
                array[i * j] += i;
            }
        }
    }

    public static void init2(long[] array){
        for (int i = 1; i <= 1000000; i += 2) {
            for (int j = i; j <= 1000000; j += i) {
                array[j] += i;
            }
        }
    }
}