package algorithm.prev.programmers.Level3;

import java.util.Arrays;

public class _12936 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, 2*3)));
        System.out.println(Arrays.toString(solution(5, 2*3*4*5)));
        System.out.println(Arrays.toString(solution(7, 2*3*4*5*6*7)));
        System.out.println(Arrays.toString(solution(9, 2*3*4*5*6*7*8*9)));
        System.out.println(Arrays.toString(solution(11, 2*3*4*5*6*7*8*9*10*11)));
        System.out.println(Arrays.toString(solution(13, 2L*3*4*5*6*7*8*9*10*11*12*13)));
        System.out.println(Arrays.toString(solution(15, 2L*3*4*5*6*7*8*9*10*11*12*13*14*15)));
        System.out.println(Arrays.toString(solution(17, 2L*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17)));
        System.out.println(Arrays.toString(solution(19, 2L*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19)));
        System.out.println(Arrays.toString(solution(20, 2L*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19*20)));
        /*for (long i = 113261346111326134l; i <= 113261346111326135l; i++) { 11326134634l
            System.out.println(Arrays.toString(solution(20, i)));
        }*/
    }

    public static int[] solution(int n, long k) {
        long[] arr = init();
        boolean[] check = new boolean[n + 1];

        String[] result = solve(new StringBuilder(), check, arr, n, k - 1).toString().split(" ");
        int[] answer = new int[result.length];
        for (int i = 0; i < result.length; i++) {
            answer[i] = Integer.parseInt(result[i]);
        }

        return answer;
    }

    private static StringBuilder solve(StringBuilder sb, boolean[] check, long[] arr, int n, long k){
        if (n == 0) return sb;

        long div = k / arr[n - 1];
        long remain = k - arr[n - 1] * div;

        // System.out.println(k + " / " + arr[n - 1] + " = " + div + " (" + n + ")");

        for (int i = 1; ; i++) {
            if (!check[i]) div--;
            if (div + 1 == 0){
                check[i] = true;
                sb.append(i).append(" ");
                break;
            }
        }

        solve(sb, check, arr, n - 1, remain);
        return sb;
    }

    private static long[] init(){
        long[] arr = new long[21];
        arr[0] = arr[1] = 1;
        for (int i = 2; i < 21; i++) {
            arr[i] = arr[i - 1] * i;
        }
        return arr;
    }
}