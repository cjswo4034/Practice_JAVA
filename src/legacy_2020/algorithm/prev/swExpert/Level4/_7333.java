package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;

public class _7333 {
    static int cnt, n, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] arr;
        boolean[] check;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            check = new boolean[n];

            cnt = result = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
                if(arr[i] >= 50) cnt++;
            }

            Arrays.sort(arr);
            result = test(arr, check, n - cnt - 1) + cnt;

            sb.append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static int test(int[] arr, boolean[] check, int pos){
        int result = 0, count, j;
        for (int i = pos; i >= 0; i--) {
            if(check[i]) continue;
            check[i] = true;
            count = 1; j = 0;
            while(count * arr[i] < 50) count++;
            while(j < i && check[j]) j++;
            while(count-- > 1) {
                if(check[j]) break;
                check[j++] = true;
            }
            if (count > 0) return result;
            result++;
        }
        return result;
    }
}
