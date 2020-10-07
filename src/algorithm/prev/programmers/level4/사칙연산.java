package algorithm.prev.programmers.level4;

import java.util.Arrays;

public class 사칙연산 {
	
	class Solution {
	    public int solution(String input[]) {
	    	int len = input.length / 2 + 1;
	    	
	        int[][] dpMax = new int[len][len];
	        int[][] dpMin = new int[len][len];
	        
	        for (int i = 0; i < len; i++) {
	        	Arrays.fill(dpMax[i], Integer.MIN_VALUE);
	        	Arrays.fill(dpMin[i], Integer.MAX_VALUE);
	        	dpMax[i][i] = dpMin[i][i] = Integer.parseInt(input[i * 2]);
	        }
	        
	        for (int i = 1; i < len; i++) {
				for (int j = 0; j + i < len; j++) {
	                int temp = i + j;
					for (int k = j; k < i + j; k++) {
						if (input[k * 2 + 1].equals("+")) {
							dpMax[j][temp] = Math.max(dpMax[j][temp], dpMax[j][k] + dpMax[k + 1][temp]);
							dpMin[j][temp] = Math.min(dpMin[j][temp], dpMin[j][k] + dpMin[k + 1][temp]);
						} else if (input[k * 2 + 1].equals("-")) {
							dpMax[j][temp] = Math.max(dpMax[j][temp], dpMax[j][k] - dpMin[k + 1][temp]);
							dpMin[j][temp] = Math.min(dpMin[j][temp], dpMin[j][k] - dpMax[k + 1][temp]);
						}
					}
				}
			}
	        return dpMax[0][len - 1];
	    }
	}
}
