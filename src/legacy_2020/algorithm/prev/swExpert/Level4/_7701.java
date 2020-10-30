package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _7701 {
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] arr;
        Set<String> set;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append("\n");
            set = new HashSet<>();

            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++)
                set.add(br.readLine());

            arr = new String[set.size()];
            arr = set.toArray(arr);
            Arrays.sort(arr, (o1, o2) -> {
                if (o1.length() == o2.length()) return o1.compareTo(o2);
                return o1.length() > o2.length() ? 1 : -1;
            });

            for (String chr : arr)
                sb.append(chr).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
