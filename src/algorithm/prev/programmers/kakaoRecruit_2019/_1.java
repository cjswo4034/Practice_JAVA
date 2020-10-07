package algorithm.prev.programmers.kakaoRecruit_2019;

import java.util.ArrayList;

// pass
public class _1 {
    public static void main(String[] args) {
        _1 prob = new _1();
        System.out.println(prob.solution("aaaaaaaaaaaaaaaaaaaa"));
    }

    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= s.length(); i++) {
            answer = Math.min(getLength(s, i), answer);
        }
        return answer;
    }

    private int getLength(String s, int n){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < s.length() - n; i += n) list.add(s.substring(i, i + n));
        if (s.length() % n != 0) list.add(s.substring(s.length() - (s.length() % n)));
        else list.add(s.substring(s.length() - n));

        int cnt = 0, answer = 0;
        for (int i = 0; i < list.size(); ) {
            int j = i + 1;
            cnt = 1;
            while (j < list.size() && list.get(i).equals(list.get(j))){
                j++;
                cnt++;
            }
            if (cnt == 1) answer += list.get(i).length();
            else answer += list.get(i).length() + String.valueOf(cnt).length();
            i = j;
        }

        return answer;
    }
}
