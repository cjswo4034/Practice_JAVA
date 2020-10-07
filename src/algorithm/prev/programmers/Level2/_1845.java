package algorithm.prev.programmers.Level2;

import java.util.ArrayList;
import java.util.List;

public class _1845 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,1,2,3}));
        System.out.println(solution(new int[]{3,3 ,3, 2, 2, 4}));
        System.out.println(solution(new int[]{3,3, 3, 2, 2 ,2}));
    }

    public static int solution(int[] input){
        List<Integer> list = new ArrayList<>();
        for(int num : input){
            if (!list.contains(num)) list.add(num);
        }
        return input.length / 2 < list.size() ? input.length / 2 : list.size();
    }
}
