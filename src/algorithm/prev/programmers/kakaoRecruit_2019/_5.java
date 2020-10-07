package algorithm.prev.programmers.kakaoRecruit_2019;

import java.util.ArrayList;
import java.util.Arrays;

// pass
public class _5 {
    public static void main(String[] args) {
        _5 prob = new _5();
        int[][] answer = prob.solution(5, new int[][]{{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}, {5,1,0,0}, {5,2,0,0}});

       /* System.out.println("===================");
        answer = prob.solution(5, new int[][]{{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}});
        prob.display(answer);*/
    }

    // [x, y, 기둥 | 보, 삭제 | 설치]
    // 보는 오른쪽으로, 기둥은 위쪽으로 설치
    // return : [x, y, 기둥 | 보]
    // return : x, y, a 기준 오름차순

    // 규칙은 나중에 처리하고 입력에 맞게 맵 생성
    public int[][] solution(int n, int[][] build_frame) {
        int[][] pillar = new int[n + 1][n + 1]; // 기둥
        int[][] frame = new int[n + 1][n + 1];  // 보
        for (int[] cmd : build_frame){  // 1 0 0 1
            if (cmd[2] == 0) {
                if (cmd[3] == 0) pillar[cmd[0]][cmd[1]] = 0;
                if (cmd[3] == 1) pillar[cmd[0]][cmd[1]] = 1;
            } else {
                if (cmd[3] == 0) frame[cmd[0]][cmd[1]] = 0;
                if (cmd[3] == 1) frame[cmd[0]][cmd[1]] = 1;
            }
            if (!pillarCheck(frame, pillar) || !frameCheck(frame, pillar)){
                if (cmd[2] == 0) pillar[cmd[0]][cmd[1]] ^= 1;
                if (cmd[2] == 1) frame[cmd[0]][cmd[1]] ^= 1;
            }
        }

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < pillar.length; i++) {
            for (int j = 0; j < pillar[i].length; j++) {
                if (pillar[i][j] == 1) list.add(new int[]{i, j, 0});
                if (frame[i][j] == 1) list.add(new int[]{i, j, 1});
            }
        }

        list.sort((arr1, arr2) -> {
            if (arr1[0] == arr2[0] && arr1[1] == arr2[1]) return Integer.compare(arr1[2], arr2[2]);
            if (arr1[1] == arr2[1]) return Integer.compare(arr1[1], arr2[1]);
            return Integer.compare(arr1[0], arr2[0]);
        });

        int[][] answer = new int[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 기둥
    // 1) 바닥 위에 있거나 (설치 x == 0, 삭제 : 위나 아래에 기둥이 있거나, 동일한 위치나 y - 1에 보가 없으면 안됨)
    // 2) 보의 한쪽 끝 윗부분에 있거나 (설치 동일한 위치나 y - 1에 보가 있어야됨, 삭제 :
    // 3) 다른 기둥 위에 있어야 한다.
    private boolean pillarCheck(int[][] frame, int[][] pillar){
        for (int x = 0; x < pillar.length; x++) {
            for (int y = 0; y < pillar[0].length; y++) {
                if (pillar[x][y] == 1){
                    boolean flag = false;
                    // 1) 바닥 위에 있거나
                    if (y == 0) flag = true;
                    // 2) 보의 한쪽 끝 윗부분에 있거나 (설치 동일한 위치나 y - 1에 보가 있어야됨, 삭제 :
                    if (frame[x][y] == 1 || (x > 0 && frame[x - 1][y] == 1)) flag = true;
                    // 3) 다른 기둥 위에 있어야 한다.
                    if (y > 0 && pillar[x][y - 1] == 1) flag = true;

                    if (!flag) return false;
                }
            }
        }
        return true;
    }

    // 보
    // 1) 한쪽 끝 부분이 기둥 위에 있거나
    // 2) 양쪽 끝 부분이 다른 보와 동시에 연결되어야 한다.
    private boolean frameCheck(int[][] frame, int[][] pillar){
        for (int x = 0; x < frame.length; x++) {
            for (int y = 0; y < frame[0].length; y++) {
                if (frame[x][y] == 1){
                    boolean flag = false;
                    // 한쪽 끝 부분이 기둥 위에 있거나
                    if (y > 0 && pillar[x][y - 1] == 1) flag = true;    // 아래
                    if (y > 0 && x + 1 < frame.length && pillar[x + 1][y - 1] == 1) flag = true;    // 오른쪽 아래
                    // 양쪽 끝 부분이 다른 보와 동시에 연결되어야 한다.
                    if ((x > 0 && frame[x - 1][y] == 1) && (x + 1 < frame.length && frame[x + 1][y] == 1)) flag = true;
                    if (!flag) return false;
                }
            }
        }
        return true;
    }

    private void display(int[][] arr){
        for (int[] row : arr){
            System.out.println(Arrays.toString(row));
        }
    }
}
