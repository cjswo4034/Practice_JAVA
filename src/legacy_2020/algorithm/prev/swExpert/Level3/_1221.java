package algorithm.prev.swExpert.Level3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _1221 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] keys = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(keys[i], i);
        }

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append("#").append(t).append(" "); st.nextToken();

            int n = Integer.parseInt(st.nextToken());
            int[] array = new int[10];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                array[map.get(st.nextToken())]++;
            }

            for (int i = 0; i < 10; i++) {
                while(array[i] > 0){
                    sb.append(keys[i] + " ");
                    array[i]--;
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
