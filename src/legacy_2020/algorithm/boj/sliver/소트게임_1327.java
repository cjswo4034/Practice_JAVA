package algorithm.boj.sliver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 소트게임_1327 {
    private static int n, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(st.nextToken());
        }
        String input = sb.toString();
        String collect = "123456789".substring(0, n);
        System.out.println(bfs(input, collect));
    }

    private static int bfs(String input, String collect){
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.add(input);
        set.add(input);

        int ret = 0;
        String current, next;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                current = q.poll();
                if (collect.equals(current)) return ret;
                for (int i = 0, len = n - k; i <= len; i++) {
                    next = reverseFromI(current, i);
                    if (set.contains(next)) continue;
                    set.add(next);
                    q.add(next);
                }
            }
            ret++;
        }
        return -1;
    }

    private static String reverseFromI(String str, int i) {
        StringBuilder sb = new StringBuilder();
        if (i > 0) sb.append(str, 0, i);
        sb.append(new StringBuilder(str.substring(i, i + k)).reverse());
        sb.append(str.substring(i + k));
        return sb.toString();
    }
}
