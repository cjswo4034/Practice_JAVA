package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.LinkedHashSet;

public class 캐시 {
    public static void main(String[] args) {
        캐시 prob = new 캐시();
        System.out.println(prob.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(prob.solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(prob.solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
        System.out.println(prob.solution(0, new String[]{"Jeju", "Jeju", "Seoul", "NewYork", "LA"}));
    }

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;

        int answer = 0;
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (String city : cities) {
            if (set.size() > 0 && set.size() >= cacheSize && !set.contains(city.toUpperCase()))
                set.remove(set.iterator().next());
            answer += getProcessingTime(set, city.toUpperCase());
        }
        return answer;
    }

    private int getProcessingTime(LinkedHashSet<String> set, String city){
        if (!set.contains(city)){
            set.add(city);
            return 5;
        } else {
            set.remove(city);
            set.add(city);
            return 1;
        }
    }
}