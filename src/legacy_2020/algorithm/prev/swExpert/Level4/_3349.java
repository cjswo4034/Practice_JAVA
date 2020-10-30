package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3349 {
    static int n, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken(); st.nextToken();
            n = Integer.parseInt(st.nextToken());

            result = 0;
            st = new StringTokenizer(br.readLine());
            int fromX = Integer.parseInt(st.nextToken());
            int fromY = Integer.parseInt(st.nextToken());

            for (int i = 1; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int toX = Integer.parseInt(st.nextToken());
                int toY = Integer.parseInt(st.nextToken());

                result += solve(fromX, fromY, toX, toY);

                fromX = toX;
                fromY = toY;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static int solve(int x, int y, int nx, int ny){
        if (nx >= x && ny >= y) { // 1사분면
            return Math.max(nx - x, ny - y);
        } else if (nx < x && ny < y) { // 3사분면
            return Math.max(x - nx, y - ny);
        } else {    // 2, 4사분면
            return Math.abs(nx - x) + Math.abs(ny - y);
        }
    }
}