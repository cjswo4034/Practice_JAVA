package algorithm.prev.programmers.Level3;

import java.util.Arrays;

public class _42898 {
    public static void main(String[] args) {
        _42898 prob = new _42898();
        System.out.println(prob.solution(4, 3, new int[][]{{2, 2}}));
    }

    public int solution(int m, int n, int[][] puddles) {
        int[][] map = getMap(m, n, puddles);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == -1) continue;
                if (i - 1 >= 0) map[i][j] = (map[i][j] + (map[i - 1][j] == -1 ? 0 : map[i - 1][j])) % 1000000007;
                if (j - 1 >= 0) map[i][j] = (map[i][j] + (map[i][j - 1] == -1 ? 0 : map[i][j - 1])) % 1000000007;
            }
        }

        return map[m - 1][n - 1];
    }

    private int[][] getMap(int m, int n, int[][] puddles){
        int[][] map = new int[m][n]; map[0][0] = 1;
        for (int[] puddle : puddles) map[puddle[0] - 1][puddle[1] - 1] = -1;
        return map;
    }

    private void display(int[][] map){
        for(int[] arr : map){
            System.out.println(Arrays.toString(arr));
        }
    }
}