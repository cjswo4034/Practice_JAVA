package algorithm.prev.programmers.kakaoRecruit_2017;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class 추석트래픽 {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");

    public static void main(String[] args) throws Exception {
        추석트래픽 prob = new 추석트래픽();
        System.out.println(prob.solution(new String[]{
                "2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"}));
        System.out.println(prob.solution(new String[]{
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"}));
        System.out.println(prob.solution(new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"}));
    }

    public int solution(String[] lines) throws Exception {
        int answer = 1;

        Time[] times = new Time[lines.length];
        String processingTime;
        for (int idx = 0; idx < lines.length; idx++){
            processingTime = getProcessingTime(lines[idx]);
            times[idx] = convertToTime(getTime(lines[idx], processingTime), convertToInt(processingTime));
        }
        Arrays.sort(times);

        for (int i = 0; i < times.length - 1; i++) {
            int tmp = 1;
            for (int j = i + 1; j < times.length; j++) {
                if (times[i].to + 1000 > times[j].from){
                    tmp++;
                }
            }
            answer = Math.max(answer, tmp);
        }

        return answer;
    }

    private String getTime(String line, String processingTime){
        return line.replace(processingTime, "");
    }

    private String getProcessingTime(String line){
        return line.substring(line.lastIndexOf(" "));
    }

    private int convertToInt(String processingTime){
        return (int)(Double.parseDouble(processingTime.substring(1, processingTime.length() - 1)) * 1000);
    }

    private Time convertToTime(String dateTime, int processingTime) throws Exception {
        long to = format.parse(dateTime).getTime();
        long from = new Date(to - processingTime + 1).getTime();
        return new Time(from, to);
    }

    class Time implements Comparable<Time>{
        long from, to;

        Time(long from, long to){
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Time{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }

        @Override
        public int compareTo(Time o) {
            if (this.to == o.to) return Long.compare(this.from, o.from);
            return Long.compare(this.to, o.to);
        }
    }
}