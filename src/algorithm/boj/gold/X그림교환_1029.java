package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// gold 1
public class X그림교환_1029 {
    private static int result = 1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) arr[i][j] = input[j] - '0';
        }


    }

    private static void dfs(int[][] arr, boolean[] visited, int depth, int curr, int prev, int tot) {
    }
}
