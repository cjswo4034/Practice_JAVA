package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _10473 {
	static int n;
	static double dist[];
	static Point start, end;
	static ArrayList<Point> list[];
	
	public static void main(String[] args) throws IOException{
		init();
		
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.add(list[0].get(0));
		dist[0] = 0;
		
		while(!q.isEmpty()) {
			int curr = (int)q.poll().x;
			
			for(Point p : list[curr]) {
				if(dist[(int)p.x] > dist[curr] + p.w) {
					dist[(int)p.x] = dist[curr] + p.w;
					q.add(new Point(p.x, p.y, dist[(int)p.x]));
				}
			}
		}
		
		System.out.printf("%.6f", dist[dist.length - 1]);
	}
	
	static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		start = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		end = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		n = Integer.parseInt(br.readLine());

		dist = new double[n + 2];
		list = new ArrayList[n + 2];
		for(int i = 0 ; i < list.length; i++)
			list[i] = new ArrayList<>();
		Arrays.fill(dist, 1000000000);
		
		Point tmp[] = new Point[n + 2];
		tmp[0] = start;
		for(int i = 1 ; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			tmp[i] = new Point(Double.parseDouble(st.nextToken()),
					Double.parseDouble(st.nextToken()));
		}
		tmp[tmp.length-1] = end;
		
		for(int i = 0 ; i < n + 2; i++) {
			for(int j = 0 ; j < n + 2; j++) {
				if(i == 0)
					list[i].add(new Point(i, j, distance(tmp[i], tmp[j]) / 5));
				else 
					list[i].add(new Point(i, j,
							Math.min(2 + distance2(tmp[i], tmp[j]) / 5,
									distance(tmp[i], tmp[j]) / 5)));
			}
		}
	}
	
	static double distance(Point p1, Point p2) {
		double dist = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
		
		return dist;
	}
	
	static double distance2(Point p1, Point p2) {
		double dist = Math.abs(Math.sqrt(Math.pow(p1.x - p2.x, 2)
				+ Math.pow(p1.y - p2.y, 2)) - 50);
		
		return dist;
	}
	
	static class Point implements Comparable<Point>{
		double x, y, w;
		
		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
			this.w = 0;
		}
		
		public Point(Point p, double w) {
			super();
			this.x = p.x;
			this.y = p.y;
			this.w = w;
		}
		
		public Point(double x, double y, double w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}


		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.w <= o.w ? -1 : 1;
		}
	}
}