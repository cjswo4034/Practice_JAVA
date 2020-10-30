package algorithm.prev.programmers.Level2;

public class _12913 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}}));
    }

    public static int solution(int[][] input){
        int answer = 0;
        for (int i = input.length - 2; i >= 0 ; i--) {
            input[i][0] += Math.max(input[i + 1][1], Math.max(input[i + 1][2], input[i + 1][3]));
            input[i][1] += Math.max(input[i + 1][0], Math.max(input[i + 1][2], input[i + 1][3]));
            input[i][2] += Math.max(input[i + 1][0], Math.max(input[i + 1][1], input[i + 1][3]));
            input[i][3] += Math.max(input[i + 1][0], Math.max(input[i + 1][1], input[i + 1][2]));
        }
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, input[0][i]);
        }
        return answer;
    }
}
