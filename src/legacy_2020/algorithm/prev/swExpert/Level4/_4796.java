package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Scanner;

public class _4796 {
    static int result;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = sc.nextInt();

            int[] array = new int[n + 1];
            array[n] = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();//
            }
            result = 0; //
            solve(array, n);

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void solve(int[] array, int n){
        boolean[] pos = new boolean[n + 1];
        pos[0] = array[0] < array[1];
        for (int i = 1; i < n; i++) {
            pos[i] = array[i - 1] < array[i];
        }

        boolean start = pos[0];
        for (int i = 0; i < n - 1; i++) {
            if(start) {
                if (pos[i] && !pos[i + 1]) {
                    int l, r;
                    int j = i;
                    while (j > 0 && array[j - 1] < array[j]) j--;
                    l = i - j;

                    j = i;
                    while (j < n && array[j] > array[j + 1]) j++;
                    r = j - i;
                    result += (l * r);
                }
            } else {
                start = pos[i];
            }
        }
    }
}