package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _4530 {
    static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num1 = st.nextToken();
            String num2 = st.nextToken();

            result = solve(num2) - solve(num1);
            if(num1.charAt(0) == '-' && num2.charAt(0) != '-')
                result--;
            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
    }

    public static long solve(String tmp){
        int i = 0;
        long test = 0;
        boolean minus = false;
        if(tmp.charAt(0) == '-'){
            i = 1;
            minus = true;
        }
        for (int leng = tmp.length(); i < leng; i++) {
            int notFour = tmp.charAt(i) - '0';
            notFour = notFour >= 4 ? notFour - 1 : notFour;
            test += (notFour) * (long)Math.pow(9, leng - i - 1);
        }
        if(minus) test = -test;
        return test;
    }
}
