package algorithm.programmers.kakao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 튜플 {

    public int[] solution(String input) {
    	Set<Integer> set = new HashSet<>();
    	String[] arr = input.replaceAll("\\{", " ").replaceAll("\\}", " ").trim().split(" , ");
        
    	Arrays.parallelSort(arr, (a, b) -> a.length() - b.length());
    	
        int[] answer = new int[arr.length];
        int i = 0;
        
        for (String str: arr) {
        	for (String s : str.split(",")) {
        		int value = Integer.parseInt(s);
        		if (set.contains(value)) continue;
        		set.add(value);
        		answer[i++] = value;
        	}
        }
        
        return answer;
    }
}
