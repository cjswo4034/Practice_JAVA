package algorithm.prev.programmers.Level3;

import java.util.Arrays;

public class _12938 {
    public static void main(String[] args) {
        _12938 sol = new _12938();
        System.out.println(Arrays.toString(sol.solution(2, 9)));
    }

    public int[] solution(int n, int s) {
        if (n > s) return new int[]{-1};

        int[] answer = new int[n];
        int div = s / n;
        int rem = s - div * n;
        int idx = n - 1;

        Arrays.fill(answer, div);
        while (rem-- > 0) answer[idx--]++;

        return answer;
    }
}