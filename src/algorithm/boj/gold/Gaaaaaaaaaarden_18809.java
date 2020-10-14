package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* [Gold 1] 구현, 시뮬레이션
 * 1. 배양액을 뿌릴 수 있는 지역을 list에 저장한다.
 * 2. 1의 지역에 g와 r, 0을 번갈아 대입하면서 배양액 g, r이 전부 0이면 개화할 수 있는 꽃의 개수를 계산한다.
 *
 * 꽃이 피는 경우
 * 1. map의 i행 j열을 방문했을 때의 시간을 visit[i][j]에 할당한다.
 * 2. 배양액을 뿌릴 때 같은 시간(==visit[i][j])에 배양액을 뿌렸고, 배양액의 종류가 다르면 꽃이 핀다.
 * */
public class Gaaaaaaaaaarden_18809 {
    static final int[][] DIR = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    static int n, m, g, r, cnt;
    static int[][] map;
    static List<Node> ground;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        ground = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    cnt++;
                    ground.add(new Node(i, j, 2));
                }
            }
        }
        System.out.println(solution(0, g, r));
    }

    /**
     * 1. 배양액을 뿌릴 수 있는 지역을 순회하면서 g, r, 0을 번갈아 대입한다.
     * 2. 배양액 g와 r을 전부 뿌렸으면 꽃이 피는 경우를 계산한다.
     *
     * @param i 배양액을 뿌릴 수 지역.
     * @param g 배양액 g의 남은 갯수.
     * @param r 배양액 r의 남은 갯수.
     * @return 배양액을 뿌렸을 때 개화한 꽃의 개수.
     * */
    static int solution(int i, int g, int r) {
        if (g + r == 0) return bfs();
        if (i == cnt) return 0;

        int res = 0, tmp;

        if (g > 0) {
            ground.get(i).color = 8;
            res = solution(i + 1, g - 1, r);
            ground.get(i).color = 2;
        }

        if (r > 0) {
            ground.get(i).color = 9;
            tmp = solution(i + 1, g, r - 1);
            if (tmp > res) res = tmp;
            ground.get(i).color = 2;
        }

        tmp = solution(i + 1, g, r);
        if (tmp > res) res = tmp;
        return res;
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();;
        int[][] visitTime = new int[n][m];

        for (Node node: ground) {
            if (node.color != 0){
                q.add(node);
                visitTime[node.r][node.c] = 1;
            }
            map[node.r][node.c] = node.color;
        }

        int res = 0, time = 2;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node cur = q.poll();

                // 방문한 지역이 개화했다면 배양액은 다른 지역으로 못간다.
                if (visitTime[cur.r][cur.c] == -1) continue;

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + DIR[i][0];
                    int nc = cur.c + DIR[i][1];

                    if (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 0) continue;
                    if (visitTime[nr][nc] != 0) {
                        // * 개화조건
                        // 1) 방문한 시간이 같으면서
                        // 2) 뿌린 배양액의 종류가 다를 때
                        if (visitTime[nr][nc] == time && map[nr][nc] != cur.color) {
                            visitTime[nr][nc] = -1;
                            res++;
                        }
                        continue;
                    }

                    map[nr][nc] = cur.color;
                    visitTime[nr][nc] = time;
                    q.add(new Node(nr, nc, cur.color));
                }
            }
            time++;
        }

        return res;
    }

    static class Node {
        int r, c, color;

        public Node(int r, int c, int color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
}
