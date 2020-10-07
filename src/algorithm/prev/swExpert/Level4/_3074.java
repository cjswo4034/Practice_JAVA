package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class _3074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, m;
        int[] arr;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 심사대
            m = Integer.parseInt(st.nextToken()); // 사람 수

            // 이분탐색으로 해결하기
            // 1. (최저 시간 + 최악의 시간) / 2 를 이용하여 이분탐색
            // 2. 모든 심사대에 대하여 1.의 결과를 나눈 값의 합과 사람 수 m을 비교한다.
            // 3. 2.에서 비교한 결과 나눈 값의 합이 사람 수보다 크면 다음 최악의 시간은 1.의 결과 - 1
            //                                                  작다면 다음 최저 시간은 1.의 결과 + 1

            arr = new int[n];
            long minTime = 1, maxTime = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
                maxTime = Math.max(maxTime, arr[i]);
            }

            maxTime *= m;
            long cnt, midTime, result = maxTime;
            while (minTime <= maxTime){
                midTime = (minTime + maxTime) >> 1;
                cnt = sumDesk(arr, midTime);
                if (cnt >= m){
                    result = Math.min(result, midTime);
                    maxTime = midTime - 1;
                } else {
                    minTime = midTime + 1;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static long sumDesk(int[] arr, long criteria){
        return IntStream.of(arr).asLongStream()
                .reduce(0, (a, b) -> a + criteria / b);
    }

    // 시간초과
    /*public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int n, m;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                pq.add(new Node(Integer.parseInt(br.readLine())));
            }

            int tmp = 0;
            long maxValue = 0, result = 0;
            for (int i = 0; i < m; i++) {
                Node node = pq.poll();
                tmp = (int)((maxValue - node.tot) / node.time) > 1 ? (int)((maxValue - node.tot) / node.time) : 1;
                node.inclementPeopleCount(tmp);
                if (node.tot > maxValue) maxValue = node.tot;
                pq.add(node);
                i += (tmp - 1);
                System.out.println(node + " : " + tmp + " : " + maxValue);
                if (node.tot > result) result = node.tot;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static class Node implements Comparable<Node>{
        private int time, people;
        private long tot;

        Node(int time){
            this.time = time;
            this.people = 0;
            this.tot = 0;
        }

        public void inclementPeopleCount(){
            this.people++;
            this.tot += time;
        }

        public void inclementPeopleCount(int value){
            this.people += value;
            this.tot += (this.time * value);
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.tot + this.time, o.tot + o.time);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "time=" + time +
                    ", people=" + people +
                    ", tot=" + tot +
                    '}';
        }
    }*/
}