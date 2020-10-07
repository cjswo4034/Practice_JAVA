package algorithm.prev.swExpert.Level4;

import java.io.*;

public class _7965 {
    private static final int DIV = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        long [] arr = init();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append("#").append(t).append(" ").append(arr[n]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static long [] init(){
        long [] arr = new long[1000001];
        for (int i = 1; i < arr.length; i++)
            arr[i] = (arr[i - 1] + pow(i, i)) % DIV;
        return arr;
    }

    private static long pow(long num, long num2){
        if (num2 == 0) return 1;
        else if (num2 % 2 == 0) return pow((num * num) % DIV, num2 / 2);
        else return (pow((num * num) % DIV, num2 / 2) * num) % DIV;
    }
}
