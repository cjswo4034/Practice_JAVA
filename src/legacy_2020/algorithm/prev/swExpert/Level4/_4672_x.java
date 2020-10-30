package algorithm.prev.swExpert.Level4;

import java.io.*;
import java.util.Arrays;

public class _4672_x {
    static int result;
    static char[] input;
    static int[] inputs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input = br.readLine().toCharArray();
            bw.write("#" + t);

            inputs = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                inputs[i] = i;
            }

            result = 0;
            Arrays.sort(input);
            while(nextPermutation()){
                solve();
            }

            bw.write(" " + result + "\n");
            bw.flush();
        }
    }

    static boolean nextPermutation(){
        int i = input.length - 1, j = input.length - 1;
        while(i > 0 && inputs[i-1] >= inputs[i]) i--;
        if(i <= 0) return false;

        while(inputs[i - 1] >= inputs[j]) j--;
        swap(i - 1, j);

        j = input.length - 1;
        while (i < j){
            swap(i, j);
            i++; j--;
        }
        return true;
    }

    static void swap(int i, int j){
        int tmp = input[i];
        input[i] = input[j];
        input[j] = (char)tmp;

        tmp = inputs[i];
        inputs[i] = inputs[j];
        inputs[j] = tmp;
    }

    static void solve(){
        int tmpResult = input.length;
        for (int i = 2; i <= input.length; i++) {
            for (int j = 0; j <= input.length - i; j++) {
                if(isPanli(Arrays.copyOfRange(input, j, j + i))){
                    tmpResult++;
                }
            }
        }
        result = Math.max(result, tmpResult);
    }

    static boolean isPanli(char[] chs){
        for (int i = 0, length = chs.length / 2; i <= length; i++) {
            if(chs[i] != chs[chs.length - (i + 1)]){
                return false;
            }
        }
        return true;
    }
}
