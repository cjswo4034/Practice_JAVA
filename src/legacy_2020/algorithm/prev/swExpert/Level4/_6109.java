package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _6109 {
    static String command;
    static int n;
    static int[][] map;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            bw.write("#" + t + "\n");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            command = st.nextToken();
            map = new int[n + 1][n +1 ];
            result = new int[n + 1][n + 1];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            switch(command){
                case "up": transferUp(); break;
                case "down": transferDown(); break;
                case "right": transferRight(); break;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] == -1 || map[i][j] == 0) continue;
                    int k = j + 1;
                    while(k < n){
                        if(map[i][j] == map[i][k]){
                            map[i][j] += map[i][k];
                            map[i][k] = -1;
                            break;
                        } else if(map[i][k] > 0)
                            break;
                        k++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                int k = 0;
                for (int j = 0; j < n; j++) {
                    if(map[i][j] > 0){
                        result[i][k++] = map[i][j];
                    }
                }
            }

            switch(command){
                case "up": transferDown(); break;
                case "down": transferUp(); break;
                case "right": transferRight(); break;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bw.write(map[i][j] + " ");
                }
                bw.write("\n");
            }
        }
        bw.flush();
    }

    public static void transfer(){
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = map[i][j];
                map[i][j] = map[j][i];
                map[j][i] = tmp;
            }
        }
    }

    public static void transferRight(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = map[i][j];
                map[i][j] = map[i][n - j - 1];
                map[i][n - j - 1] = tmp;
            }
        }
    }

    public static void transferUp(){
        transfer();
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = map[i][j];
                map[i][j] = map[n - i - 1][j];
                map[n - i - 1][j] = tmp;
            }
        }
    }

    public static void transferDown(){
        transfer();
        transferRight();
    }
}