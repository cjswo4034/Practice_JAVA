package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 소트_1083 {
	public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();
        List<Integer> cache = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0, tmp; i < n; i++) {
			tmp = Integer.parseInt(st.nextToken());
        	arr.add(tmp);
			cache.add(tmp);
		}
        
        int s = Integer.parseInt(br.readLine());

        int chance = s;
        int pos = 0;	// 바꿔야될 원소의 위치
        int posOfMaxValue = 0;
        
        while (chance > 0 && pos < arr.size()) {
        	// 1. 기회 내에서 가장 큰 원소의 위치를 구한다
        	posOfMaxValue = findPosOfMaxValue(arr, pos, chance);
        	if (posOfMaxValue == pos) {
        		pos++;
        		continue;	// 현재 원소가 가장 큰 원소이면 pass
        	}
        	
        	// 2. 1에서 구한 원소의 위치와 현재 기준이 되는 원소의 위치에 삽입한다.
        	insert(arr, posOfMaxValue, pos);
        	
        	// 3. 결과와 비교하여 저장한다.
        	if (moreThan(arr, cache)) {
        		Collections.copy(cache, arr);
        	}
        	
        	chance -= (posOfMaxValue - pos);
        	pos++;
        }
        
        for (int el: cache) {
        	sb.append(el).append(" ");
        }
        
        System.out.println(sb);
	}
	
	static int findPosOfMaxValue(List<Integer> arr, int from, int chance) {
		if (from >= arr.size()) return from;
		
		int res = from;
		int maxValue = arr.get(from);
		for (int i = from + 1; i < arr.size() && i <= from + chance; i++) {
			if (maxValue < arr.get(i)) {
				maxValue = arr.get(i);
				res = i;
			}
		}
		
		return res;
	}
	
	static void insert(List<Integer> arr, int src, int dest) {
		int tmp = arr.get(src);
		arr.remove(arr.indexOf(tmp));
		arr.add(dest, tmp);
	}
	
	static boolean moreThan(List<Integer> arr1, List<Integer> arr2) {
		for (int i = 0; i < arr1.size(); i++) {
			if (arr1.get(i) < arr2.get(i)) return false;
			else if (arr1.get(i) > arr2.get(i)) return true;
		}
		return true;
	}
}
