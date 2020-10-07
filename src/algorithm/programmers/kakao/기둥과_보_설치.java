package algorithm.programmers.kakao;

import java.util.ArrayList;
import java.util.List;

public class 기둥과_보_설치 {
	public static void main(String[] args) {
		System.out.println(1 ^ 1);
		System.out.println(0 ^ 1);
	}

    public int[][] solution(int n, int[][] build_frame) {
        boolean[][][] map = new boolean[n + 1][n + 1][2];

        for (int i = 0; i < build_frame.length; i++) {
			int row = build_frame[i][1];
			int col = build_frame[i][0];
			boolean isPilar = build_frame[i][2] == 0;
			boolean isInstall = build_frame[i][3] == 1;
			
			// 1. 구조물 설치	
			build(map, isPilar, isInstall, row, col);
			
	        // 2. 검증
			if (!validate(map, row, col)) {
				build(map, isPilar, !isInstall, row, col);
			}
			
		}
        
        List<int[]> answer = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j][0]) answer.add(new int[] {j, i, 0});
				if (map[i][j][1]) answer.add(new int[] {j, i, 1});
			}
		}
        
        return (int[][]) answer.toArray(new int[answer.size()][3]);
    }
    
    void display(boolean[][] map, boolean isFrame) {
    	for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j]) System.out.print(isFrame ? 2 : 1);
				else System.out.println(0);
			}
			System.out.println();
		}
    }
    
    void build(boolean[][][] map, boolean isPilar, boolean isInstall, int row, int col) {
    	if (isInstall) map[row][col][isPilar ? 0 : 1] = true;
    	else map[row][col][isPilar ? 0 : 1] = false;
    }
    
    boolean validate(boolean[][][] map, int row, int col) {
    	for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map.length; c++) {
				if (map[r][c][1] && !vaildateCmd(map, true, row, col)) return false;
				if (map[r][c][0] && !vaildateCmd(map, false, row, col)) return false;
			}
		}
    	return true;
    }
    
    boolean vaildateCmd(boolean[][][] map, boolean isPilar, int row, int col) {
    	if (isPilar) {
    		if (row == 0) return true;
        	if (row > 0 && map[row - 1][col][0]) return true;
        	if (map[row][col][1] || (col > 0 && map[row][col - 1][1])) return true;
    	} else {
    		if (row > 0 && (map[row - 1][col][0] || map[row - 1][col + 1][0])) return true;
        	if (col > 0 && map[row][col - 1][1] && map[row][col + 1][1]) return true;
    	}
    	return false;
    }
    
    // 기둥(1)은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
    boolean validatePilar(boolean[][] pilar, boolean[][] frame, int row, int col) {
    	if (row == 0) return true;
    	if (row > 0 && pilar[row - 1][col]) return true;
    	if (frame[row][col] || (col > 0 && frame[row][col - 1])) return true;
    	return false;
    }
    
    // 보(2)는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    boolean validateFrame(boolean[][] pilar, boolean[][] frame, int row, int col) {
    	if (row > 0 && (pilar[row - 1][col] || pilar[row - 1][col + 1])) return true;
    	if (col > 0 && frame[row][col - 1] && frame[row][col + 1]) return true;
    	return false;
    }
}
