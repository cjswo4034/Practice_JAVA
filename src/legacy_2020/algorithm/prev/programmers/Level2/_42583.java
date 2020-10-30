package algorithm.prev.programmers.Level2;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class _42583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = 1; //Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            System.out.println("#" + i + " : " + solution(3, 12, new int[]{7, 3, 4, 5, 6, 11}));
        }
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) q.add(0);

        int idx = 0, time = 0, sum = 0;
        while(!q.isEmpty()){
            time++;
            sum -= q.poll();
            if (sum + truck_weights[idx] <= weight){
                sum += truck_weights[idx];
                q.add(truck_weights[idx++]);
                if (idx >= truck_weights.length) break;
            } else {
                q.add(0);
            }
        }

        return time + bridge_length;
    }
}