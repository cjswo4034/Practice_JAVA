package algorithm.prev.programmers.Level2;

public class _43165 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public static int solution(int[] numbers, int target){
        return dfs(numbers, 0, 0, target);
    }

    private static int dfs(int [] numbers, int sum, int depth, int target){
        if (depth >= numbers.length) {
            if (sum == target) return 1;
            return 0;
        }

        return dfs(numbers,sum + numbers[depth],depth + 1, target) +
                dfs(numbers,sum - numbers[depth],depth + 1, target);
    }
}
