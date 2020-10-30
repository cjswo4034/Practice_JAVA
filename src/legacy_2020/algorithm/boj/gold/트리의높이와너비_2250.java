package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의높이와너비_2250 {
	static final int INF = 987654321;
	static int idx = 1, maxLv = 1;
	static int[][] tree;
	static List<Integer>[] lv;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int root = n * (n + 1) / 2;
        tree = new int[n + 1][2];
        lv = new ArrayList[n + 1];
        
        for (int i = 1, idx, l, r; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			idx = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			if (l != -1) root -= l;
			if (r != -1) root -= r;
			tree[idx][0] = l;
			tree[idx][1] = r;
			lv[i] = new ArrayList<>();
		}

        int level = 1, idx = 0, max = 0, res;
        System.out.println(root);
        order(root, level);

        // 해당 레벨의 가장 오른쪽 노드 idx - 왼쪽 노드 idx + 1 
        while (level <= maxLv) {
        	res = lv[level].get(lv[level].size() - 1) - lv[level].get(0) + 1;
        	
        	if (max < res) {
        		idx = level;
        		max = res;
        	}
        	level++;
        }
        
        System.out.println(idx + " " + max);
	}
	
	static void order(int i, int level) {
		if (i == -1) return;
		maxLv = Math.max(maxLv, level);
		
		order(tree[i][0], level + 1);
		lv[level].add(idx++);
		order(tree[i][1], level + 1);
	}
}
