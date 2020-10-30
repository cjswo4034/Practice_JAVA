package algorithm.prev.programmers.Level2;

import java.io.*;

public class _12899 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            int input = Integer.parseInt(br.readLine());
            System.out.println("#" + i + " : " + solution(input));
        }
    }
    public static String solution(int input) {
        StringBuilder sb = new StringBuilder();
        while(input > 0){
            sb.insert(0, input % 3);
            input = (input - 1) / 3;
        }
        return sb.toString().replaceAll("0", "4");
    }
}