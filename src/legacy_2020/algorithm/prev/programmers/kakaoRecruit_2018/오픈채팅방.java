package algorithm.prev.programmers.kakaoRecruit_2018;

import java.util.Arrays;
import java.util.HashMap;

public class 오픈채팅방 {
    public static void main(String[] args) {
        오픈채팅방 prob = new 오픈채팅방();
        System.out.println(Arrays.toString(prob.solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));
    }

    public String[] solution(String[] records) {
        String[] answer;
        HashMap<String, String> map = new HashMap<>();
        String[] tmpRecord; int size = 0;
        for (String record : records){
            tmpRecord = record.split(" ");
            if (!tmpRecord[0].equals("Leave")) map.put(tmpRecord[1], tmpRecord[2]);
            if (!tmpRecord[0].equals("Change")) size++;
        }

        answer = new String[size];
        size = 0;
        for (String record : records){
            tmpRecord = record.split(" ");
            if (tmpRecord[0].equals("Enter")) answer[size++] = enter(map, tmpRecord[1]);
            else if (tmpRecord[0].equals("Leave")) answer[size++] = leave(map, tmpRecord[1]);
        }
        return answer;
    }

    private String enter(HashMap<String, String> map, String id){
        StringBuilder sb = new StringBuilder();
        sb.append(map.get(id)).append("님이").append("들어왔습니다.");
        return sb.toString();
    }

    private String leave(HashMap<String, String> map, String id){
        StringBuilder sb = new StringBuilder();
        sb.append(map.get(id)).append("님이").append("나갔습니다.");
        return sb.toString();
    }
}