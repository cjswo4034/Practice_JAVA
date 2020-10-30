package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3378 {
    static int p, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[][] inputs, outputs;
        boolean[] r, c, s;
        boolean[][][] parens;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            inputs = new char[p][];
            for (int i = 0; i < p; i++) {
                inputs[i] = br.readLine().toCharArray();
            }

            outputs = new char[q][];
            for (int i = 0; i < q; i++) {
                outputs[i] = br.readLine().toCharArray();
            }

            parens = new boolean[21][21][21];
            clear(parens);
            solve(parens, inputs);

            sb.append(answer(parens, outputs)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void solve(boolean[][][] parens, char[][] inputs){
        int[] parenCnt = new int[3];
        for (int i = 0; i < p; i++) {
            int count = 0;
            boolean dot = true;
            for (int j = 0; j < inputs[i].length; j++) {
                if (inputs[i][j] != '.') {
                    if(dot && count != 0){
                        probability(parens, parenCnt, count);
                    }
                    if (inputs[i][j] == '(' || inputs[i][j] == '{' || inputs[i][j] == '[') {
                        if (inputs[i][j] == '(') parenCnt[0]++;
                        else if (inputs[i][j] == '{') parenCnt[1]++;
                        else parenCnt[2]++;
                    } else if (inputs[i][j] == ')' || inputs[i][j] == '}' || inputs[i][j] == ']') {
                        if (inputs[i][j] == ')') parenCnt[0]--;
                        else if (inputs[i][j] == '}') parenCnt[1]--;
                        else parenCnt[2]--;
                    }
                    dot = false;
                } else if(dot) count++;
            }
        }
    }

    public static void probability(boolean[][][] parens, int[] parenCnt, int count){
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                for (int k = 0; k <= 20; k++) {
                    if (parenCnt[0] * i + parenCnt[1] * j + parenCnt[2] * k != count){
                        parens[i][j][k] = false;
                    }
                }
            }
        }
    }

    public static String answer(boolean[][][] parens, char[][] outputs){
        StringBuilder sb = new StringBuilder();
        int[] parenCnt = new int[3];
        for (int i = 0; i < q; i++) {
            sb.append(result(parens, parenCnt) + " ");
            for (int j = 0; j < outputs[i].length; j++) {
                if (outputs[i][j] == '(' || outputs[i][j] == '{' || outputs[i][j] == '[') {
                    if (outputs[i][j] == '(') parenCnt[0]++;
                    else if (outputs[i][j] == '{') parenCnt[1]++;
                    else parenCnt[2]++;
                } else if (outputs[i][j] == ')' || outputs[i][j] == '}' || outputs[i][j] == ']') {
                    if (outputs[i][j] == ')') parenCnt[0]--;
                    else if (outputs[i][j] == '}') parenCnt[1]--;
                    else parenCnt[2]--;
                }
            }
        }
        return sb.toString();
    }

    public static int result(boolean[][][] parens, int[] parenCnt){
        if (parenCnt[0] + parenCnt[1] + parenCnt[2] == 0) return 0;
        int count = 0, prev = 0, sum = 0;
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                for (int k = 1; k <= 20; k++) {
                    if(parens[i][j][k]){
                        sum = parenCnt[0] * i + parenCnt[1] * j + parenCnt[2] * k;
                        if (prev != sum) {
                            count++;
                            if (count > 1) return -1;
                        }
                        prev = sum;
                    }
                }
            }
        }
        return sum;
    }

    public static void clear(boolean[][][] parens){
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                for (int k = 1; k <= 20; k++) {
                    parens[i][j][k] = true;
                }
            }
        }
    }
}
