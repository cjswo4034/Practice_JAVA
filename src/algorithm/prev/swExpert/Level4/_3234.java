package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3234 {
    static int n, tot, result;
    static int exponential[] = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 };
    static int factorial[] = { 0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            n = Integer.parseInt(br.readLine());
            int[] array = new int[n];
            boolean[] check = new boolean[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            tot = 0;
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(st.nextToken());
                tot += array[i];
            }

            result = 0;
            dfs(0, 0, 0, array, check);

            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
    }

    public static void dfs(int l, int r, int depth, int[] array, boolean[] check){
        if(l - r < 0) return;
        if(depth - n == 0){
            result++;
            return;
        }

        if (2 * l >= tot){
            result += factorial[n - depth] * exponential[n - depth];
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!check[i]){
                check[i] = true;
                dfs(l + array[i], r, depth + 1, array, check);
                dfs(l, r + array[i], depth + 1, array, check);
                check[i] = false;
            }
        }
    }
}