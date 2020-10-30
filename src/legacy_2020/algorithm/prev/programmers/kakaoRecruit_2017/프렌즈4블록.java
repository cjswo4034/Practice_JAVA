package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.Arrays;

public class 프렌즈4블록 {
    public static void main(String[] args) {
        프렌즈4블록 prob = new 프렌즈4블록();

        String[] map = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(prob.solution(4, 5, map));
        map = new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(prob.solution(6, 6, map));
    }

    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) map[i] = board[i].toCharArray();

        int answer = 0, cnt;
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 1; i < m; i++) {   // 블록 탐색
                for (int j = 1; j < n; j++) {
                    cnt = check(map, i, j); // 네 개의 블록이 같을 때 결과에 더할 블록 수. 같지 않다면 -1
                    if (cnt != -1) {
                        answer += cnt;
                        flag = false;
                    }
                }
            }
            arrangeMap(map, m, n);
        }

        return answer;
    }

    private void display(char[][] map){
        System.out.println("=====================");
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    private int check(char[][] map, int i, int j) {
        if (!enableCheck(map, i, j)) return -1;

        int cnt = 0, curr = map[i][j];
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
                if (map[i - k][j - l] == curr){
                    map[i - k][j - l] = (char)(curr + 32);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private boolean enableCheck(char[][] map, int i, int j){
        if (map[i][j] == '-') return false;
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
                if (!(map[i - k][j - l] == map[i][j] || map[i -k][j - l] == map[i][j] + 32)) return false;
            }
        }
        return true;
    }

    private void arrangeMap(char[][] map, int m, int n){
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j > 0; j--) {
                if (map[j][i] > 'Z'){
                    int k = j - 1;
                    while (k > 0 && map[k][i] > 'Z') k--;
                    swap(map, i, j, k);
                }
            }

            for (int j = 0; j < m; j++) {
                if (map[j][i] > 'Z')
                    map[j][i] = '-';
            }
        }
    }

    private void swap(char[][] map, int i, int j, int k){
        char tmp = map[j][i];
        map[j][i] = map[k][i];
        map[k][i] = tmp;
    }
}