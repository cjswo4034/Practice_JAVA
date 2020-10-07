package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 압축 {
    public static void main(String[] args) {
        압축 prob = new 압축();
        System.out.println(Arrays.toString(prob.solution("TOBEORNOTTOBEORTOBEORNOT")));
    }

    public int[] solution(String msg){
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> map = init();
        for (int i = 0, j, curr = 27; i < msg.length(); ) {
            j = getIndex(map, msg, i);
            answer.add(map.get(msg.substring(i, j)));
            if (j >= msg.length()) break;

            map.put(msg.substring(i, j + 1), curr++);
            i = j;
        }

        return answer.stream()
                .mapToInt(value -> value)
                .toArray();
    }

    private int getIndex(HashMap<String, Integer> map, String msg, int from){
        int idx;
        for (idx = msg.length(); idx > from; idx--)
            if (map.containsKey(msg.substring(from, idx))) break;
        return idx;
    }

    private HashMap<String, Integer> init(){
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 65; i <= 90; i++) map.put(Character.toString((char)i), i - 64);
        return map;
    }
}
