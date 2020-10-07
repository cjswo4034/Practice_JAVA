package algorithm.prev.programmers.Level2;

public class _12924 {
    public static void main(String[] args) {
        System.out.println(solution(15));
    }

    public static int solution(int input){
        int answer = 0;
        for (int i = 1; i <= input; i++) {
            System.out.println("<" + i + ">");
            if (isAble(i, input)) answer++;
        }
        return answer;
    }

    private static boolean isAble(int num, int input){
        int sum = 0;
        for (int i = num; i <= input ; i++) {
            sum += i;
            System.out.println(i + " " + sum);
            if (sum == input) return true;
            else if (sum > input) return false;
        }
        return false;
    }
}
