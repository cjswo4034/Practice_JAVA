package algorithm.prev.swExpert.Level4;

import java.io.*;

public class _6959 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            char[] str = br.readLine().toCharArray();
            char result = solve(str) % 2 == 0 ? 'A' : 'B';

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static int solve(char[] str){
        int cnt = 1;
        int tmp = str[0] - '0';
        for (int i = 1; i < str.length; i++) {
            tmp = tmp * 10 + str[i] - '0';
            while(tmp >= 10){
                tmp = tmp / 10 + tmp % 10;
                cnt++;
            }
        }
        return cnt;
    }
}

