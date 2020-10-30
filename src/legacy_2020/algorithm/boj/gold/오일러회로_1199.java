package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* [Gold 2] (출저: https://m.blog.naver.com/PostView.nhn?blogId=kks227&logNo=220800097205&proxyReferer=https:%2F%2Fwww.google.com%2F)
*  모든 간선을 지나되 한번 지난 간선은 다시 지나지 않고 출발점으로 돌아오는 경로를 구한다. (오일러 회로) 오일러 경로 아님!
*  - Hierholzer's Algorithm
*  - 필요충분조건
*    1. isolated vertex가 존재하지 않는다.
*    2. 모든 정점의 차수가 짝수이다.
* */
public class 오일러회로_1199 {
    static int n;
    static int[] degree;
    static boolean[] visited;
    static List<Edge>[] adj;

    /* [Hierholzer's Algorithm]
     * 1. 아무 정점 v를 뽑고 v에서 출발해 v로 돌아오는 경로를 하나 뽑는다.
     * 2. 위 경로에 속해있는 정점 중 사용되지 않은 간선이 있는 정점 u가 존재하지 않을 때까지
     *    정점 u에 대해서 1을 반복하면서 원래의 경로에 끼워넣는다.
     * */
    public static void main(String[] args) throws Exception {
        solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        degree = new int[n];
        visited = new boolean[n];
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0, v; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                v = Integer.parseInt(st.nextToken());
                if (j > i && v > 0) {
                    Edge e1 = new Edge(j, v), e2 = new Edge(i, v);
                    e1.dual = e2;
                    e2.dual = e1;
                    adj[i].add(e1);
                    adj[j].add(e2);
                    degree[i] += v;
                    degree[j] += v;
                }
            }
        }

        // 필요충분조건 2. 차수가 홀수인 정점이 존재하면 불가능
        for (int i = 0; i < n; i++) {
            if (degree[i] % 2 == 1) {
                System.out.println(-1);
                return;
            }
        }

        int start = -1;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (dfs(i) > 1) {
                if (flag) {     // 필요충분조건 1. 크기가 2 이상인 컴포넌트가 2개 이상이면 불가능하다
                    System.out.println(-1);
                    return;
                } else {
                    start = i;
                    flag = true;
                }
            }
        }

        if (start == -1) start = 0;

        Eulerian(sb, start);
        System.out.println(sb);
    }

    // 컴포넌트 별로 방문하는 dfs
    static int dfs(int cur) {
        int res = 1, next;
        visited[cur] = true;
        for (Edge e: adj[cur]) {
            next = e.to;
            if (!visited[next]) res += dfs(next);
        }
        return res;
    }

    // 오일러 회로 찾기
    static void Eulerian(StringBuilder sb, int cur) {
        for (Edge e: adj[cur]) {
            if (e.cnt > 0) {
                e.cnt--;
                e.dual.cnt--;
                Eulerian(sb, e.to);
            }
        }
        sb.append(cur + 1).append(" ");
    }

    static class Edge {
        int to, cnt;
        Edge dual;

        public Edge() {
            to = -1;
        }

        public Edge(int to, int cnt) {
            this.to = to;
            this.cnt = cnt;
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////

    // 가장 많은 간선을 가진 인덱스에서 아무렇게나 경로를 그림
    // -> 아닌 케이스가 존재함
    static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] pointer = new int[n];
        int[][] dist = new int[n][n];

        Arrays.fill(pointer, -1);
        for (int i = 0; i < n; i++) {
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                if (dist[i][j] != 0 && pointer[i] == -1) pointer[i] = j;
                sum += dist[i][j];
            }

            if (sum % 2 == 1) {
                System.out.println(-1);
                break;
            }
        }

        /*
        *   아래와 같은 Test case에서 틀림
        *   7
        *   0 1 1 1 0 0 1
        *   1 0 1 0 0 0 0
        *   1 1 0 1 0 0 1
        *   1 0 1 0 1 1 0
        *   0 0 0 1 0 0 1
        *   0 0 0 1 0 0 1
        *   1 0 1 0 1 1 0
        *
        *   -> 다음 방문지역의 개수가 0이면 현재 위치에서 다른 지역을 방문할 수 있는지 확인??
        * */
        int cur = 0;
        do {
            System.out.print((cur + 1) + " ");
            cur = getPointer(dist, pointer, cur);
        } while (cur >= 0);
    }

    /**
     * pointer[i]: dist[i]에서 0이 아닌 첫번째 열을 가리킨다. (이하 pointer[i] == j)
     * dist[i][j] 및 dist[j][i]에서 1을 뺀다. (경로를 이동했으므로)
     * pointer[i]를 dist[i][col] > 0인 첫번째 열로 갱신한다.
     * pointer[j]를 dist[j][col] > 0인 첫번째 열로 갱신한다.
     * @return dist[idx][j] > 0인 첫번째 j를 반환한다.
     **/
    static int getPointer(int[][] dist, int[] pointer, int i) {
        if (pointer[i] < 0) return -1;
        int res = pointer[i];
        int p1 = pointer[i], p2 = pointer[res];

        dist[i][p1]--; dist[p1][i]--;
        while (p1 < n && dist[i][p1] == 0) p1++;
        while (p2 < n && dist[res][p2] == 0) p2++;

        pointer[i] = p1 == n ? -1 : p1;
        pointer[res] = p2 == n ? -1 : p2;

        return res;
    }

    // 더 간단한거
    public static void solution2(int map[][], int cur,int N,  BufferedWriter out){
        for(int i=0; i<N; i++){
            while(map[cur][i]>0){
                map[cur][i]--;
                map[i][cur]--;
                solution2(map,i, N,out);
            }
        }

        try{
            out.write((cur+1)+ " ");
        }catch(IOException e){ }
    }
}
