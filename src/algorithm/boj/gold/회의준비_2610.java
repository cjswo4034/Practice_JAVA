package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* * Gold2
* 1. 위원회를 분리한다. (BFS) -> Union-Find가 더 빠름
* 2. 각 위원회에 대해서 Dist 를 계산한다. (Floyd)
* 3. 위원회에서 최대값이 가장 작은 위원을 대표로 선출한다.
* */
public class 회의준비_2610 {
    static final int INF = 987654321;
    static int n, m;
    static int[] affiliatedCommittee;
    static int[][] cost;
    static List<Committee> committees;
    static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        cost = new int[n + 1][n + 1];
        adj = new ArrayList[n + 1];
        affiliatedCommittee = new int[n + 1];
        committees = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0, from, to; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            adj[to].add(from);
            cost[from][to] = cost[to][from] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (cost[i][j] == 0 && i != j) cost[i][j] = INF;
            }
        }
        
        Committee committee;
        for (int i = 1; i <= n; i++) {
            if (affiliatedCommittee[i] > 0) continue;
            committee = new Committee();
            committees.add(committee);
            separatedCommittee(committee, i);
            committee.floyd();
        }

        int[] ans = new int[committees.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = committees.get(i).getRepresentative();
        Arrays.sort(ans);

        sb.append(committees.size()).append("\n");
        for (int e: ans) sb.append(e).append("\n");
        System.out.println(sb);
    }

    static void separatedCommittee(Committee committee, int v) {
        Queue<Integer> q = new LinkedList<>();
        affiliatedCommittee[v] = committees.size();
        committee.members.add(v);
        q.add(v);

        while (!q.isEmpty()) {
            int mem = q.poll();

            for (int next: adj[mem]) {
                if (affiliatedCommittee[next] == 0) {
                    affiliatedCommittee[next] = committees.size();
                    committee.members.add(next);
                    q.add(next);
                }
            }
        }
    }

    static class Committee {
        List<Integer> members = new ArrayList<>();

        void floyd() {
            for (int k: members) {
                for (int i: members) {
                    for (int j: members) {
                        if (cost[i][k] + cost[k][j] < cost[i][j]) {
                            cost[i][j] = cost[i][k] + cost[k][j];
                        }
                    }
                }
            }
        }

        public int getRepresentative() {
            int res = 0;
            int lowestMax = INF;
            for (int member: members) {
                int max = cost[member][0];

                for (int next: members)
                    if (max < cost[member][next]) max = cost[member][next];

                if (max < lowestMax) {
                    res = member;
                    lowestMax = max;
                }
            }
            return res;
        }

        public void display(int[][] arr) {
            for (int[] row: arr)
                System.out.println(Arrays.toString(row));
        }

        @Override
        public String toString() {
            return "Committee{" +
                    "members=" + members +
                    ", representative=" + getRepresentative() +
                    '}';
        }
    }
}
