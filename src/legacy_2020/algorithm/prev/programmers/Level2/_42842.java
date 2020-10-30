package algorithm.prev.programmers.Level2;

import java.util.Stack;

public class _42842 {
    /*
    int a = (brown+4)/2;
    int b = red+brown;
    int[] answer = {(int)(a+Math.sqrt(a*a-4*b))/2,(int)(a-Math.sqrt(a*a-4*b))/2};
        return answer;

    a는 완성된 카펫의 가로+세로입니다. b는 주어진 타일의 개수를 모두 합친 값입니다.
    궁극적으로 answer 안에서 값을 구하는 방법을 기하학 적으로 보자면
    우선 카펫의 가로 + 세로, 즉 a를 한변으로 하는 정사각형을 만든 후,
    카펫모양으로 정사각형의 네 귀퉁이를 잘라내면,
    즉 - 4 * b를 하면 가운데에 정사각형 형태의 타일이 남습니다.
    루트를 이용하여 그 정사각형의 한 변의 길이를 구해주는데
    그 한 변의 길이가 완성된 카펫의 가로와 세로의 길이 차이가 됩니다.
    그러므로 더 긴쪽, 즉 가로를 구하기 위해선 가로와 새로를 더한 값에 차이를 더해주고 2로 나누고,
    짧은 쪽을 구하기 위해선 빼고 2로 나눠주면 됩니다. 그림으로 그려보면 이해가 더 쉬운것 같네요
    */

    public static void main(String[] args) {
        System.out.println(solution(24, 24));
    }

    public static int[] solution(int brown, int red){
        int[] answer = new int[2];
        Stack<Pair> stack = new Stack<>();
        int sum = brown + red;
        for (int i = 3; i < sum; i++) {
            for (int j = 3; i * j <= sum; j++) {
                if (j > i) break;
                if (i * j == sum) stack.push(new Pair(j, i));
            }
        }

        for(Pair p : stack){
            int col = p.col;
            int row = p.row;
            if (check(col, row, brown)) answer = new int[]{col, row};
        }

        return answer;
    }

    private static boolean check(int col, int row, int brown){
        return (col * 2 + (row - 2) * 2) == brown;
    }

    static class Pair{
        int row, col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}
