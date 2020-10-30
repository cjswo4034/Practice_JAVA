package algorithm.codingTests.monthlyChallenge.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _3 {
    int dist, index;
    boolean[] visit;
    List<Integer>[] adj;

    public int solution(int n, int[][] edges) {
        int mem = 0;
        adj = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0, from, to; i < edges.length; i++) {
            from = edges[i][0];
            to = edges[i][1];

            adj[from].add(to);
            adj[to].add(from);
        }

        int a, b;
        longestIndex(1, 0);

        getLongestDistance(index, dist, -1);  // find a
        a = index;

        mem += getLongestDistance(index, 0, -1); // find b && add distance A(a -> b)
        b = index;

        // a에서 가장 먼 노드
        int aDist = mem;
        aDist += getLongestDistance(a, 0, b);   // a->c1
        getDistance(b, index, 0);                  // b->c1
        aDist += dist;

        // b에서 가장 먼 노드
        int bDist = mem;
        bDist += getLongestDistance(b, 0, a);   // b->c2
        getDistance(a, index, 0);                  // a->c2
        bDist += dist;

        return Math.max(aDist, bDist) / 3;
    }

    int getLongestDistance(int initIdx, int initDist, int visitedIdx) {
        // init
        dist = initDist;
        index = initIdx;
        Arrays.fill(visit, false);
        if (visitedIdx != -1) visit[visitedIdx] = true;

        // find longest index
        longestIndex(index, 0);

        return dist;
    }

    // a -> b 까지의 거리
    void getDistance(int a, int b, int dist) {
        if (a == b) {
            this.dist = dist;
            return;
        }

        if (visit[a]) return;
        visit[a] = true;

        for (int next: adj[a])
            getDistance(next, b, dist + 1);
    }

    // root에서 가장 먼 노드 index
    void longestIndex(int root, int weight) {
        if (visit[root]) return;
        visit[root] = true;

        if (dist < weight) {
            dist = weight;
            index = root;
        }

        for (int next: adj[root])
            longestIndex(next, weight + 1);
    }
}