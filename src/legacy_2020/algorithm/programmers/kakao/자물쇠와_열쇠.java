package algorithm.programmers.kakao;

public class 자물쇠와_열쇠 {
    // 1. key를 최소한의 크기로 자른다. (1 포함)
    // 2. lock을 최소한의 크기로 자른다. (0 포함)
    // 3. 2.가 1.에 포함될 수 있는지 확인한다.
    // 4. 2.를 1.의 원소와 더한다.
    //    a. 모든 원소가 1인지 확인한다.
    //    b. 2.를 90도로 회전한다.
    //    c. 3.으로 돌아간다.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [][] key = {{1, 1}, {1, 0}};
		int [][] lock = {{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 0}};
		
		자물쇠와_열쇠 prob = new 자물쇠와_열쇠();
		System.out.println(prob.solution(key, lock));
	}
	
    public boolean solution(int[][] key, int[][] lock) {
    	if (check(lock, 1)) return true;
    	if (check(key, 0)) return false;

        key = split(key, 1);
        lock = split(lock, 0);
        
        for (int i = 0; i < 4; i++) {
        	if (!isIn(key, lock)) continue;
        	if (isOpenable(key, lock)) return true;
        	
			lock = rotate(lock);
		}
        
        return false;
    }
    
    // array를 value를 포함한 배열로 자른다.
    private int[][] split(int[][] arr, int value) {
    	int minR, minC, maxR, maxC;
    	minR = arr.length;
    	minC = arr[0].length;
    	maxR = maxC = 0;
    	
    	for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == value) {
					minR = Math.min(minR, i);
					minC = Math.min(minC, j);
					maxR = Math.max(maxR, i);
					maxC = Math.max(maxC, j);
				}
			}
		}
    	
    	int[][] res = new int[maxR - minR + 1][maxC - minC + 1];
    	for (int i = minR; i <= maxR; i++) {
			for (int j = minC; j <= maxC; j++) {
				res[i - minR][j - minC] = arr[i][j]; 
			}
		}
    	
    	return res;
    }
    
    // lock이 key에 포함될 수 있는지 확인한다.
    private boolean isIn(int[][] key, int[][] lock) {
    	return key.length >= lock.length && key[0].length >= lock[0].length;
    }
    
    // 배열을 회전한다.
    private int[][] rotate(int[][] arr) {
    	int r = arr.length, c = arr[0].length;
    	int[][] res = new int[c][r];
    	
    	for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				res[i][j] = arr[r - j - 1][i];
			}
		}

    	return res;
    }
    
    // 두 배열을 더한다. (slide window)
    private boolean isOpenable(int[][] key, int[][] lock) {
    	int rTo = key.length - lock.length;
    	int cTo = key[0].length - lock[0].length;

    	for (int r = 0; r <= rTo; r++) {
			for (int c = 0; c <= cTo; c++) {
				boolean flag = true;
				
				TO: for (int i = 0; i < lock.length; i++) {
					for (int j = 0; j < lock[0].length; j++) {
						if ((key[r + i][c + j] ^ lock[i][j]) != 1) {
							flag = false;
							break TO;
						}
					}
				}
				
				if (flag) {
					System.out.println(r + " : " + c);
					return flag;
				}
			}
		}
    	
    	return false;
    }
    
    
    
    // 전부 1인지 검사한다.
    private boolean check(int[][] arr, int value) {
    	for (int[] row : arr) {
    		for (int col : row) {
    			if (col != value) return false;
    		}
    	}
    	return true;
    }
}
