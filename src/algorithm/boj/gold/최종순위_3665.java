package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 최종순위_3665 {
	static int[] indegree, before, after;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Queue<Integer> q;
        Queue<Pair> mq;
        int n, m, tmp;
        // 인접한 것을 변경해 나간다.
        // 등수가 바뀐 쌍이 남는다면 불가능
        // 위상 정렬에 사용되는 q가 남는다면 모호함.
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
        	n = Integer.parseInt(br.readLine());
        	indegree = new int[n + 1];
        	before = new int[n + 2];
        	after = new int[n + 2];
        	mq = new LinkedList<>();
        	q = new LinkedList<>();

        	st = new StringTokenizer(br.readLine());
        	tmp = Integer.parseInt(st.nextToken());
        	for (int i = 1, a; i < n; i++) {
				a = Integer.parseInt(st.nextToken());
				indegree[a]++;
				before[a] = tmp;
				after[tmp] = a;
				tmp = a;
			}

        	m = Integer.parseInt(br.readLine());
        	for (int i = 0, a, b; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				mq.add(new Pair(a, b));
			}
        	
        	System.out.println(Arrays.toString(indegree));
        	System.out.println(Arrays.toString(after));
        	System.out.println(Arrays.toString(before));
        	
        	while (!mq.isEmpty()) {
        		int size = mq.size();
        		boolean flag = false;
        		while (size-- > 0) {
	        		Pair p = mq.poll();
	        		if (after[p.a] == p.b) {
	        			changeOrder(p.a, p.b);
	        			flag = true;
	        		} else if (after[p.b] == p.a) {
	        			changeOrder(p.b, p.a);
	        			flag = true;
	        		} else mq.add(p);
        		}
        		if (!mq.isEmpty() && !flag) break;
        	}
        	
        	if (!mq.isEmpty()) {
        		sb.append("IMPOSSIBLE\n");
        		continue;
        	}
        	
        	System.out.println(Arrays.toString(indegree));
        	System.out.println(Arrays.toString(after));
        	System.out.println(Arrays.toString(before));
        	
        	for (int i = 1; i <= n; i++)
				if (indegree[i] == 0) q.add(i);
        	
        	StringBuilder tmpSb = new StringBuilder();
        	for (int i = 0; i < n; i++) {
        		if (q.isEmpty()) break;
				int next = q.poll();
				
				tmpSb.append(next).append(" ");
				
				if (--indegree[after[next]] == 0)
					q.add(after[next]);
			}
        	
        	if (!q.isEmpty()) {
        		sb.append("?\n");
        		continue;
        	}
        	
        	sb.append(tmpSb).append("\n");
        }
        System.out.println(sb);
	}
	
	static void changeOrder(int a, int b) {		
		after[before[a]] = b;
		before[after[b]] = a;
		
		
			after[a] = after[b];
			before[b] = before[a];
		
		after[b] = a;
		before[a] = b;
		
		int tmp = indegree[a];
		indegree[a] = indegree[b];
		indegree[b] = tmp;
	}
	
	static class Pair {
		int a, b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
