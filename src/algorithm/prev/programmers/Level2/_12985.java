package algorithm.prev.programmers.Level2;

public class _12985 {
    public static void main(String[] args) {
        System.out.println(solution(8, 4, 7));
    }

    public static int solution(int n, int a, int b){
        int ans = 0;
        while (a != b){
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            ans++;
        }
        return ans;
    }
}
