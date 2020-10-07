package algorithm.prev.programmers.Level2;

public class _12977 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 4}));
        System.out.println(solution(new int[]{1, 2, 7, 6, 4}));
    }

    public static int solution(int[] input){
        int answer = 0;
        boolean[] primes = primes();

        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    if (!primes[input[i] + input[j] + input[k]]) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    private static boolean[] primes(){
        boolean[] primes = new boolean[3000];
        primes[0] = primes[1] = true;
        for (int i = 2; i < 3000; i++) {
            if (!primes[i]) {
                for (int j = i * 2; j < 3000; j += i) {
                    primes[j] = true;
                }
            }
        }
        return primes;
    }

    private static boolean dfs(int[] inputs, int depth, int pos, int sum){
        if (depth == 3){
            return true;
        } else {
            for (int i = pos + 1; i < inputs.length; i++) {
                dfs(inputs, depth + 1, i, sum + inputs[i]);
            }
            return false;
        }
    }
}
