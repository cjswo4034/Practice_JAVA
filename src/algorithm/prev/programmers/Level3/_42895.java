package algorithm.prev.programmers.Level3;

import java.util.ArrayList;

public class _42895 {
    public static void main(String[] args) {
        System.out.println(solution(5, 3600));
        System.out.println(solution(2, 11));
    }

    // (5 + 55) * (5 + 55) 뒤에가 괄호일 때
    public static int solution(int N, int number){
        ArrayList<Number> list = new ArrayList<>();
        initList(list, N);

        int ans = dfs(N, number, 0, 0, list);

        return ans <= 8 ? ans : -1;
    }

    private static void initList(ArrayList<Number> list, int N){
        Number number;
        for (int i = 0, ni = 0; i < 8; i++) {
            if (i != 0) ni = ni * 10 + N;
            for (int j = 0, nj = 0; j < 8 - i; j++) {
                nj = nj * 10 + N;
                number = new Number(ni + nj, i + j + 1);
                if (!list.contains(number)) list.add(number);
                if (ni - nj < 0) continue;
                number = new Number(ni - nj, i + j + 1);
                if (!list.contains(number)) list.add(number);
            }
        }
    }

    static private int dfs(int N, int num, int curr, int depth, ArrayList<Number> list){
        if (depth > 8) return Integer.MAX_VALUE;
        if (curr == num) return depth;

        int answer = Integer.MAX_VALUE;

        for (Number number : list){
            answer = Math.min(dfs(N, num, curr + number.value, depth + number.cnt, list), answer);
            answer = Math.min(dfs(N, num, curr - number.value, depth + number.cnt, list), answer);
            answer = Math.min(dfs(N, num, curr * number.value, depth + number.cnt, list), answer);
            if (number.value != 0) answer = Math.min(dfs(N, num, curr / number.value, depth + number.cnt, list), answer);
        }
        return answer;
    }

    static class Number{
        int value, cnt;

        Number(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Number)) return false;
            Number number = (Number)obj;
            return this.value == number.value && this.cnt == number.cnt;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "value=" + value +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
