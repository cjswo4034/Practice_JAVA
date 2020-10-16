package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** [Gold 1] 구현, 시뮬레이션
 *  - 높은 벽으로 둘러쌓인 영역을 가장 낮은 벽의 높이 만큼 높일 때 필요한 수를 구한다.
 *    1) 그룹으로 나눌 배열,
 *    2) 그룹 합계의 최대값을 저장할 배열
 *    3) 원본 배열
 **/
public class 수영장만들기_1113 {
    static final int[][] DIR = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = line[j] - '0';
            }
        }

        // 전체를 2부터 채운다.
        // 원본에 비해 높아진 영역을 따라가다가 외곽이 나오면 틀린거.
        // -> 해당 영역의 그룹은 다음 차례부터 손대지 않기
        for (int waterLv = 2; waterLv < 10; waterLv++) {

        }
    }

    // 1. 가생이 빼고 waterLv만큼 채운다.
    // 2. 수위가 lv이고 원본 수위와 다른 인접한 영역들로 나눈다.
    // 3. 각각의 영역들의 외곽(원본과 같은) 중
    static int fillPool(int waterLv) {
        int[][] waterPool = copy();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (waterPool[i][j] < waterLv)
                    waterPool[i][j] = waterLv;
            }
        }
        return -1;
    }

    static int[][] copy() {
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, res[i], 0, map[i].length);
        }
        return res;
    }
}
