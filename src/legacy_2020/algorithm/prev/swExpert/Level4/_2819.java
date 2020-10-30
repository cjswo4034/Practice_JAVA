package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _2819 {
    static int result;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static char[][] array;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            array = new char[4][4];
            map = new HashMap<String, Integer>();

            StringTokenizer st;
            for (int i = 0; i < 4; i++) {
                String tmp = br.readLine();
                array[i][0] = tmp.charAt(0);
                array[i][1] = tmp.charAt(2);
                array[i][2] = tmp.charAt(4);
                array[i][3] = tmp.charAt(6);
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i, j, 1, new StringBuilder(array[i][j]));
                }
            }

            result = map.size();
            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
    }

    public static void dfs(int x, int y, int depth, StringBuilder sb){
        if(depth == 8){
            if(map.get(sb.toString()) == null)
                map.put(sb.toString(), 1);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + x;
            int ny = dir[i][1] + y;

            if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;

            dfs(nx, ny, depth + 1, sb.append(array[nx][ny]));
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
