package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* [Gold 3] 구현
 * 1. D-트리: 'ㄷ' > 'ㅈ' * 3
 * 2. G-트리: 'ㄷ' < 'ㅈ' * 3
 * 3. DUDUDUNGA-트리: 'ㄷ' == 'ㅈ' * 3
 * */
public class ㄷㄷㄷㅈ_19535 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Edge> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        long d = 0, j = 0;
        long[] degree = new long[N];

        StringTokenizer st;
        for (int i = 1, a, b; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;

            degree[a]++; degree[b]++;
            list.add(new Edge(a, b));
        }

        for (long n: degree) {
            if (n >= 3) j += n * (n - 1) * (n - 2) / 6;
        }

        for (Edge p: list) {
            d += (degree[p.a] - 1) * (degree[p.b] - 1);
        }

        j *= 3;
        if (j < d) System.out.println("D");
        else if (d < j) System.out.println("G");
        else System.out.println("DUDUDUNGA");
    }

    static class Edge {
        int a, b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}