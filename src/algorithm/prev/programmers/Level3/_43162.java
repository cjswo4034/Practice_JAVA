package algorithm.prev.programmers.Level3;

import java.util.LinkedList;
import java.util.Queue;

public class _43162 {

    public static void main(String[] args) {
        System.out.println(solution(2, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(solution(2, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }

    public static int solution(int n, int[][] computers) {
        Math.max(1, 2);
        int answer = 0;
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[i].length; j++) {
                if (computers[i][j] == 1){
                    computers[i][j] = 0;
                    bfs(computers, i);
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void dfs(int[][] computers, int n){
        if (n >= computers.length) return;

        for (int i = 0; i < computers[n].length; i++) {
            if (computers[n][i] == 1){
                computers[n][i] = 0;
                dfs(computers, i);
            }
        }
    }

    public static void bfs(int[][] computers, int n){
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        while(!q.isEmpty()){
            int curr = q.poll();
            for (int i = 0; i < computers[curr].length; i++) {
                if (computers[curr][i] == 1){
                    computers[curr][i] = 0;
                    q.add(i);
                }
            }
        }
    }
}