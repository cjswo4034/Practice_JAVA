package algorithm.prev.programmers.Level2;

import java.util.Arrays;
import java.util.Stack;

public class _42841 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}}));
    }

    public static int solution(int[][] input){
        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        initStack(stack);
        for(int curr : stack){
            boolean flag = false;
            for (int[] condition : input) {
                int value = condition[0];
                int strike = strikeCount(curr, value);
                int ball = ballCount(curr, value) - strike;
                flag = strike == condition[1] && ball == condition[2];
                if (!flag) break;
            }
            if (flag) answer++;
        }

        return answer;
    }

    private static void initStack(Stack<Integer> stack){
        boolean[] check = new boolean[10];
        for (int i = 123; i < 988; i++) {
            if (isSame(i, check)) continue;
            if (containZero(i)) continue;
            stack.push(i);
        }
    }

    private static boolean isSame(int num, boolean[] check){
        Arrays.fill(check, false);
        check[num % 10] = true;
        if (check[(num / 10) % 10]) return true;
        else {
            check[(num / 10) % 10] = true;
            return check[num / 100];
        }
    }

    private static boolean containZero(int num){
        while (num > 0){
            if (num % 10 == 0) return true;
            num /= 10;
        }
        return false;
    }

    private static int strikeCount(int curr, int target){
        int cnt = 0;
        while (curr > 0){
            if (curr % 10 == target % 10) cnt++;
            curr /= 10;
            target /= 10;
        }
        return cnt;
    }

    private static int ballCount(int curr, int target){
        int cnt = 0, tmp;
        while (curr > 0){
            tmp = target;
            while (tmp > 0){
                if (curr % 10 == tmp % 10) cnt++;
                tmp /= 10;
            }
            curr /= 10;
        }
        return cnt;
    }
}
