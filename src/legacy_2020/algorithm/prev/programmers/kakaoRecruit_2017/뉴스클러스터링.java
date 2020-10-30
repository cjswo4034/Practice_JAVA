package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.*;

public class 뉴스클러스터링 {
    public static void main(String[] args) {
        뉴스클러스터링 prob = new 뉴스클러스터링();
        System.out.println(prob.solution("FRANCE", "french"));
        System.out.println(prob.solution("handshake", "shake hands"));
        System.out.println(prob.solution("aa1+aa2", "AAAA12"));
        System.out.println(prob.solution("E=M*C^2", "e=m*c^2"));
        System.out.println(prob.solution("AABBC", "AABBDE"));
    }

    public int solution(String str1, String str2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        separateString(str1, map1);
        separateString(str2, map2);

        int intersection = 0, union = 0;
        for (Map.Entry<String, Integer> entry : ((HashMap<String, Integer>)map1.clone()).entrySet()){
            if (map2.containsKey(entry.getKey())){
                union += Math.max(entry.getValue(), map2.get(entry.getKey()));
                intersection += Math.min(entry.getValue(), map2.get(entry.getKey()));
                map1.remove(entry.getKey());
                map2.remove(entry.getKey());
            } else {
                union += entry.getValue();
                map1.remove(entry.getKey());
            }
        }

        union += map2.values().stream().reduce(0, Integer::sum);

        if (intersection + union == 0) return 65536;
        else return (int)((double)intersection / union * 65536);
    }

    private void separateString(String str, HashMap<String, Integer> map){
        String tmp;
        for (int i = 1; i < str.length(); i++) {
            if (Character.isAlphabetic(str.charAt(i - 1)) && Character.isAlphabetic(str.charAt(i))){
                tmp = str.substring(i - 1, i + 1).toUpperCase();
                if (map.containsKey(tmp)) map.put(tmp, map.get(tmp) + 1);
                else map.put(tmp, 1);
            }
        }
    }
}