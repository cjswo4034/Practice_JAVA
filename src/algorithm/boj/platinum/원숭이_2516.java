package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 원숭이_2516 {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        boolean[][] cage = new boolean[2][n + 1];
        List<Integer>[] adj = new ArrayList[n + 1];

        List<Integer> list;
        for (int i = 1, cnt; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            while (cnt-- > 0) list.add(Integer.parseInt(st.nextToken()));
            adj[i] = list;
            cage[0][i] = true;
        }

        // 모두 1번 우리에 가둔다
        // 1번 우리의 1번 원숭이부터 앙숙의 수가 2개 이상이면 2번 우리로 옮긴다.
        // 2번 우리도 동일하게
        // 옮겨진 원숭이가 있으면 다른 우리에 반복

        int flag = 1;
        while (cageMonkey(cage, adj, flag ^= 1));

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (cage[0][i]) {
                cnt++;
                sb1.append(i).append(" ");
            } else sb2.append(i).append(" ");
        }

        if (cnt == n) {
            cnt--;
            sb1.delete(0, 2);
            sb2.append(1);
        }

        sb1.insert(0, cnt + " ");
        sb2.insert(0, (n - cnt) + " ");

        System.out.println(sb1);
        System.out.println(sb2);
    }

    // 옮겨진 원숭이가 한개라도 있으면 True
    static boolean cageMonkey(boolean[][] cage, List<Integer>[] adj, int idx) {
        boolean res = false;
        for (int i = 1; i <= n; i++) {
            if (cage[idx][i] && getConnectionCount(cage[idx], adj[i]) > 1) {
                cage[idx][i] = false;
                cage[idx ^ 1][i] = true;
                res = true;
            }
        }
        return res;
    }

    static int getConnectionCount(boolean[] cage, List<Integer> connections) {
        int res = 0;
        for (int v: connections) if (cage[v]) res++;
        return res;
    }
}