package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 자기_방으로_돌아가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Route[] routes;
        int T = Integer.parseInt(br.readLine().trim());
        for(int t = 1, n; t <= T ; t++){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            routes = new Route[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                routes[i] = new Route(
                        Math.min(from, to),
                        Math.max(from, to));
            }

            Arrays.sort(routes);
            for(Route route : routes)
                System.out.println(route);
            int result = 0;
            for (int i = 0, curr; i < n; i++) {
                if (routes[i].isPassed()) continue;
                routes[i].pass();
                curr = i; result++;
                for (int next = i; next < n; next++) {
                    if (!routes[curr].collision(routes[next])){
                        System.out.println(routes[curr] + "-->" + routes[next]);
                        routes[next].pass();
                        curr = next;
                    }
                }
            }

            sb.append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static class Route implements Comparable<Route>{
        private int from, to;
        private boolean pass;

        Route(int from, int to){
            this.from = from % 2 == 0 ? from / 2 : (from + 1) / 2;
            this.to = to % 2 == 0 ? to / 2 : (to + 1) / 2;
            this.pass = false;
        }

        public void pass(){
            this.pass = true;
        }

        public boolean isPassed(){
            return this.pass;
        }

        public boolean collision(Route r){
            return this.to >= r.from;
        }

        public boolean equals(Route obj) {
            return this.from == obj.from
                    && this.to == obj.to;
        }

        @Override
        public int compareTo(Route o) {
            if (this.from == o.from) return Integer.compare(this.to, o.to);
            return Integer.compare(this.from, o.from);
        }

        @Override
        public String toString() {
            return "Route{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }
}