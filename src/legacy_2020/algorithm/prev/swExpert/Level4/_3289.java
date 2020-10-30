package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3289 {
    static int n, m, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] union;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            union = new int[n + 1];
            Arrays.fill(union , -1);

            int mode, a, b;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                mode = Integer.parseInt(st.nextToken());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (mode == 0){
                    merge(a, b, union);
                } else {
                    if (find(a, union) == find(b, union))
                        sb.append(1);
                    else
                        sb.append(0);
                }
            }

            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void merge(int a, int b, int[] union){
        a = find(a, union);
        b = find(b, union);
        if (a == b) return;
        if (-union[a] > -union[b]){
            union[a] += union[b];
            union[b] = a;
        } else {
            union[b] += union[a];
            union[a] = b;
        }
    }

    public static int find(int n, int[] union){
        if (union[n] < 0) return n;
        return union[n] = find(union[n], union);
    }
}
