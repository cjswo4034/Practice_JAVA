package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;

public class _7792 {
    static int n, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int [] count;
        boolean [][] arr;
        Pair [] p;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new boolean[n][];
            count = new int[n];
            p = new Pair[n];

            String tmp;
            for (int i = 0; i < n; i++) {
                arr[i] = new boolean[26];
                tmp = br.readLine();
                for (int j = 0; j < tmp.length(); j++) {
                    if (Character.isAlphabetic(tmp.charAt(j))){
                        if (arr[i][tmp.charAt(j) - 'A']) continue;
                        arr[i][tmp.charAt(j) - 'A'] = true;
                        count[i]++;
                    }
                }
                p[i] = new Pair(count[i], tmp);
            }
            Arrays.sort(p);

            sb.append("#").append(t).append(" ").append(p[0].str).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static class Pair implements Comparable<Pair>{
        int count;
        String str;

        private Pair(int count, String str) {
            this.count = count;
            this.str = str;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count == o.count) return this.str.compareTo(o.str);
            return this.count > o.count ? -1 : 1;
        }
    }
}
