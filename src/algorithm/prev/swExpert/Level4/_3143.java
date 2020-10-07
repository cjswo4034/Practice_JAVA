package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _3143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();

            int idx = 0, result = A.length();
            while ((idx = A.indexOf(B, idx)) != -1){
                result = result - B.length() + 1;
                idx += B.length();
            }

            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
    }
}
