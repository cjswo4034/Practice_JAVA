package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.Arrays;

public class 셔틀버스 {
    public static void main(String[] args) {
        셔틀버스 prob = new 셔틀버스();
        System.out.println(prob.solution(2,10, 5, new String[]{"08:09", "08:05", "09:00",  "08:02", "08:03"}));
        System.out.println(prob.solution(2,1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(prob.solution(1,1, 1, new String[]{"00:01"}));
        System.out.println(prob.solution(10,60, 1, new String[]{"18:02"}));
    }

    // 1. 가장 늦게 도착한 셔틀버스가 여유 있다면 버스가 도착할 때 도착하면 됨
    // 2. 가장 늦게 탑승한 승객보다 1분 빨리 도착하면 됨
    public String solution(int n, int t, int m, String[] timetable) {
        Shuttle[] shuttles = getShuttles(n, t, m);      // 셔틀버스 생성
        int[] timetableToInt = new int[timetable.length];  // 크루 도착시간 분으로 변경
        for (int i = 0; i < timetable.length; i++) timetableToInt[i] = timetableToInt(timetable[i]);
        Arrays.sort(timetableToInt);                       // 크루 도착시간을 오름차순으로 변경

        int idx = 0;
        for (Shuttle shuttle : shuttles){               // 가장 빨리 도착하는 셔틀버스부터 출발
            int curr = shuttle.time;                    // 현재 도착한 셔틀버스의 시간
            for (; idx < timetableToInt.length; idx++) {
                if (curr < timetableToInt[idx]) break;     // 크루의 도착시간이 버스 도착시간보다 늦다면 다음 셔틀로 이동
                if (!shuttle.enableBoardingShuttle(timetableToInt[idx])) break;    // 현재 셔틀의 정원이 초과하면 다음 셔틀로 이동
            }
            Arrays.sort(shuttle.arr);
        }

        Shuttle lastShuttle = shuttles[shuttles.length - 1];            // 탑승할 수 있는 버스 중 가장 마지막 버스
        if (lastShuttle.size < m)                                       // 여유가 있다면 해당 버스가 도착할 때 도착하면 Ok
            return getStringTime(lastShuttle.time);
        else                                                            // 마지막 셔틀을 가장 마지막에 탑승한 승객보다 1분 빨리 도착
            return getStringTime(lastShuttle.arr[lastShuttle.size - 1] - 1);
    }

    private Shuttle[] getShuttles(int n, int t, int limit){
        Shuttle[] shuttles = new Shuttle[n];
        for (int i = 0; i < n; i++) shuttles[i] = new Shuttle(i * t + 60 * 9, limit);
        return shuttles;
    }

    private int timetableToInt(String time){
        String[] strTime = time.split(":");
        return Integer.parseInt(strTime[0]) * 60 + Integer.parseInt(strTime[1]);
    }

    private String getStringTime(int time){
        int hour = time / 60, min = time % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(hour < 10 ? "0" : "").append(hour).append(":");
        sb.append(min < 10 ? "0" : "").append(min);
        return sb.toString();
    }

    class Shuttle{
        int time, limit, size;
        int[] arr;

        Shuttle(int time, int limit) {
            this.time = time;
            this.limit = limit;
            this.size = 0;
            this.arr = new int[limit];
        }

        boolean enableBoardingShuttle(int time){
            if (size >= limit) return false;
            arr[size++] = time;
            return true;
        }
    }
}