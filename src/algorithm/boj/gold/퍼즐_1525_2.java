package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 퍼즐_1525_2 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder start = new StringBuilder();
        StringTokenizer st;
        
        for (int i = 0; i < 3; i++) {
        	st = new StringTokenizer(br.readLine());
        	start.append(st.nextToken()).append(st.nextToken()).append(st.nextToken());
		}
        
        System.out.println(bfs(start.toString(), "123456780"));
	}
	
	static int bfs(String start, String end) {
		Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int res = 0;
        
        q.add(start);
        set.add(start);
        
        String curr, next;
        while (!q.isEmpty()) {
        	int size = q.size();
        	while (size-- > 0) {
        		curr = q.poll();
        		
            	if (curr.equals(end)) return res;
            	
        		int pos = curr.indexOf("0");
            	for (int i = 0; i < 4; i++) {
            		next = replaceZero(curr, pos, i);
            		
            		if (next.equals("")) continue;
            		if (set.contains(next)) continue;
            		set.add(next);
            		q.add(next);
    			}
        	}
        	res++;
        }
        
        return -1;
	}
	
	// dir: [0: 위, 1: 아래, 2: 오른쪽, 3:왼쪽
	static String replaceZero(String str, int pos, int dir) {
		int dest = -1;
		if (dir == 0 && pos > 2) dest = pos - 3;
		else if (dir == 1 && pos < 6) dest = pos + 3;
		else if (dir == 2 && pos % 3 != 2) dest = pos + 1;
		else if (dir == 3 && pos % 3 != 0) dest = pos - 1;
		
		if (dest == -1) return "";
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(pos, str.charAt(dest));
		sb.setCharAt(dest, '0');
		return sb.toString();
	}
}
