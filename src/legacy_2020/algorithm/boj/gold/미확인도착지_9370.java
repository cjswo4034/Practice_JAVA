package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
* 출발지에서 주어진 간선을 지나 목적지 후보로 가는 경로가 출발지에서 목적지 후보로 가는 경로보다 빠르거나 같은 후보를 구한다.
* (특정 경로를 포함했을 때 최단 거리가 되는 후보들을 구한다.)
*    - 다익스트라... 특정 경로를 반드시 포함해야 된다. 경로의 끝 지점은 g, h로 표기한다.
* 1. 경로 g <-> h의 가중치는 홀수로 나머지 경로들의 가충지는 2를 곱하여 짝수로 할당한다.
* 2. s에서 나머지 정점들로 가는 가중치를 다익스트라를 이용하여 구한다.
* 3. 목적지 후보들 중 가중치가 홀수인 목적지를 출력한다.
* * 경로 g <-> h만 홀수이므로 경로 g,h를 지나면서 다른 정점으로 가는 경로와 가중치가 같은 다른 경로는 존재할 수 없다.
*/

public class 미확인도착지_9370 {
    private static final int INF = 200000000;

    // n: 정점, m: 간선, t: 목적지 후보, s: 출발지, [g, h]: 듀오가 지나간 간선
    static int n, m, t, s, g, h;
    static List<Integer> destinations;
    static List<Edge>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            g = Integer.parseInt(st.nextToken()) - 1;
            h = Integer.parseInt(st.nextToken()) - 1;

            adj = new ArrayList[n];
            destinations = new ArrayList<>();

            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for (int i = 0, a, b, c; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken()) - 1;
                b = Integer.parseInt(st.nextToken()) - 1;
                c = Integer.parseInt(st.nextToken()) * 2;

                if ((a == g && b == h) || (a == h && b == g)){
                    adj[a].add(new Edge(b, c - 1));
                    adj[b].add(new Edge(a, c - 1));
                } else {
                    adj[a].add(new Edge(b, c));
                    adj[b].add(new Edge(a, c));
                }
            }

            int[] dist = dijkstra();

            for (int i = 0; i < t; i++)
                destinations.add(Integer.parseInt(br.readLine()) - 1);
            destinations.sort(Integer::compareTo);

            for (int dest: destinations) {
                if (dist[dest] % 2 == 1)
                    sb.append(dest + 1).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[n];

        Arrays.fill(dist, INF);
        dist[s] = 0;
        pq.offer(new Edge(s, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (dist[e.v] < e.w) continue;

            for (Edge next: adj[e.v]) {
                int nv = next.v;
                int nw = next.w + e.w;

                if (dist[nv] > nw) {
                    dist[nv] = nw;
                    pq.offer(new Edge(nv, nw));
                }
            }
        }
        return dist;
    }

    static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            if (w != o.w) return Integer.compare(w, o.w);
            return Integer.compare(v, o.v);
        }
    }
}
