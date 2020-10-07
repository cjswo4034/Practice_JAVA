package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA_11437 {
	static StringTokenizer st;
	static ArrayList<Integer>[] v;
	static int[][] p;
	static int[] depth; 
	static boolean[] visit;
	static final int LOGN = 16;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		v = new ArrayList[N+1];
		p = new int[N+1][LOGN + 1];
		depth = new int[N+1];
		visit = new boolean[N+1];
		for(int i = 1; i <= N; ++i) v[i] = new ArrayList<Integer>();
		int a, b, c;
		for(int i = 0; i < N-1; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			v[a].add(b);
			v[b].add(a);
		}
		
		dfs(1);
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; ++i){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = lca(a, b);
			bw.write(c+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int cur){
		if(visit[cur]) return;
		visit[cur] = true;
		for(int next : v[cur]) {
			if(visit[next]) continue;
			depth[next] = depth[cur] + 1;
			p[next][0] = cur; 
			
			for(int k = 1; k <= LOGN; ++k)
				p[next][k] = p[ p[next][k-1]][k-1];
			
			dfs(next);
		}
	}
	
	
	private static int lca(int a, int b) {
		if(depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		for(int i=LOGN; i>=0; i--) {
			if((1 << i) <= (depth[a] - depth[b])) {
				a = p[a][i];
			}
		}
		
		if(a==b) return a;
		
		for(int i = LOGN; i >= 0; i--) {
			if(p[a][i] != p[b][i]) {
				a = p[a][i];
				b = p[b][i];
			}
		}
		
		return p[a][0];
	}
}
