package algorithm.prev.programmers.Level2;

/*
*  1) 순열을 구한다.
*  2) 모든 순열에 대해 조건을 대입해본다.
* */

import java.util.ArrayList;
import java.util.List;

public class _1835 {
    public static void main(String[] args) {
        List<String> permutations = new ArrayList<>();
        permutation(permutations,"", "ACFJMNRT");
        System.out.println(permutations.size());
        System.out.println(solution(2, new String[]{"N~F=0", "R~T>2"}));
    }

    private static void permutation(List<String> list, String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            list.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(list,prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    public static int solution(int n, String[] data) {
        int answer = 0;
        char[] chars = "ACFJMNRT".toCharArray();

        do {
            boolean flag = true;
            for (String condition : data){
                if (!condition(condition, chars)) flag = false;
            }
            if (flag) answer++;
        } while(permutation(chars));

        return answer;
    }

    /*
    * 1) l과 r의 위치를 구한다.
    * 2) 거리를 구한다. (위치 차이의 절대값 - 1)
    * 3) 조건에 맞는지 확인한다.
    * */
    private static boolean condition(String condition, char[] chars){
        int l = getPosition(condition.charAt(0), chars);
        int r = getPosition(condition.charAt(2), chars);

        int dist = Math.abs(l - r) - 1;

        switch (condition.charAt(3)){
            case '<': return dist < (condition.charAt(4) - '0');
            case '>': return dist > (condition.charAt(4) - '0');
            case '=': return dist == (condition.charAt(4) - '0');
        }

        return false;
    }

    private static int getPosition(char target, char[] chars){
        int pos = 0;
        for (char ch : chars) {
            if (ch == target) break;
            pos++;
        }
        return pos;
    }

    /* Permutation
    * 1) 오른쪽부터 <인 L의 위치를 구한다.
    * 2) L의 우측에 있는 값 중 l보다 크고 가장 오른쪽에 있는 R을 구한다.
    * 3) L과 R을 바꾼다.
    * 4) L의 오른쪽에 있는 값들을 뒤집는다.
    * */
    private static boolean permutation(char[] chars){
        int l = chars.length - 1;
        while (l > 0 && chars[l - 1] >= chars[l]) l--;

        if (l <= 0) return false;

        int r = chars.length - 1;
        while (chars[l - 1] >= chars[r]) r--;

        change(chars, l - 1, r);

        r = chars.length - 1;
        while (l < r) {
            change(chars, l, r);
            l++;
            r--;
        }
        return true;
    }

    private static void change(char[] chars, int l, int r){
        char tmp = chars[l];
        chars[l] = chars[r];
        chars[r] = tmp;
    }
}
