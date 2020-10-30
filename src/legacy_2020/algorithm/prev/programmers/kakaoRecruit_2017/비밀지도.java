package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.Arrays;

public class 비밀지도 {
    public static void main(String[] args) {
        비밀지도 prob = new 비밀지도();
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        System.out.println(Arrays.toString(prob.solution(5, arr1, arr2)));

        arr1 = new int[]{46, 33, 33, 22, 31, 50};
        arr2 = new int[]{27, 56, 19, 14, 14, 10};
        System.out.println(Arrays.toString(prob.solution(6, arr1, arr2)));
    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        for (int i = 0; i < arr1.length; i++)
            answer[i] = transAns(arr1[i] | arr2[i], n);
        return answer;
    }

    private String transAns(int num, int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (((1 << i) & num) > 0) sb.append("#");
            else sb.append(" ");
        }
        return sb.reverse().toString();
    }
}