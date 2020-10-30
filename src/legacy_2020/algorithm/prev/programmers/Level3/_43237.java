package algorithm.prev.programmers.Level3;

import java.util.stream.IntStream;

public class _43237 {
    public static void main(String[] args) {
        _43237 prob = new _43237();
        System.out.println(prob.solution(new int[]{120, 110, 140, 150}, 485));
    }

    public int solution(int[] budgets, int M) {
        int answer = 0;

        int tot;   // 상한과 예산 차의 합
        int min = 0, criteria = 0, max = IntStream.of(budgets).max().orElse(Integer.MAX_VALUE);
        while (min <= max){
            criteria = (min + max) >> 1;
            tot = getSum(budgets, criteria);

            if (tot < M){    // 상한과 예산 차의 합이 상한보다 작으면
                min = criteria + 1;
                answer = Math.max(criteria, answer);
            } else {
                max = criteria - 1;
            }
        }

        return answer;
    }

    private int getSum(int[] budgets, int criteria){
        return IntStream.of(budgets)
                .reduce(0, (left, right) -> left + Math.min(criteria, right));
    }
}