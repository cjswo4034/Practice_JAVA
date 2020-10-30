package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _8189 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int max = 0;
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                max = Math.max(arr[i], max);
            }

            // 1. 해당 시간에 존재하면 큐에 삽입한다.
            // 2. 큐의 크기 >= A 이면 (큐의 크기 + 1) / 2 개의 원소를 제거하고 제거된 원소들의 갯수만큼 현재시간을 결과에 추가한다.
            // 3. 큐의 헤드 + B <= 현재 시간이면 헤드를 제거하고 현재시간을 결과에 추가한다.

            // System.out.println("start : " + Arrays.toString(arr));

            Queue<Integer> q = new LinkedList<>();
            for (int curr = 1, idx = 0; curr <= max + b; curr++) {
                if (idx < arr.length && curr == arr[idx]) q.add(arr[idx++]);

                if (!q.isEmpty() && (q.peek() + b <= curr || q.size() >= a)){
                    int loofCnt = (q.size() + 1) / 2;
                    while (loofCnt-- > 0){
                        q.poll();
                        sb.append(curr).append(" ");
                    }
                }

                // System.out.println(curr + " : " + Arrays.toString(q.toArray()));
            }

            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}