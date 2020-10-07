package algorithm.prev.programmers.Level3;

public class _1831 {
    int n, result;
    public static void main(String[] args) {
        _1831 prob = new _1831();
        System.out.println(prob.solution(15));
        System.out.println(prob.solution(24));
        System.out.println(prob.solution(41));
        System.out.println(prob.solution(2147483647));
    }
    public int solution(int n) {
        this.n = n;
        result = 0;
        dfs(3, 1, 0);

        return this.result;
    }

    // 1. 위에서 아래로 변경경
    // 2. 길이를 먼저 파악 후 범위 내에서 탐색

   private void dfs(long curr, int multi, int plus){
        System.out.printf("%d : [%d] + [%d]\n", curr, multi, plus);
        if (n < curr || multi * 2 < plus) return;
        if (n == curr && multi * 2 == plus){
            System.out.println(curr + " : " + multi + " : " + plus);
            result++;
            return;
        }

        dfs(curr * 3, multi + 1, plus);
        dfs(curr + 1, multi, plus + 1);
    }
}