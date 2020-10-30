package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3124_kruscal {
    static int v, e, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] union;
        Edge[] edges;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            union = new int[v];
            edges = new Edge[e];

            int from, to, weight;
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken()) - 1;
                to = Integer.parseInt(st.nextToken()) - 1;
                weight = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(from, to, weight);
            }

            Arrays.sort(edges);
            Arrays.fill(union, -1);

            long result = 0, cnt = 0;
            for (int idx = 0; ; idx++) {
                if (merge(edges[idx].from, edges[idx].to, union)){
                    result += edges[idx].weight;
                    if(++cnt == v - 1) break;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static int find(int n, int[] union){
        if (union[n] < 0) return n;
        return union[n] = find(union[n], union);
    }

    public static boolean merge(int a, int b, int[] union){
        a = find(a, union);
        b = find(b, union);
        if(a == b) return false;
        union[a] = b;
        return true;
    }

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge e){
            if(this.weight == e.weight)
                return this.to < e.to ? -1 : 1;
            return this.weight < e.weight ? -1 : 1;
        }
    }
}
