package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _7854 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        init(list); Collections.sort(list);

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int num = Integer.parseInt(br.readLine()), result = 0;
            for (Integer number : list) {
                if (num >= number) result++;
                else break;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void init(List<Integer> list){
        int tmp = 10; list.add(1);
        while (tmp <= 1000000000){
            for (int i = 1; i < 10; i++) {
                if (tmp % i == 0) list.add(tmp / i);
            }
            tmp *= 10;
        }
    }
}
