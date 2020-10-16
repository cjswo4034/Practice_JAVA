package algorithm.codingTests.ucpc_2020_예선;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* [Gold4] 위상정렬 (BFS로도 풀 수 있음)
 * 주변에 있는 사람 중 루머를 믿는 사람이 절반 이상이면 루머를 믿는다.
 * 최초 유포자는 주변사람과 관계없이 주변에 루머를 퍼뜨린다.
 * 1. 루머를 믿는 사람(i)은 주변 사람(j)의 '주변 사람 중 루머를 믿지 않는 사람의 수(indegree[j])'를 1 감소한다.
 * 2. 주변 사람(j)이 루머를 믿지 않는 상태(ans[j] == -1) 이면서 indegree가 주변 사람 수의 절반 이하일 때 다음 루머 유포자가 된다.
 * 3. 주변 사람(j)이 다음 루머 유포자가 되면 ans[j]에 루머를 유포한 사람(i)이 루머를 믿은 시간 + 1을 할당한다.
 * */
public class 루머_19538 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        List<List<Integer>> adj = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(st.nextToken()), m;
        int[] ans = new int[n];
        int[] indegree = new int[n];

        Arrays.fill(ans, -1);
        for (int i = 0, next; i < n; i++) {
            List<Integer> list = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            while ((next = Integer.parseInt(st.nextToken())) != 0) {
                list.add(next - 1);
                indegree[i]++;
            }

            adj.add(list);
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0, init; i < m; i++) {
            init = Integer.parseInt(st.nextToken()) - 1;
            q.add(init);
            ans[init] = 0;
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj.get(cur)) {
                // 주변 사람의 수가 홀수나 짝수인 것은 무관
                if (ans[next] == -1 && --indegree[next] == adj.get(next).size() / 2) {
                    q.add(next);
                    ans[next] = ans[cur] + 1;
                }
            }
        }

        for (int e: ans) sb.append(e).append(" ");
        System.out.print(sb);
    }
}
