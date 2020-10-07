package algorithm.prev.programmers.kakaoRecruit_2018;

import java.util.Arrays;

public class 블록게임_x {

    public static void main(String[] args) {
        블록게임_x prob = new 블록게임_x();
        /*int[][] map = {
                {0,0,0},
                {1,1,1},
                {1,-1,-1}};

        System.out.println(prob.isSquare(map, 2, 2));
        prob.display(map);*/

        int[][] map = {         // --> 정답코드에선 0이 나옴??
                {0,0,0,0,0,0,0,6,0,0},
                {0,0,0,0,5,0,6,6,6,0},
                {0,0,0,4,5,5,5,0,0,0},
                {0,0,3,4,4,4,8,0,0,0},
                {1,2,3,3,3,8,8,0,0,0},
                {1,2,2,2,0,7,8,0,0,0},
                {1,1,0,0,7,7,7,0,0,0}
        };

        System.out.println(prob.solution(map));
    }

    public int solution(int[][] board){
        // 1. 맵 초기화 (위에서부터 검은돌 (-1)로 꽉 채움)
        // 2. 맵 전체 탐색
        // 3. isSquare == true면 answer++;
        int answer = 0;
        initMap(board); // 1.
        display(board);

        boolean flag = true;
        while (flag) {  // 2.
            flag = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] > 0 && isSquare(board, i, j)) { // 3.
                        answer++;
                        flag = true;
                        display(board);
                    }
                }
            }
        }

        return answer;
    }

    private void display(int[][] map){
        System.out.println("---------------------------");
        for (int [] layer : map){
            System.out.println(Arrays.toString(layer).replaceAll("-1", "#"));
        }
    }

    private void initMap(int[][] map){
        for (int i = 0; i < map[0].length; i++) {
            int j = 0;
            while (j < map.length && map[j][i] == 0) map[j++][i] = -1;
        }
    }

    // -1 : 변환 가능
    // 0 : 절대 안됨
    private boolean isSquare(int[][] map, int x, int y){
        int value= map[x][y];

        int minX = Math.max(0, x - 2);  // 소속 가능한 범위
        int minY = Math.max(0, y - 2);
        int maxX = Math.min(map.length - 1, x + 2);
        int maxY = Math.min(map[0].length - 1, y + 2);

        int x1 = map.length, x2 = 0;
        int y1 = map[0].length, y2 = 0;
        for (int i = minX; i <= maxX; i++) {    // 블록이 속할 수 있는 직사각형 양 끝점
            for (int j = minY; j <= maxY; j++) {
                if (map[x][y] == map[i][j]){
                    x1 = Math.min(x1, i);
                    x2 = Math.max(x2, i);
                    y1 = Math.min(y1, j);
                    y2 = Math.max(y2, j);
                }
            }
        }

        boolean result = true;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (map[i][j] != map[x][y] && map[i][j] != -1) { // 직사각형 내에 다른 블록이면서 검은 블록이 아닐경우
                    result = false;
                    break;
                }
            }
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (map[i][j] == value && result) map[i][j] = -1;    // 해당 블록이 제거 가능하면 -1로 초기화
            }
        }

        if (result){
            for (int i = y1; i <= y2; i++) {        // 해당 블록이 제거 가능하면 그 아래까지 -1로 초기화
                int j = x1;
                while (j < map.length && (map[j][i] < 1)) map[j++][i] = -1;
            }
        }

        return result;
    }
}
