package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _1808 {
    static int n, result;
    static int[] check;
    static boolean[] number =new boolean[10];
    static LinkedList<value> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            list = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 10; i++) {
                number[i] = st.nextToken().equals("1");
            }
            n = Integer.parseInt(br.readLine());
            check = new int[n + 1];

            int skip = -1;
            Arrays.fill(check, Integer.MAX_VALUE);
            for (int i = 2 ; i <= n ; i++){
                int tmp = check(i);
                if(tmp != -1) {
                    list.add(new value(i, tmp + 1));
                    if(i == n) skip = tmp + 1;
                }
            }

            if(skip != -1){
                result = skip;
            } else {
                result = Integer.MAX_VALUE;
                dfs(1, 0);
            }

            result = result == Integer.MAX_VALUE ? -1 : result; // v == 1일 때 2로
            bw.write("#" + t + " " + result + "\n");
            bw.flush();
        }
    }

    public static void dfs(int mul, int sum){
        if(sum > result) return;
        if(mul == n){
            result = Math.min(result, sum);
            return;
        }

        for (int i = 0, length = list.size(); i < length; i++) {
            int nextMul = mul * list.get(i).v;
            int nextSum = sum + list.get(i).leng;

            if(nextMul > n) return;
            if(nextSum < check[nextMul]){
                check[nextMul] = nextSum;
                dfs(nextMul, nextSum);
            }
        }
    }

    public static int check(int num){
        int cnt = 0;
        while(num > 0){
            if(!number[num % 10]) return -1;
            num /= 10;
            cnt++;
        }
        return cnt;
    }

    static class value{
        int v, leng;

        public value(int v, int leng) {
            this.v = v;
            this.leng = leng;
        }
    }
}
