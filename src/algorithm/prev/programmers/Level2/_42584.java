package algorithm.prev.programmers.Level2;

import java.io.*;
import java.util.Arrays;

public class _42584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("#" + 1 + " : " + Arrays.toString(solution(new int[]{1, 2, 3, 2, 3})));
    }
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < answer.length - 1; i++) {
            int j = i + 1;
            while(j < answer.length && prices[i] <= prices[j]) j++;
            answer[i] = (j == answer.length ? j - 1 : j) - i;
        }
        return answer;
    }
}