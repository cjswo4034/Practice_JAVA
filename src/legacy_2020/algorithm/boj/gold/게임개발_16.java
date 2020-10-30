package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게임개발_16 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        Queue<Building> q = new LinkedList<>();
        Building[] building = new Building[n + 1];
        
        for (int i = 0; i <= n; i++) {
        	building[i] = new Building(i);
        	q.add(building[i]);
        }
        
        for (int i = 1, tmp; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			tmp = Integer.parseInt(st.nextToken());
			building[i].setReqTime(tmp);
			
			while (st.hasMoreTokens()) {
				tmp = Integer.parseInt(st.nextToken());
				if (tmp == -1) break;
				building[i].addReqBuilding(building[tmp]);
			}
		}
        
        while (!q.isEmpty()) {
        	Building curr = q.poll();
        	
        	if (!curr.build()) q.add(curr);
        }
        
        for (int i = 1; i < building.length; i++) {
			System.out.println(building[i].buildTime);
		}
	}
	
	static class Building {
		int num, reqTime, buildTime;
		boolean isBuilded;
		List<Building> req;
		
		public Building(int num) {
			this.num = num;
			this.isBuilded = true;
			this.req = new ArrayList<>();
		}
		
		public void setReqTime(int reqTime) {
			this.reqTime = this.buildTime = reqTime;
		}
		
		public void addReqBuilding(Building b) {
			if (isBuilded) isBuilded = false;
			req.add(b);
		}
		
		public boolean build() {
			// 1. 선행 건물이 지어졌는지 확인
			// 2. 지어졌으면 선행 건물 중 건설된 시간이 가장 큰 건물의 건실 시간 + 현 건물의 건설 시간
						
			int t = 0;
			for (Building b : req) {
				if (!b.isBuilded) return false;
				t = Math.max(t, b.buildTime);
			}
			
			this.buildTime += t;
			this.isBuilded = true;
			
			return true;
		}
		
		public boolean constructible() {
			return req.stream().allMatch(i -> i.isBuilded);
		}

		@Override
		public String toString() {
			return "Building [num=" + num + ", reqTime=" + reqTime + ", buildTime=" + buildTime + ", isBuilded="
					+ isBuilded + ", req=" + req.size() + "]";
		}
	}
}
