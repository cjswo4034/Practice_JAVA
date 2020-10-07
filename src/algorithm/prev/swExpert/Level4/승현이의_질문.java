package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 승현이의_질문 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] arr;
        int T = Integer.parseInt(br.readLine()), n, result, tot;
        for(int t = 1; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            tot = n = Integer.parseInt(st.nextToken());
            arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            result = 0;
            Arrays.sort(arr);
            for (int i = 0; i < n; i++) {
                if (tot <= arr[i]) result = Math.max(result, tot);
                tot--;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}