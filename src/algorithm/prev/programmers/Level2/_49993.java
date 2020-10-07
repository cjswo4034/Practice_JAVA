package algorithm.prev.programmers.Level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _49993{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            String input = br.readLine();
            String[] tree = br.readLine().split(" ");
            System.out.println("#" + i + " : " + solution(input, tree));
        }
    }

    public static int solution(String skill, String[] skill_trees) {
        int result = 0;
        for (int i = 0; i < skill_trees.length; i++) {
            if (solution(skill.toCharArray(), skill_trees[i])){
                result++;
            }
        }
        return result;
    }

    public static boolean solution(char[] skill, String tree) {
        int[] check = new int[skill.length + 1];
        Arrays.fill(check, -1);

        for (int i = 1; i <= skill.length; i++) {
            for (int j = 0; j < tree.length(); j++) {
                if (skill[i - 1] == tree.charAt(j)){
                    check[i] = j;
                }
            }
        }

        check[0] = 0;
        for (int i = 0; i < check.length - 1; i++) {
            if (i == check.length - 2 && check[i + 1] == -1) continue;
            if (check[i] > check[i + 1]){
                for (int j = i + 1; j < check.length; j++) {
                    if (check[j] != -1) return false;
                }
            }
        }

        return true;
    }
}