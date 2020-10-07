package algorithm.prev.programmers.kakaoRecruit_2019;

import java.util.Arrays;

// pass
public class _3 {
    public static void main(String[] args) {
        _3 prob = new _3();
        int[][] key = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};
        int[][] lock = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}};
        System.out.println(prob.solution(key, lock));
    }

    // Key와 Lock 각각의 돌기, 홈 부분의 최소 영역을 잘라낸다.
    // 1-Key의 영역보다 1-Lock의 영역이 더 크면 실패
    // 1-Lock을 윈도우 슬라이딩으로 모든 회전에 대해 (0, 90, 180, 270) 탐색한다.
    // 일치(xor 연산 시 모두 1인 경우)하는게 있으면 true

    public boolean solution(int[][] inputKey, int[][] inputLock) {
        if (!hasNumber(inputLock, 0)) return true;
        if (!hasNumber(inputKey, 1)) return false;
        int[][] key = sliceArea(inputKey, 1);
        int[][] lock = sliceArea(inputLock, 0);

        if (!checkSize(key, lock)){
            return false;
        } else {
            // display(key);
            for (int i = 0; i < 4; i++) {
                lock = rotate(lock);
                // display(lock);
                for (int j = 0; j <= key.length - lock.length; j++) {
                    for (int k = 0; k <= key[0].length - lock[0].length; k++) {
                        if (isCorrect(lock, key, j, k)) return true;
                    }
                }
            }
            return false;
        }
    }

    private void display(int[][] arr){
        System.out.println("==============================");
        for (int[] row : arr) System.out.println(Arrays.toString(row));
    }

    private boolean hasNumber(int[][] arr, int key){
        for (int[] row : arr)
            for (int el : row)
                if (el == key) return true;
        return false;
    }

    private boolean checkSize(int[][] key, int[][] lock){
        return lock.length <= key.length && lock[0].length <= key[0].length;
    }

    private int[][] sliceArea(int[][] area, int criteria){
        int minX = area.length, minY = area[0].length;
        int maxX = 0, maxY = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[0].length; j++) {
                if (area[i][j] == criteria){
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        int[][] answer = new int[maxX - minX + 1][maxY - minY + 1];
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                answer[i - minX][j - minY] = area[i][j];
            }
        }

        return answer;
    }

    private int[][] rotate(int[][] arr){
        int[][] answer = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                answer[j][i] = arr[arr.length - 1 - i][j];
            }
        }
        return answer;
    }

    private boolean isCorrect(int[][] lock, int[][] key, int x, int y){
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[0].length; j++) {
                if ((lock[i][j] ^ key[x + i][y + j]) != 1) return false;
            }
        }
        return true;
    }
}
