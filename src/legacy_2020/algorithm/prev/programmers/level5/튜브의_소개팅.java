package algorithm.prev.programmers.level5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 튜브의_소개팅 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] arr = {{0, 1}, {100, 2}, {5, 3}, {1, -1}, {1, 0}};
		// new int[][]{{0, -1, -1}, {100, 100, 4}, {1, 2, 0}}
		System.out.println(Arrays.toString(sol.solution(5, 2, 106, arr)));
	}
}

class Solution {
    public int[] solution(int m, int n, int s, int[][] time_map) {      
    	int stepLimit = m * n + 1;
        int[] answer = {251, Integer.MAX_VALUE};
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        long[][][] visitedTime = new long[m][n][stepLimit];
        
        Queue<Node> q = new LinkedList<>();
        
        for (int i = 0; i < visitedTime.length; i++) {
        	for (int j = 0; j < visitedTime[0].length; j++) {
        		Arrays.fill(visitedTime[i][j], Long.MAX_VALUE);	
			}
		}
        
        visitedTime[0][0][0] = 0;
        q.add(new Node(0, 0, 0, 0));
        
        while (!q.isEmpty()) {
        	Node current = q.poll();
        	
        	for (int[] d: direction) {
				int nRow = d[0] + current.row;
				int nCol = d[1] + current.col;
				
				if (nRow < 0 || nCol < 0 || nRow >= m || nCol >= n || time_map[nRow][nCol] < 0) continue;
				
				int nextCount = current.count + 1;
				long nextTime = current.time + time_map[nRow][nCol];
				
				if (current.time + time_map[nRow][nCol] > s || nextCount >= stepLimit || nextTime >= visitedTime[nRow][nCol][nextCount]) continue;
				
				q.add(new Node(nextTime, nextCount, nRow, nCol));
				visitedTime[nRow][nCol][nextCount] = nextTime;
			}
        }
        
        for (int i = 0; i < stepLimit; i++) {
			if (visitedTime[m - 1][n - 1][i] != Long.MAX_VALUE) {
				answer[0] = i;
				answer[1] = (int) visitedTime[m - 1][n - 1][i];
				return answer;
			}
		}
        return answer;
    }
    
    class Node {
    	long time;
    	int count, row, col;
		public Node(long time, int count, int row, int col) {
			this.time = time;
			this.count = count;
			this.row = row;
			this.col = col;
		}
    }
}