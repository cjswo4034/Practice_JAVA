package algorithm.boj.gold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// gold 2
public class 제곱ㄴㄴ수_1016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        boolean[] arr = getArr(max, min);

        int res = 0;
        for (boolean b : arr) if (!b) res++;
        System.out.println(res);
    }

    private static boolean[] getArr(long max, long min) {
        boolean[] arr = new boolean[(int)(max - min) + 1];
        for (long i = 2; i * i <= max; i++) {
            long tmp = i * i;
            for (long j = min / (tmp); j * tmp <= max; j ++) {
                if (j * tmp >= min) arr[(int)(j * tmp - min)] = true;
            }
        }
        return arr;
    }
}
