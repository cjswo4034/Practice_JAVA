package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class _8275 {
    static int sum;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int [] arr;
        int n, x, m;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   // 우리의 개수
            x = Integer.parseInt(st.nextToken());   // 햄스터 상한
            m = Integer.parseInt(st.nextToken());   // 기록 수

            int tmpSum = 0;
            Range[] ranges = new Range[m];
            for (int i = 0, l, r, s; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                l = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                tmpSum += s;
                ranges[i] = new Range(l, r, s);
            }

            sum = 0;
            arr = new int[n];
            answer = new int[n];
            dfs(ranges, arr, x, 0);

            if (sum == 0 && tmpSum != 0) sb.append(-1).append(" ");
            else for (int ans : answer) sb.append(ans).append(" ");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void dfs(Range[] ranges, int[] arr, int limit, int depth){
        if (depth >= arr.length) {
            if (check(ranges, arr)){
                int totSum = IntStream.of(arr).sum();
                if (totSum > sum) {
                    sum = totSum;
                    System.arraycopy(arr, 0, answer, 0, arr.length);
                }
            }
            return;
        }

        for (int i = 0; i <= limit; i++) {
            arr[depth] = i;
            dfs(ranges, arr, limit, depth + 1);
        }
    }

    static boolean check(Range[] ranges, int[] arr){
        int sum = -1;
        for (Range range : ranges){
            sum = IntStream.range(range.from - 1, range.to)
                                .reduce(0, (i, j) -> i + arr[j]);
            if (sum != range.sum) return false;
        }
        return true;
    }

    static class Range implements Comparable<Range>{
        int from, to, sum;

        Range(int from, int to, int sum) {
            this.from = from;
            this.to = to;
            this.sum = sum;
        }

        @Override
        public int compareTo(Range o) {
            if (this.from == o.from) return Integer.compare(this.to, o.to);
            else return Integer.compare(this.from, o.from);
        }
    }
}