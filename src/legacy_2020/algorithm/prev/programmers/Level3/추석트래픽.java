package algorithm.prev.programmers.Level3;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class 추석트래픽 {
	public static void main(String[] args) throws Exception {
		추석트래픽 pl = new 추석트래픽();
//		System.out.println(pl.solution(new String[] { 
//				"2016-09-15 01:00:04.001 2.0s",
//				"2016-09-15 01:00:07.000 2s" }));
//		System.out.println(pl.solution(new String[] { 
//				"2016-09-15 01:00:04.002 2.0s",  
//				"2016-09-15 01:00:07.000 2s" }));
		System.out.println(pl.solution(new String[] { 
				"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
				"2016-09-15 21:00:02.066 2.62s"
				}));
		 
	}
	
	public int solution(String[] lines) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		List<Time> list = new LinkedList<>();
		
		for (String line : lines) {
			list.add(strToTime(line, format));
			System.out.println(strToTime(line, format));
		}
		
		Collections.sort(list);
		
		int answer = 0;
		for (int i = 0; i < list.size(); i++) {
			answer = Math.max(answer, countOneSec(list, i));
		}
		
		return answer;
	}
	
	// 끝 시간이 1초 이내인 것들의 개수
	public int countOneSec(List<Time> list, int idx) {
		long criteria = list.get(idx).to + 1000;
		int count = 1;	// 현재 시간 포함
		for (int i = idx + 1; i < list.size(); i++) {
			System.out.println(list.get(i).from + " : " + criteria);
			if (list.get(i).from < criteria) count++;
		}
		System.out.println("------------------------" +count);
		return count;
	}
	
	public Time strToTime(String date, SimpleDateFormat format) throws Exception{
		String strTo = date.substring(0, date.lastIndexOf(" "));
		String strFrom = date.substring(date.lastIndexOf(" "));
		strFrom = strFrom.replaceAll("[^0-9]", "");
		for (; strFrom.length() < 4;) strFrom += "0";
		
		long to = format.parse(strTo).getTime();
		long from = to - Long.parseLong(strFrom) + 1;
				
		return new Time(from, to);
	}
	
	class Time implements Comparable<Time>{
		long from, to;
		
		public Time(long from, long to){
			this.from = from;
			this.to = to;
		}

		@Override
		public String toString() {
			return "Time [from=" + from + ", to=" + to + "]";
		}
		
		@Override
		public int compareTo(Time o) {
			if (this.to == o.to)
				return Long.compare(this.from, o.from);
			return Long.compare(this.to, o.to);
		}
	}
}
