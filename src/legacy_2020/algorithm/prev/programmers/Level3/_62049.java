package algorithm.prev.programmers.Level3;

public class _62049 {
    public static void main(String[] args) {
        solution(4);
    }

    public static int[] solution(int n){
        int[] arr = new int[(1 << n) - 1];
        recursion(arr, n);
        return arr;
    }

    private static void recursion(int[] arr, int n){
        if (n == 1){
            arr[0] = 0;
            return;
        }

        recursion(arr, n - 1);

        for (int i = 0, len = (1 << n) - 1; i < len / 2; i++)
            arr[len - i - 1] = arr[i] ^ 1;
    }
}
