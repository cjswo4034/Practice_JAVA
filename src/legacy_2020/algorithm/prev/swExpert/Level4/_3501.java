package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3501 {
    static int p, q, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            sb.append(solution(p, q, new int[Math.max(p, q) * 10 + 1])).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static String solution(int a, int b, int[] div){
        if (a % b == 0) return String.valueOf(a / b);
        StringBuilder sb = new StringBuilder();
        sb.append(a / b).append(".");
        int pos = String.valueOf(a / b).length() + 1;
        a = a % b * 10;
        while(true){
            if (a % b == 0) return sb.append(a / b).toString();
            if (div[a] == 0){
                div[a] = pos++;
                sb.append(a / b);
                a = a % b * 10;
            } else {
                sb.insert(div[a], "(").append(")");
                return sb.toString();
            }
        }
    }
}
