package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 큐빙_5373_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Cube cube = new Cube();
        cube.rotate(Type.F, false);

        int t = Integer.parseInt(br.readLine()), n;
        while (t-- > 0) {
            cube = new Cube();
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            char [] cmd;
            for (int i = 0; i < n; i++) {
                cmd = st.nextToken().toCharArray();
                cube.rotate(Type.valueOf(String.valueOf(cmd[0])), cmd[1] == '+');
            }

            sb.append(cube.getUpFace());
        }
        System.out.println(sb);
    }

    enum Type {
        U(0, 'w'), D(1, 'y'), L(2, 'g'), R(3, 'b'), F(4, 'r'), B(5, 'o');

        int num;
        char color;

        Type(int v1, char v2) { num = v1; color = v2; }

        public char getCharName() {
            return this.name().charAt(0);
        }
    }

    static class Cube {
        Face[] faces = new Face[6];

        public Cube() {
            for (Type type: Type.values())
                faces[type.num] = new Face(type.color);

            for (int i = 0; i < 6; i++)
                setFaceFields(i);
        }

        // u d l r -> u r d l
        private void setFaceFields(int idx) {
            switch (idx) {
                case 0: faces[0].setClosedFace(faces[5], faces[3], faces[4], faces[2]); faces[0].setAngle(180, 270, 0, 90); break;
                case 1: faces[1].setClosedFace(faces[4], faces[3], faces[5], faces[2]); faces[1].setAngle(0, 90, 180, 270); break;
                case 2: faces[2].setClosedFace(faces[0], faces[4], faces[1], faces[5]); faces[2].setAngle(270, 0, 90, 0); break;
                case 3: faces[3].setClosedFace(faces[0], faces[5], faces[1], faces[4]); faces[3].setAngle(90, 0, 270, 0); break;
                case 4: faces[4].setClosedFace(faces[0], faces[3], faces[1], faces[2]); faces[4].setAngle(0, 0, 0, 0); break;
                case 5: faces[5].setClosedFace(faces[0], faces[2], faces[1], faces[3]); faces[5].setAngle(180, 0, 180, 0); break;
            }
        }

        public String getUpFace() {
            StringBuilder sb = new StringBuilder();
            for (char[] chars: faces[Type.U.num].map) {
                for (char el: chars) sb.append(el);
                sb.append("\n");
            }
            return sb.toString();
        }

        public void rotate(Type type, boolean isClockwise) {
            rotate(type.num, isClockwise);
        }

        private void rotate(int idx, boolean isClockwise) {
            Face frontFace = faces[idx];

            // 1. 정면 회전
            frontFace.rotate(isClockwise);

            // 2. 앞면을 제외한 사면을 회전
            frontFace.rotateFacesByAngle(false);

            // 3. 회전한 큐브를 복사 (앞에꺼만 미리 복사?)
            Face [] copiedFaces = frontFace.getClosedFaces();

            // 4. 사면 회전 (u r d l)
//            int fromV, toV;
//            boolean isRow, reverse;
//            for (int from = 0, to = isClockwise ? 1 : 3; from <= 3; from++, to++) {
//                to = to % 4;
//                fromV = (from == 0 || from == 3) ? 2 : 0;
//                toV = (isClockwise && from < 2) ? 0 : 2;
//                isRow = from % 2 == 0;
//                reverse = isRow ^ isClockwise;
//                transpose(copiedFaces[from], frontFace.closedFaces[to], isRow, fromV, toV, reverse);
//            }
            if (isClockwise) {
                transpose(copiedFaces[0], frontFace.closedFaces[1], true, 2, 0, false); // u
                transpose(copiedFaces[1], frontFace.closedFaces[2], false, 0, 0, true); // r
                transpose(copiedFaces[2], frontFace.closedFaces[3], true, 0, 2, false); // d
                transpose(copiedFaces[3], frontFace.closedFaces[0], false, 2, 2, true); // l
            } else {
                transpose(copiedFaces[0], frontFace.closedFaces[3], true, 2, 2, true);  // u
                transpose(copiedFaces[1], frontFace.closedFaces[0], false, 0, 2, false);// r
                transpose(copiedFaces[2], frontFace.closedFaces[1], true, 0, 0, true);  // d
                transpose(copiedFaces[3], frontFace.closedFaces[2], false, 2, 0, false);// l
            }

            // 5. 원상복구
            frontFace.rotateFacesByAngle(true);
        }

        /**
         * @param from  옮길 면.
         * @param to    옮겨질 면.
         * @param isRow 옮길 면의 행을 옮기면 True, 열이면 False.
         * @param fromV 옮길 면의 몇 번째 행 or 열.
         * @param toV   옮길 면의 몇 번째 행 or 열.
         * @param reverse 옮길 면의 원소를 역순으로 옮김.
         */
        private void transpose(Face from, Face to, boolean isRow, int fromV, int toV, boolean reverse) {
            int j = reverse ? 2 : 0;
            int adder = reverse ? -1 : 1;
            if (isRow) {
                for (int i = 0; i < 3; i++, j+= adder)
                    to.map[i][toV] = from.map[fromV][j];
            } else {
                for (int i = 0; i < 3; i++, j+= adder)
                    to.map[toV][i] = from.map[j][fromV];
            }
        }

        static class Face {
            Face [] closedFaces = new Face[4];
            char color;
            int [] angles = new int[4];
            char[][] map = new char[3][3];

            Face (char color) {
                this.color = color;
                for (int i = 0; i < 3; i++) Arrays.fill(map[i], color);
            }

            Face (char[][] map) {
                for (int i = 0; i < 3; i++)
                    System.arraycopy(map[i], 0, this.map[i], 0, 3);
            }

            void setClosedFace(Face... faces) {
                this.closedFaces = faces;
            }

            void setAngle(int... angles) {
                this.angles = angles;
            }

            public Face[] getClosedFaces() {
                Face[] res = new Face[4];
                for (int i = 0; i < 4; i++) {
                    res[i] = new Face(closedFaces[i].map);
                }
                return res;
            }

            public void rotateFacesByAngle(boolean flag) {
                for (int i = 0; i < 4; i++)
                    closedFaces[i].rotateByAngle(angles[i], flag);
            }

            public void rotateByAngle(int angle, boolean flag) {
                if (angle <= 0) return;
                rotateByAngle(flag ? 360 - angle : angle);
            }

            private void rotateByAngle(int angle) {
                while (angle >= 90) {
                    rotate(true);
                    angle -= 90;
                }
            }

            private void rotate(boolean isClockwise) {
                char[][] tmp = new char[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (isClockwise) tmp[i][j] = map[2 - j][i];
                        else tmp[i][j] = map[j][2 - i];
                    }
                }
                this.map = tmp;
            }
        }
    }
}
