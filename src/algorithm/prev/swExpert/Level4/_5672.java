package algorithm.prev.swExpert.Level4;

import java.io.*;

public class _5672 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n; char[] input; String result;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            input = new char[n];
            for (int i = 0; i < n; i++)
                input[i] = br.readLine().toCharArray()[0];

            result = solution(input);
            sb.append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static String solution(char[] input){
        StringBuilder sb = new StringBuilder();
        int l = 0, r = input.length - 1;
        while(l <= r){
            if (input[l] > input[r]){
                sb.append(input[r--]);
            } else if (input[l] < input[r]){
                sb.append(input[l++]);
            } else {
                int check = getCheck(input, l, r);
                if (check == 0){
                    sb.append(input[r--]);
                } else if (check == 1){
                    sb.append(input[l++]);
                } else {
                    while(l <= r) sb.append(input[r--]);
                }
            }
        }
        return sb.toString();
    }

    public static int getCheck(char[] input, int left, int right){
        while(left++ < right--){
            if (input[left] > input[right]){
                return 0;   // right쪽이 더 작을 때
            } else if (input[left] < input[right]){
                return 1;   // left쪽이 더 작을 때
            }
        }
        return -1;
    }
}