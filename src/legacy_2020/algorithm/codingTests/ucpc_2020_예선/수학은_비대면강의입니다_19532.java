package algorithm.codingTests.ucpc_2020_예선;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수학은_비대면강의입니다_19532 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        int x = 0, y =0;
        THIS: for (x = -999; x < 1000; x++) {
            for (y = -999; y < 1000; y++) {
                int tmp1 = a * x + b * y - c;
                int tmp2 = d * x + e * y - f;
                if (tmp1 == tmp2 && tmp1 == 0) {
                    break THIS;
                }
            }
        }

        System.out.println(x + " " + y);
    }
}
