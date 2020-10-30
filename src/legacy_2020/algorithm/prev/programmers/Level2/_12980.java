package algorithm.prev.programmers.Level2;

public class _12980 {

    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(6));
        System.out.println(solution(2000));
        System.out.println(solution(5000));
    }

    public static int solution(int n){
        int answer = 0;
        while (n > 0){
            if (n % 2 == 1) {
                n--;
                answer++;
            }
            n /= 2;
        }
        return answer;
    }
}
