package algorithm.prev.swExpert.Level4;

import java.io.*;

public class _7103 {
    static int n, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int MAX = (int)Math.sqrt(32767);
        int arr[] = new int[32768];
        for (int i = 0; i <= MAX; i++) {
            for (int j = i; j <= MAX; j++) {
                int sum1 = (int)Math.pow(i, 2) + (int)Math.pow(j, 2);
                if (sum1 > 32767) break;
                for (int k = j; k <= Math.sqrt(32767); k++) {
                    int sum2 = sum1 + (int)Math.pow(k, 2);
                    if (sum2 > 32767) break;
                    for (int l = k; l <= Math.sqrt(32767); l++) {
                        int sum3 = sum2 + (int)Math.pow(l, 2);
                        if (sum3 > 32767) break;
                        arr[sum3]++;
                    }
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            sb.append(arr[n]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
