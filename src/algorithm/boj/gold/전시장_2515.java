package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 전시장_2515 {
    static int n, s;
    static int[] dp;
    static Artwork[] artworks;

    // 최소 1456ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][2];
        int[][] input = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input, Comparator.comparingInt(a -> a[0]));

        // p는 항상 a[i].height - a[j].hegith >= s 인 j이다.
        for (int i = 1, p = 0; i <= n; i++) {
            while (p <= n && input[i][0] - input[p][0] >= s) p++;
            if (p > 0 && p <= n) p--;

            // 미술품 i를 살 때 = 현재 미술품 가격 + 최대값(높이 차가 s이상인 미술품 j를 살때, 안살때)
            dp[i][1] = input[i][1] + Math.max(dp[p][0], dp[p][1]);

            // 미술품 i를 안살 때 = 최대값(이전 미술품을 살 때, 안살 때)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }

        System.out.println(Math.max(dp[n][0], dp[n][1]));
    }

    // 최소 1768ms
    public static void solution2(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        dp = new int[n + 1];
        artworks = new Artwork[n + 1];
        artworks[0] = new Artwork(-s, 0);

        int max = 0;
        for (int i = 1, h, c; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            artworks[i] = new Artwork(h, c);
            if (max < c) max = c;
        }

        Arrays.sort(artworks, Comparator.comparingInt(a -> a.height));

        // 최소 높이 + s > 최대 높이 -> max(c);
        // 최소 높이 + s == 최대 높이 -> max(c, 최소 높이 c + 최대 높이 c)
        if (artworks[1].height + s >= artworks[n].height) {
            if (artworks[1].height + s == artworks[n].height)
                System.out.println(Math.max(max, artworks[1].cost + artworks[n].cost));
            else System.out.println(max);
            return;
        }

        artworks[0].next = 1;
        for (int i = 1; i <= n; i++) {
            int nH = artworks[i].height + s;
            int nJ = artworks[i - 1].next;

            if (nJ > n) {
                artworks[i].next = n + 1;
                continue;
            }

            for (int j = nJ; j <= n; j++) {
                if (artworks[j].height >= nH) {
                    artworks[i].next = j;
                    break;
                }
            }

            if (artworks[i].next == -1) artworks[i].next = n + 1;
        }

        Arrays.fill(dp, -1);
        System.out.println(function(0));
    }

    static int function(int cur) {
        if (dp[cur] != -1) return dp[cur];
        if (artworks[cur].next > n) return dp[cur] = artworks[cur].cost;

        int next = artworks[cur].next;
        int curCost = artworks[cur].cost;
        int firstHeight = artworks[next].height;

        for (; next <= n; next++) {
            if (artworks[next].height - firstHeight >= s) break;
            dp[cur] = Math.max(dp[cur], function(next) + curCost);
        }

        return dp[cur];
    }

    static class Artwork {
        int height, cost, next;
        // 미술품 높이와의 차이가 s 이상인 바로 다음 미술품 Index

        public Artwork(int height, int cost) {
            this.height = height;
            this.cost = cost;
            this.next = -1;
        }

        @Override
        public String toString() {
            return "Artwork{" +
                    "height=" + height +
                    ", cost=" + cost +
                    ", next=" + next +
                    '}';
        }
    }
}