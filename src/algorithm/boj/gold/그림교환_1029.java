package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/* [Gold 1] 비트마스크, 그래프 탐색
 * 그림의 구매가보다 많거나 같은 금액으로 그림을 팔 수 있을 때, 그림을 소유할 수 있는 사람의 최대값을 구하기
 * - 0번 아티스트가 외부 상인에게 0원으로 그림을 구매함.
 * - map[i][j] -> j가 i에게 그림을 살 때 가격(=i가 j에게 그림을 팔 때 가격)
 * - visit[i][cost][visit] -> i번 아티스트가 그림을 cost원으로 구매했을 때 이전에 그림을 소유한 아티스트 리스트 visit.
 * */
public class 그림교환_1029 {
    static int n;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line[j] - '0';
            }
        }

        Art art = new Art(0, 0, 1);
        System.out.println(bfs(art));
    }

    static int bfs(Art art) {
        boolean[][][] visited = new boolean[n][10][1 << n];
        Queue<Art> q = new LinkedList<>();
        int res = 1;

        q.add(art);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Art cur = q.poll();

                if (visited[cur.idx][cur.cost][cur.visit]) continue;
                visited[cur.idx][cur.cost][cur.visit] = true;

                for (int next = 0; next < n; next++) {
                    if ((cur.visit & 1 << next) == 0 && arr[cur.idx][next] >= cur.cost) {
                        q.add(new Art(next, arr[cur.idx][next], cur.visit | (1 << next)));
                    }
                }
            }
            if (!q.isEmpty()) res++;
        }

        return res;
    }

    static class Art {
        int idx, cost, visit;

        public Art(int idx, int cost, int visit) {
            this.idx = idx;
            this.cost = cost;
            this.visit = visit;
        }
    }
}
