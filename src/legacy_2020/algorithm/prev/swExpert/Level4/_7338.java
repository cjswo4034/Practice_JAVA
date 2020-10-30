package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _7338 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long year = Long.parseLong(st.nextToken()) - 2016;
            long month = Long.parseLong(st.nextToken());
            long resultY = 0;
            long resultM = 0;

            resultM = (year * 12) + month;
            resultY = resultM / 13 + 2016;
            resultM = resultM % 13;

            if(resultM == 0){
                resultY--;
                resultM = 13;
            }

            sb.append("#").append(t).append(" ").append(resultY).append(" ").append(resultM).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
