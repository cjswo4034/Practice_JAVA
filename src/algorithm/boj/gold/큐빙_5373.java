package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 큐빙_5373 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Cube cube;
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
        static final int CUBE_SIZE = 3;
        char[][][] faces, tmpFaces;
        boolean isClockwise;
        int rotateUAngle, rotateDAngle, rotateLAngle, rotateRAngle;

        public Cube() {
            faces = new char[6][CUBE_SIZE][CUBE_SIZE];
            tmpFaces = new char[6][CUBE_SIZE][CUBE_SIZE];
            for (Type type: Type.values()) {
                for (int j = 0; j < CUBE_SIZE; j++) {
                    Arrays.fill(faces[type.num][j], type.color);
                }
            }
        }

        public String getUpFace() {
            StringBuilder sb = new StringBuilder();
            for (char[] chars: faces[Type.U.num]) {
                for (char el: chars) sb.append(el);
                sb.append("\n");
            }
            return sb.toString();
        }

        public void rotate(Type type, boolean isClockwise) {
            this.isClockwise = isClockwise;

            switch (type) {
                case U: rotate(type, Type.B, Type.F, Type.L, Type.R); break;
                case D: rotate(type, Type.F, Type.B, Type.L, Type.R); break;
                case F: rotate(type, Type.U, Type.D, Type.L, Type.R); break;
                case B: rotate(type, Type.U, Type.D, Type.R, Type.L); break;
                case L: rotate(type, Type.U, Type.D, Type.B, Type.F); break;
                case R: rotate(type, Type.U, Type.D, Type.F, Type.B); break;
            }
        }

        private void rotate(Type front, Type up, Type down, Type left, Type right) {
            // 1.정면 큐브 회전
            faces[front.num] = rotate(front);

            // 2. 앞면을 제외한 나머지 사면을 회전
            setRotateAngle(front);
            rotateByAngle(up, down, left, right, true);

            // 3. 회전한 큐브를 Copy
            copyArr(up, down, left, right);

            // 4. Copy 큐브를 원본 큐브에 Transpose
            if (isClockwise) {
                transpose(up, right, true, CUBE_SIZE - 1, 0, false);
                transpose(down, left, true, 0, CUBE_SIZE - 1, false);
                transpose(left, up, false, CUBE_SIZE - 1, CUBE_SIZE - 1, true);
                transpose(right, down, false, 0, 0, true);
            } else {
                transpose(up, left, true, CUBE_SIZE - 1, CUBE_SIZE - 1, true);
                transpose(down, right, true, 0, 0, true);
                transpose(left, down, false, CUBE_SIZE - 1, 0, false);
                transpose(right, up, false, 0, CUBE_SIZE - 1, false);
            }

            // 5. 원본 큐브 복구
            rotateByAngle(up, down, left, right, false);
        }

        private void copyArr(Type... types) {
            for (Type type: types) {
                for (int i = 0; i < CUBE_SIZE; i++) {
                    System.arraycopy(faces[type.num][i], 0, tmpFaces[type.num][i], 0, CUBE_SIZE);
                }
            }
        }

        /**
         * @param from  옮길 면.
         * @param to    옮겨질 면.
         * @param isRow 옮길 면의 행을 옮기면 True, 열이면 False.
         * @param fromV 옮길 면의 몇 번째 행 or 열.
         * @param toV   옮길 면의 몇 번째 행 or 열.
         * @param reverse 옮길 면의 원소를 역순으로 옮김.
         */
        private void transpose(Type from, Type to, boolean isRow, int fromV, int toV, boolean reverse) {
            int j = reverse ? CUBE_SIZE - 1 : 0;
            int adder = reverse ? -1 : 1;
            if (isRow) {
                for (int i = 0; i < CUBE_SIZE; i++, j+= adder)
                    faces[to.num][i][toV] = tmpFaces[from.num][fromV][j];
            } else {
                for (int i = 0; i < CUBE_SIZE; i++, j+= adder)
                    faces[to.num][toV][i] = tmpFaces[from.num][j][fromV];
            }
        }

        private void setRotateAngle(Type front) {
            rotateUAngle = rotateDAngle = rotateLAngle = rotateRAngle = 0;
            switch (front) {
                case U: rotateLAngle = 90; rotateUAngle = 180; rotateRAngle = 270; break;
                case D: rotateLAngle = 270; rotateDAngle = 180; rotateRAngle = 90; break;
                case B: rotateUAngle = rotateDAngle = 180; break;
                case L: rotateDAngle = 90; rotateUAngle = 270; break;
                case R: rotateDAngle = 270; rotateUAngle = 90; break;
            }
        }

        private void rotateByAngle(Type up, Type down, Type left, Type right, boolean flag) {
            boolean tmp = isClockwise;
            isClockwise = true;
            rotate(up, flag ? rotateUAngle : 360 - rotateUAngle);
            rotate(down, flag ? rotateDAngle : 360 - rotateDAngle);
            rotate(left, flag ? rotateLAngle : 360 - rotateLAngle);
            rotate(right, flag ? rotateRAngle : 360 - rotateRAngle);
            isClockwise = tmp;
        }

        private void rotate(Type type, int angle) {
            if (angle <= 0) return;
            faces[type.num] = rotate(type);
            angle -= 90;
            while (angle > 0) {
                angle -= 90;
                faces[type.num] = rotate(type);
            }
        }

        private char[][] rotate(Type type) {
            char[][] tmp = new char[CUBE_SIZE][CUBE_SIZE];
            for (int i = 0; i < CUBE_SIZE; i++) {
                for (int j = 0; j < CUBE_SIZE; j++) {
                    if (isClockwise) tmp[i][j] = faces[type.num][(CUBE_SIZE - 1) - j][i];
                    else tmp[i][j] = faces[type.num][j][(CUBE_SIZE - 1) - i];
                }
            }
            return tmp;
        }
    }
}
