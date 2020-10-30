package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.StringTokenizer;

public class _6855 {
    static int n, k, result;
    static int[] array = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());


            bw.write("#" + t + " " + result);
        }
    }

    public static void test(int from, int to){

    }
}
