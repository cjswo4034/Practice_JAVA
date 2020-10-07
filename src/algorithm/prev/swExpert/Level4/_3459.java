package algorithm.prev.swExpert.Level4;

import java.io.*;

public class _3459 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            long n = Long.parseLong(br.readLine());

            String result;
            int length = length(n);
            if (length % 2 == 1){
                if (solveA(length) > n){
                    result = "Alice";
                } else {
                    result = "Bob";
                }
            } else {
                if (solveB(length) > n){
                    result = "Bob";
                } else {
                    result = "Alice";
                }
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static long solveA(int length){
        int cnt = 1;
        long current = 1;
        while(length-- > 1){
            if (cnt++ % 2 == 1){
                current = current * 2 + 1;
            } else {
                current = current * 2;
            }
        }
        return current;
    }

    public static long solveB(int length){
        int cnt = 1;
        long current = 1;
        while(length-- > 1){
            if (cnt++ % 2 == 1){
                current = current * 2;
            } else {
                current = current * 2 + 1;
            }
        }
        return current;
    }

    public static int length(long n){
        int cnt = 0;
        while(n > 0){
            if(n % 2 == 0) n = n /2;
            else n = (n - 1) / 2;
            cnt++;
        }
        return cnt;
    }
}