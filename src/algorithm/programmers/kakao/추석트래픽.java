package algorithm.programmers.kakao;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class 추석트래픽 {
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	
	public static void main(String[] args) throws Exception {
		추석트래픽 pro = new 추석트래픽();
		pro.logParser("2016-09-15 01:00:04.001 2.1s");
		pro.logParser("2016-09-15 01:00:07.000 2s");
	}
	
	// 1. Wrapper 클래스 만들기. (초 단위로 시작시간, 끝시간 변수)
	// 2. 시작시간, 끝시간 순으로 오름차순 정렬
	// 3. 2중 for 문으로 시작시간 >= 끝시간 인 최대 개수를 구함
    public int solution(String[] lines) throws Exception{
        int answer = 0;
        Log[] logs = new Log[lines.length];
        
        for (int i = 0; i < lines.length; i++) {
			logs[i] = logParser(lines[i]);
		}
        
        Arrays.parallelSort(logs);
        
        for (int i = 0; i < logs.length - 1; i++) {
        	int tmp = 1;
        	System.out.println(logs[i]);
			for (int j = i + 1; j < logs.length; j++) {
				// logs[i].to - 1 <= logs[j].from
				if (logs[i].to - 1 <= logs[j].from) tmp++;
				System.out.println(logs[j]);
			}
			answer = Math.max(answer, tmp);
		}
        
        return answer;
    }
    
    
    
	private Log logParser(String input) throws Exception{
		String dateStr = input.substring(0, input.lastIndexOf(" "));
		String pTimeStr = input.substring(input.lastIndexOf(" "), input.length() - 1);
		
		long sec = format.parse(dateStr).getTime();	// To
		int pTime = (int)(Double.parseDouble(pTimeStr) * 1000) - 1;
		
		Log log = new Log(sec - pTime, sec);
		return log;
	}
	
	class Log implements Comparable<Log>{
		long from, to;

		public Log(long from, long to) {
			this.from = from;
			this.to = to;
		}
		
		@Override
		public int compareTo(Log o) {
			if (this.from == o.from) return Long.compare(this.to, o.to);
			return Long.compare(this.from, o.from);
		}

		@Override
		public String toString() {
			return "Log [from=" + from + ", to=" + to + "]";
		}
	}
}
