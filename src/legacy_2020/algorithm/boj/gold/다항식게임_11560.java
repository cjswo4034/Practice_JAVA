package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [gold3] pre computation
public class 다항식게임_11560 {
    static long[][] arr = new long[21][211];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(st.nextToken()), k, n;

        arr[1][0] = arr[1][1] = 1;
        solution(2);
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            sb.append(arr[k][n]).append("\n");
        }
        System.out.println(sb);
    }

    static void solution(int n) {
        if (n == 21) return;

        int len = n * (n + 1) / 2;
        for (int i = 0; i <= n; i++) {
            for (int j = i; j * 2 <= len; j++) {
                arr[n][j] += arr[n - 1][j - i];
                arr[n][len - j] = arr[n][j];
            }
        }

        solution(n + 1);
    }
}
