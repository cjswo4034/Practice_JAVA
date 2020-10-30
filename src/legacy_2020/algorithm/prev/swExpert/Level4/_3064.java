package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3064 {
    static int n, m, height;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            height = getHeight(n);

            arr = new long[(int)Math.pow(2, height)];

            st = new StringTokenizer(br.readLine());
            int initIdx = (int)Math.pow(2, height - 1);
            for (int i = 0; i < n; i++) {
                arr[initIdx + i] = Integer.parseInt(st.nextToken());
            }

            for (int i = initIdx, leng = initIdx + n; i < leng; i++) {
                int P = i / 2;
                while(P > 0){
                    arr[P] += arr[i];
                    P /= 2;
                }
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                if (Integer.parseInt(st.nextToken()) == 1){
                    int index = Integer.parseInt(st.nextToken()) - 1;
                    int num = Integer.parseInt(st.nextToken());
                    add(index + initIdx, num, arr);
                } else {
                    int from = Integer.parseInt(st.nextToken()) + initIdx - 1;
                    int to = Integer.parseInt(st.nextToken()) + initIdx - 1;
                    sb.append(getSum(1, initIdx, initIdx * 2 - 1, from, to, arr) + " ");
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static long getSum(int node, int start, int end, int from, int to, long[] arr){
        if (end < from || to < start) return 0 ;
        if (from <= start && end <= to) return arr[node];
        return getSum(node * 2, start,(start + end) / 2, from, to, arr) +
                getSum(node * 2 + 1, (start + end) / 2 + 1, end, from, to, arr);
    }

    public static void add(int index, int num, long[] arr){
        while(index > 0){
            arr[index] += num;
            index /= 2;
        }
    }

    public static int getHeight(int n){
        int i = 1;
        while(Math.pow(2, i - 1) < n) i++;
        return i;
    }
}