package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _7465 {
    static int n, m, result;
    static boolean [] check;
    static LinkedList<LinkedList<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            check = new boolean[n + 1];
            list = new LinkedList<>();

            for (int i = 0; i <= n; i++) {
                list.add(new LinkedList<>());
            }

            int a, b;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                list.get(a).add(b);
                list.get(b).add(a);
            }

            result = 0;
            for (int i = 1; i <= n; i++) {
                if (!check[i]){
                    result++;
                    bfs(i);
                }
            }

            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
    }

    static void bfs(int idx){
        Queue<Integer> q = new LinkedList<>();
        check[idx] = true;
        q.add(idx);

        while(!q.isEmpty()){
            int current = q.poll();
            for (int i : list.get(current)) {
                if(!check[i]){
                    check[i] = true;
                    q.add(i);
                }
            }
        }
    }
}