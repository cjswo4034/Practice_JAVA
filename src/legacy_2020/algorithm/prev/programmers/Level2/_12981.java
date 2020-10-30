package algorithm.prev.programmers.Level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _12981 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
        System.out.println(Arrays.toString(solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));
    }

    public static int[] solution(int n, String[] input){
        Set<String> set = new HashSet<>();
        set.add(input[0]);
        for (int i = 1; i < input.length; i++) {
            if (input[i - 1].charAt(input[i - 1].length() - 1) != input[i].charAt(0) ||
                    set.contains(input[i])) {
                return new int[]{i % n + 1, i / n + 1};
            }
            set.add(input[i]);
        }

        return new int[]{0, 0};
    }
}
