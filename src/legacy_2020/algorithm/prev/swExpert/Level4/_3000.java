package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _3000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> min, max;

        int a, n, result;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            min = new PriorityQueue<>();
            max = new PriorityQueue<>(Collections.reverseOrder());

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 문자 갯수
            a = Integer.parseInt(st.nextToken()); // 공책에 쓴 자연수
            max.add(a);

            result = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 2; j++) {
                    int curr = Integer.parseInt(st.nextToken());
                    if (curr < max.peek()) {
                        max.add(curr);
                    } else min.add(curr);

                    if (!min.isEmpty() && min.size() > max.size()){
                        max.add(min.poll());
                    } else if (!max.isEmpty() && max.size() > min.size() + 1){
                        min.add(max.poll());
                    }
                }

                System.out.print(Arrays.toString(max.toArray()) + " : " + max.peek() + " : ");
                System.out.println(Arrays.toString(min.toArray()));


                result = (result + max.peek()) % 20171109;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}