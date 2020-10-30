package algorithm.prev.programmers.Level2;

import java.io.*;
import java.util.Arrays;

public class _42588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            System.out.println("#" + i + " : " + Arrays.toString(solution(new int[]{1, 2, 3, 4, 5})));
        }
    }

    public static int[] solution(int[] heights) {
        int[] answer = new int[heights.length];
        for (int i = heights.length - 1; i >= 0 ; i--){
            int j = i - 1;
            while(j >= 0 && heights[j] <= heights[i]) j--;
            answer[i] = j + 1;
        }
        return answer;
    }
}