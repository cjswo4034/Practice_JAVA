package algorithm.prev.toss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class araw {
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<List<Integer>> arr = new ArrayList<>();
		List<Integer> line = new ArrayList<>();
		String input = br.readLine();

		for (int i = 0; i < input.length(); i++) {
			char tmp = input.charAt(i);
			if (tmp == ';') {
				arr.add(line);
				line = new ArrayList<>();
			} else if (tmp != ' ') {
				line.add(tmp - '0');
			}
		}
		if (line.size() != 0) arr.add(line);
		
		int answer = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(0).size(); j++) {
				if (arr.get(i).get(j) == 1) {
					answer += nearbyTable(arr, i, j);
				}
			}
		}

		System.out.println(answer);
	}

	static int nearbyTable(List<List<Integer>> arr, int row, int col) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int nr = row + dir[i][0];
			int nc = col + dir[i][1];
			if (nr >= arr.size() || nc >= arr.get(0).size() || nr < 0 || nc < 0)
				continue;
			if (arr.get(nr).get(nc) == 0)
				ret++;
		}
		return ret;
	}
}
