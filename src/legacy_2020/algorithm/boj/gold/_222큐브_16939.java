package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* [Gold 1] 구현, 시뮬레이션
 * 크기가 2x2x2인 루빅스 큐브를 한 번 돌려서 풀 수 있는지 확인한다.
 * 한 번 돌린다는 것은 한 면을 90도만 왼쪽이나 오른쪽으로 회전한다는 것.
 * */
public class _222큐브_16939 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Cube cube = new Cube();
        for (int i = 1; i <= 24; i++)
            cube.origin[i] = Integer.parseInt(st.nextToken());

        System.out.println(cube.rotate() ? 1 : 0);
    }

    static class Cube {
        int[] origin, copiedArr;
        Face[] faces = new Face[6];
        // 상: [1 2 3 4] 5 6 17 18 21 22 13 14
        // 앞: [5 6 7 8] 3 4 17 19 10 9 16 14
        // 하: [9 10 11 12] 15 16 7 8 19 20 23 24
        // 좌: [13 14 15 16] 1 3 5 7 9 11 24 22
        // 우: [17 18 19 20] 4 2 21 23 12 10 8 6
        // 뒤: [21 22 23 24] 20 18 2 1 13 15 12 11

        Cube() {
            int[] indices = new int[]{5, 6, 17, 18, 21, 22, 13, 14, 3, 4, 17, 19, 10, 9, 16, 14, 15, 16, 7, 8, 19, 20, 23, 24, 1, 3, 5, 7, 9, 11, 24, 22, 4, 2, 21, 23, 12, 10, 8, 6, 20, 18, 2, 1, 13, 15, 12, 11};
            origin = new int[25];
            copiedArr = new int[25];
            for (int i = 0; i < 6; i++) {
                faces[i] = new Face();
                // 정면의 인덱스
                // 입력순서: lu -> ru -> ld -> rd (= 0 -> 1 -> 2 -> 3)
                // 회전순서: lu -> ru -> rd -> ld (= 0 -> 1 -> 3 -> 2)
                // for (int j = 0; j < 2; j++) faces[i].front[j] = (i * 4) + j + 1;
                // for (int j = 2; j < 4; j++) faces[i].front[j] = (i * 4) - j + 6;
                faces[i].front[0] = (i * 4) + 1;
                faces[i].front[1] = (i * 4) + 2;
                faces[i].front[2] = (i * 4) + 4;
                faces[i].front[3] = (i * 4) + 3;
                System.arraycopy(indices, 8 * i, faces[i].side, 0, 8);
            }
        }

        boolean rotate() {
            for (int i = 0; i < 6; i++) {
                copy();
                rotate(i);  // 오른쪽으로 한번 회전
                if (isSolved()) return true;

                rotate(i);
                rotate(i); // 왼쪽으로 한번 회전
                if (isSolved()) return true;
            }
            return false;
        }

        void rotate(int idx) {
            // 정면은 한번 회전
            rotate(faces[idx].front);

            // 옆면은 두번 회전
            rotate(faces[idx].side);
            rotate(faces[idx].side);
        }

        void rotate(int[] arr) {
            int prev = copiedArr[arr[arr.length - 1]];
            for (int i: arr) {
                int tmp = copiedArr[i];
                copiedArr[i] = prev;
                prev = tmp;
            }
        }

        boolean isSolved() {
            for (Face face : faces) {
                int color = copiedArr[face.front[0]];
                for (int idx : face.front) {
                    if (color != copiedArr[idx]) return false;
                }
            }
            return true;
        }

        void copy() {
            System.arraycopy(origin, 0, copiedArr, 0, copiedArr.length);
        }

        static class Face {
            int[] front, side;

            public Face() {
                this.side = new int[8];
                this.front = new int[4];
            }
        }
    }
}
