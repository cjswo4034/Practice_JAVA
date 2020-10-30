package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.*;

public class _4111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        boolean[] arr = new boolean[2000001];          // 카메라 위치가 표시된 배열
        PriorityQueue<Node> pq = new PriorityQueue<>(Collections.reverseOrder());   // 빈 구간을 담을 리스트

        int n, k, result;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            n = Integer.parseInt(br.readLine()); // 카메라의 수
            k = Integer.parseInt(br.readLine()); // 수신기의 수
            if (n < k) {
                br.readLine();
                result = 0;
            } else {
                Arrays.fill(arr, false);
                pq.clear();

                int min = Integer.MAX_VALUE, max = 0, tmp;
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < n; i++) {
                    tmp = Integer.parseInt(st.nextToken()) + 1000000;
                    min = Math.min(min, tmp);
                    max = Math.max(max, tmp);
                    arr[tmp] = true;
                }

                for (int i = min, j; i <= max; i++) {
                    if (!arr[i]) {   // 카메라가 없는 구간
                        j = i;
                        while (j <= max && !arr[j]) j++;
                        pq.add(new Node(i, --j));
                        i = ++j;
                    }
                }

                result = max - min;
                for (int i = 0; i < k - 1; i++) {
                    if (!pq.isEmpty()) result -= pq.poll().size;
                }
            }
            // 배열에 카메라의 위치를 표시, min = ?, max = ?
            // 배열을 탐색해서 카메라가 없는 구간을 리스트에 추가
            // 리스트를 구간 크기 기준 내림차순 정렬
            // k - 1 만큼 구간을 제거하고 전체 크기에서 뺌

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static class Node implements Comparable<Node>{
        int from, to, size;

        Node(int from, int to) {
            this.from = from;
            this.to = to;
            this.size = to - from + 2;
        }

        @Override
        public int compareTo(Node o) {
            if (this.size == o.size) return Integer.compare(this.from, o.from);
            return Integer.compare(this.size, o.size);
        }
    }
}