package algorithm.prev.programmers.Level3;

public class 보행자_천국 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(solution.solution(3, 6, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));
    }

    static class Solution {
        int MOD = 20170805;

        public int solution(int m, int n, int[][] cityMap) {
            int[][] arr = new int[m][n];
            arr[0][0] = 1;

            int pI, pJ;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (cityMap[i][j] != 0) continue;
                    pI = getPreviosIdx(cityMap, i - 1, j, true);
                    pJ = getPreviosIdx(cityMap, i, j - 1, false);
                    if (pI >= 0) arr[i][j] = (arr[i][j] + arr[pI][j]) % MOD;
                    if (pJ >= 0) arr[i][j] = (arr[i][j] + arr[i][pJ]) % MOD;
                }
            }
            return arr[m - 1][n - 1];
        }

        private int getPreviosIdx(int[][] citiMap, int idx1, int idx2, boolean isVertical){
            while (idx1 >= 0 && idx2 >= 0){
                if (citiMap[idx1][idx2] == 0) return isVertical ? idx1 : idx2;
                else if (citiMap[idx1][idx2] == 1) return -1;
                if (isVertical) idx1--;
                else idx2--;
            }
            return -1;  // idx1 이 0 미만이거나 0인 구간이 없을 때
        }
    }
}
