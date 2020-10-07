package algorithm.prev.programmers.kakaoRecruit_2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class 후보키 {
    public static void main(String[] args) {
        후보키 prob = new 후보키();
        System.out.println(prob.solution(new String[][]{
                {"100","ryan","music","2"},
                {"200","apeach","math","3"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        }));
    }

    public int solution(String[][] relation) {
        ArrayList<Integer> candidates = new ArrayList<>();
        Set<String> set;
        for (int i = 1, len = (1 << relation[0].length); i < len; i++) {
            if (visited(candidates, i)) continue;
            set = new HashSet<>();
            for (String[] strings : relation) set.add(getKey(i, strings));
            if (set.size() == relation.length) candidates.add(i);
        }

        return candidates.size();
    }

    private boolean visited(ArrayList<Integer> visited, int idx){
        for (Integer visit : visited){
            if ((visit & idx) == visit) return true;
        }
        return false;
    }

    private String getKey(int idx, String[] row){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.length && idx > 0; i++) {
            if ((idx & 1) == 1) sb.append(row[i]);
            idx = idx >> 1;
        }
        return sb.toString();
    }
}