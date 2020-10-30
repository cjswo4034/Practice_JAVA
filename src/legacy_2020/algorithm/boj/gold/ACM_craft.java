package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// Gold 3
public class ACM_craft {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Queue<Building> q;
		Building[] buildings;
		
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			q = new LinkedList<>();
			buildings = new Building[n + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
				q.add(buildings[i]);
			}
			
			for (int i = 0, from, to; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				buildings[from].addNext(buildings[to]);
				buildings[to].addNeed(buildings[from]);
			}
			
			int target = Integer.parseInt(br.readLine());
			
			Building tmp;
			while (!q.isEmpty()) {
				tmp = q.poll();
				
				if (!tmp.build()) q.add(tmp);
				else if (tmp.number == target) {
					System.out.println(tmp.currentTime);
					break;
				}
			}
		}
	}
	
	static class Building {
		List<Building> needs, next;
		int number;
		int currentTime, buildTime;
		boolean isSucess;
		
		Building(int number, int buildTime) {
			this.number = number;
			this.buildTime = buildTime;
			
			this.needs = new ArrayList<>();
			this.next = new ArrayList<>();
		}
		
		void addNext(Building building) {
			this.next.add(building);
		}
		
		void addNeed(Building building) {
			this.needs.add(building);
		}
		
		boolean build() {
			int temp = 0;
			for (Building building: needs) {
				if (!building.isSucess) return false;
				temp = Math.max(temp, building.currentTime);
			}
			
			this.currentTime = temp + this.buildTime;
			
			afterBuildSucess();
			return this.isSucess = true;
		}
		
		void afterBuildSucess() {
			for (Building building: next) {
				building.build();
			}
		}
		
		@Override
		public String toString() {
			return "Building No." + this.number;
		}
	}
}
