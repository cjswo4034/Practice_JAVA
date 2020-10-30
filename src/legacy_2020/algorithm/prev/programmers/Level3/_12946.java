package algorithm.prev.programmers.Level3;

import java.util.Arrays;

public class _12946 {
    static int cnt;
    public static void main(String[] args) {
        int[][] arr = solution(2);
        for(int[] result : arr){
            System.out.println(Arrays.toString(result));
        }
    }

    // 1. n-1개의 원판을 임시봉으로 옮긴다.
    // 2. 맨 아래 원판을 목적지 봉으로 옮긴다.
    // 3. 임시봉의 원판을 목적지 봉으로 옮긴다.
    public static int[][] solution(int n){
        int[][] arr = new int[(int)Math.pow(2, n) - 1][2];
        solve(arr, n, 1, 2, 3);
        return arr;
    }

    public static void solve(int[][] arr, int n, Integer src, Integer inter, Integer dest){
        if (n == 0) return;
        solve(arr, n - 1, src, dest, inter);
        arr[cnt++] = new int[]{src, dest};
        solve(arr, n - 1, inter, src, dest);
    }
}
