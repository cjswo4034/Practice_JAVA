package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.*;

public class _3124_prim {
    static int v, e, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        ArrayList<Node>[] nodeList;
        boolean [] visit;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            visit = new boolean[v];
            nodeList = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                nodeList[i] = new ArrayList<>();
            }

            int start, end, weight;
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                start = Integer.parseInt(st.nextToken()) - 1;
                end = Integer.parseInt(st.nextToken()) - 1;
                weight = Integer.parseInt(st.nextToken());
                nodeList[start].add(new Node(start, end, weight));
                nodeList[end].add(new Node(end, start, weight));
            }

            sb.append("#").append(t).append(" ").append(prim(nodeList, visit)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static long prim(ArrayList<Node>[] nodeList, boolean[] visit){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(0);
        ArrayList<Node> tempList;
        Node tempNode;

        long result = 0;
        while(!dq.isEmpty()){
            int currentNOde = dq.poll();
            visit[currentNOde] = true;
            tempList = nodeList[currentNOde];
            for (int i = 0; i < tempList.size(); i++) {
                if(!visit[tempList.get(i).to]){
                    pq.add(tempList.get(i));
                }
            }

            while(!pq.isEmpty()){
                tempNode = pq.poll();
                if(!visit[tempNode.to]){
                    visit[tempNode.to] = true;
                    result += tempNode.weight;
                    dq.add(tempNode.to);
                    break;
                }
            }
        }
        return result;
    }

    static class Node implements Comparable<Node>{
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node n){
            return this.weight < n.weight ? -1 : 1;
        }
    }
}
