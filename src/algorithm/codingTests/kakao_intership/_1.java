package algorithm.codingTests.kakao_intership;

import java.util.LinkedList;
import java.util.Queue;

public class _1 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}}));
	}
	
    public static int solution(int[][] board) {
        int[][][] costs = new int[board.length][board.length][2];
        int[][] direction = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < costs.length; i++) {
			for (int j = 0; j < costs.length; j++) {
				costs[i][j][0] = costs[i][j][1] = 987654321;
			}
		}
        costs[0][0][0] = costs[0][0][1] = 0;
        q.add(new int[] {0, 0, 0});
        q.add(new int[] {0, 0, 1});
        
        while (!q.isEmpty()) {
        	int[] current = q.poll();
        	for (int i = 0; i < direction.length; i++) {
        		int nRow = current[0] + direction[i][0];
        		int nCol = current[1] + direction[i][1];
        		if (nRow < 0 || nCol < 0 || nRow >= board.length || nCol >= board.length || board[nRow][nCol] == 1) continue;
        		
        		int nCost = costs[current[0]][current[1]][current[2]] + 100;
        		if (current[2] == 1) nCost += i / 2 == 0 ? 500 : 0; 
        		else nCost += i / 2 == 1 ? 500 : 0;
        		
        		if (nCost > costs[nRow][nCol][i / 2]) continue;
        		
        		costs[nRow][nCol][i / 2] = nCost;
        		q.add(new int[] {nRow, nCol, i / 2});
			}
        }

        return Math.min(costs[board.length - 1][board.length - 1][1], costs[board.length - 1][board.length - 1][0]);
    }
}
