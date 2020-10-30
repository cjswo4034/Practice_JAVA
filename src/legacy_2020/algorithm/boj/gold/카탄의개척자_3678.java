package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* [Gold 1]
 * 1. 각각의 겹에 번호를 매긴다.
 *    - 겹(i)의 시작은 (i - 1)의 끝 + 1부터 시작한다.
 *    - 겹(i)의 크기는 (i - 1) * 6이다.
 *    - 겹(i)의 끝은 시작 + 크기 - 1이다.
 *    - 변의 길이는 i이다.
 *    - 첫 번째 꼭지점은 시작 + (변의 길이 - 2) 이고, 이후 꼭지점은 첫 꼭지점 + (변의 길이 - 1) * 1 ~ 5이다.
 * 2. 각각의 타일이 인접하는 영역을 찾는다.
 *    - 모든 타일은 이전의 타일과 인접한다.
 *    - 타일이 겹의 시작이라면 이전 겹의 시작과 끝 타일과 인접한다.
 *    - 타일이 겹의 끝이라면 겹의 시작, 이전 겹의 끝과 인접한다.
 *    - 타일이 꼭지점이라면 바로 이전의 타일, 같은 위치에 있는 이전 겹의 꼭지점(2)만 인접한다.
 *    - 타일이 겹의 시작과 끝, 꼭지점이 아니라면 이전 타일이 인접한 이전 겹의 마지막 타일(j), 그 다음 타일(j + 1)과 인접한다.
 * */
public class 카탄의개척자_3678 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 미리 구한다.
        int[] tiles = generateTiles();
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) sb.append(tiles[Integer.parseInt(br.readLine())]).append("\n");

        System.out.print(sb.toString());
    }

    static int[] generateTiles() {
        int[] tiles = new int[10300];       // 타일에 매겨질 수
        int[] adjTile = new int[10300];     // 타일과 인접한 이전 겹의 타일 번호

        int[] count = new int[6];           // 사용된 수의 개수
        int[] size = new int[60];           // 겹의 크기
        int[][] indices = new int[60][2];   // 겹의 시작과 끝 타일 번호

        // 겹의 크기와 시작, 끝 번호 매기기
        tiles[1] = count[1] = size[1] = 1;
        indices[1][0] = indices[1][1] = 1;
        for (int i = 2; i < 60; i++) {
            size[i] = (i - 1) * 6;
            indices[i][0] = indices[i - 1][1] + 1;
            indices[i][1] = indices[i][0] + size[i] - 1;
        }

        // 타일에 번호 할당하기
        int index, adj, vertex;
        for (int i = 2; i < 60; i++) {
            for (int j = 0; j < size[i]; j++) {
                // 확인하려는 타일 Index
                index = indices[i][0] + j;

                // 꼭지점 번호. 꼭지점이 아니면 -1
                vertex = getVertex(index, i, indices[i][0]);

                // 1. 모든 타일은 이전의 타일과 인접한다.
                adj = (1 << tiles[index - 1]);
                if (index == indices[i][0]) {               // 2. 타일이 겹의 시작이라면 이전 겹의 시작과 끝 타일과 인접한다.
                    adjTile[index] = indices[i - 1][0];
                    adj |= (1 << tiles[indices[i - 1][0]]);
                    adj |= (1 << tiles[indices[i - 1][1]]);
                } else if (index == indices[i][1]) {        // 3. 타일이 겹의 끝이라면 겹의 시작, 이전 겹의 끝과 인접한다.
                    adjTile[index] = indices[i - 1][1];
                    adj |= (1 << tiles[indices[i][0]]);
                    adj |= (1 << tiles[indices[i - 1][1]]);
                } else if (vertex != -1) {                  // 4. 타일이 꼭지점이라면 같은 위치에 있는 이전 겹의 꼭지점과 인접한다.
                    adjTile[index] = getPreVertex(vertex, i - 1, indices[i - 1][0]);
                    adj |= (1 << tiles[adjTile[index]]);
                } else {                                    // 5. 타일이 중간이라면 이전 타일이 인접한 이전 겹의 마지막 타일과 그 다음 타일과 인접한다.
                    adjTile[index] = adjTile[index - 1] + 1;
                    adj |= (1 << tiles[adjTile[index]]);
                    adj |= (1 << tiles[adjTile[index - 1]]);
                }

                tiles[index] = getSmallestNum(count, adj);
                count[tiles[index]]++;
            }
        }
        return tiles;
    }

    // 겹의 첫 번째 타일이 start이고 변의 길이가 side일 때 index는 몇 번째 꼭지점인가?
    static int getVertex(int index, int side, int start) {
        // 첫 번째 꼭지점 Index
        int first = start + side - 2;
        if ((index - first) % (side - 1) == 0)
            return (index - first) / (side - 1) + 1;
        return -1;
    }

    // 겹의 첫 번째 타일이 start이고 변의 길이가 side일 때 vertex번째 꼭지점의 Index
    static int getPreVertex(int cnt, int side, int start) {
        if (side == 1) return 1;
        return start + (side - 2) + (side - 1) * (cnt - 1);
    }

    // 인접한 수가 아니면서 가장 적게 사용된 수. 가능한 수가 여러개라면 낮은 수를 반환한다.
    static int getSmallestNum(int[] count, int adj) {
        int num = 0, cnt = 987654321;
        for (int i = 1; i <= 5; i++) {
            if ((adj & (1 << i)) > 0) continue;
            if (cnt > count[i]) {
                cnt = count[i];
                num = i;
            }
        }
        return num;
    }
}
