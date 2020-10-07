package algorithm.prev.programmers.Level3;

import java.util.*;

public class _42861 {
    static private int [] union;

    public static void main(String[] args) {
        System.out.println(solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
        System.out.println(solution2(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
    }

    public static int solution(int n, int[][] costs) {
        boolean[] visited = new boolean[n];
        ArrayList<Node>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] arr : costs) {
            list[arr[0]].add(new Node(arr[0], arr[1], arr[2]));
            list[arr[1]].add(new Node(arr[1], arr[0], arr[2]));
        }

        return prim(list, visited);
    }

    private static int prim(ArrayList<Node>[] list, boolean[] visited){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        List<Node> tempList;
        Node tempNode;
        int result = 0;
        while (!q.isEmpty()){
            int curr = q.poll();
            visited[curr] = true;
            tempList = list[curr];

            for (Node node : tempList){
                if (!visited[node.end]){
                    pq.add(node);
                }
            }

            while (!pq.isEmpty()){
                tempNode = pq.poll();
                if (!visited[tempNode.end]){
                    visited[tempNode.end] = true;
                    result += tempNode.weight;
                    q.add(tempNode.end);
                    break;
                }
            }
        }
        return result;
    }

    private static int solution2(int n, int[][] costs) {
        union = new int[n];

        Node [] nodes = new Node[costs.length];
        for (int idx = 0; idx < costs.length; idx++) {
            nodes[idx] = new Node(costs[idx][0], costs[idx][1], costs[idx][2]);
        }

        Arrays.sort(nodes);
        Arrays.fill(union, -1);

        return kruskal(nodes, n);
    }

    private static int kruskal(Node[] nodes, int n){
        int answer = 0, cnt = 0;

        for (Node node : nodes){
            if (merge(node.start, node.end)){
                answer += node.weight;
                if (++cnt == n - 1) break;
            }
        }

        return answer;
    }

    private static int find(int u){
        if (union[u] < 0) return u;
        return union[u] = find(union[u]);
    }

    private static boolean merge(int u, int v){
        u = find(u);
        v = find(v);
        if (u == v) return false;
        union[u] += union[v];
        union[v] = u;
        return true;
    }

    static class Node implements Comparable<Node>{
        int start, end, weight;

        Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Node n){
            if (this.weight == n.weight) return Integer.compare(this.start, n.start);
            return Integer.compare(this.weight, n.weight);
        }
    }
}
