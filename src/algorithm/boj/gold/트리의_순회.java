package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리의_순회 {
	static int n;
	static int[] inPos;
	static int[][] tree;
	
	public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        int[] pre = new int[n];
        int[] in = new int[n];
        int[] post = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) in[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) post[i] = Integer.parseInt(st.nextToken());
        
        inPos = new int[n + 1];
        for (int i = 0; i < n; i++) inPos[in[i]] = i;
        
        Pos pos = new Pos();
        pos.setPre(0, n - 1);
        pos.setIn(0, n - 1);
        pos.setPost(0, n - 1);
        
        solution(pre, in, post, pos);
        
        for (int el: pre) sb.append(el).append(" ");
        System.out.println(sb);
	}
	
	static void solution(int[] pre, int[] in, int[] post, Pos pos) {
		if (!pos.validation()) return;
		
		// 1. post[-1]로 root 구하기
		int root = post[pos.postTo];
		pre[pos.preFrom] = root;
		
		// 2. In에서 1.을 찾기
		int inRootPos = inPos[root];
		int leftSize = inRootPos - pos.inFrom;
		
		// 3. In에서 좌, 우로 나누기
		Pos nextLeftPos = new Pos();
		nextLeftPos.setIn(pos.inFrom, inRootPos - 1);
		nextLeftPos.setPre(pos.preFrom + 1, pos.preFrom + leftSize);
		nextLeftPos.setPost(pos.postFrom, pos.postFrom + leftSize - 1);
		solution(pre, in, post, nextLeftPos);
		
		Pos nextRightPos = new Pos();
		nextRightPos.setIn(inRootPos + 1, pos.inTo);
		nextRightPos.setPre(pos.preFrom + leftSize + 1, pos.preTo);
		nextRightPos.setPost(pos.postFrom + leftSize, pos.postTo - 1);
		solution(pre, in, post, nextRightPos);
	}
	
	static class Pos {
		int preFrom, preTo;
		int inFrom, inTo;
		int postFrom, postTo;
		
		void setPre(int preFrom, int preTo) {
			this.preFrom = preFrom;
			this.preTo = preTo;
		}
		
		void setIn(int inFrom, int inTo) {
			this.inFrom = inFrom;
			this.inTo = inTo;
		}
		
		void setPost(int postFrom, int postTo) {
			this.postFrom = postFrom;
			this.postTo = postTo;
		}
		
		boolean validation() {
			if (preFrom > preTo) return false;
			if (inFrom > inTo) return false;
			if (postFrom > postTo) return false;
			return true;
		}
	}
}
