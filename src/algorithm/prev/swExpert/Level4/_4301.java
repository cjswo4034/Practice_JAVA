package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _4301 {
    static int result, n, m;
    static boolean [][] map = new boolean[1001][1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean thisTile = true;
        for (int i = 1; i <= 1000; i += 2) {
            for (int j = 1; j <= 1000; j += 2) {
                if(thisTile){
                    map[i][j] = map[i][j + 1] = true;
                    map[i + 1][j] = map[i + 1][j + 1] = true;
                }
                thisTile = !thisTile;
            }
            thisTile = !thisTile;
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            result = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if(map[i][j]){
                        result++;
                    }
                }
            }

            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
    }
}
