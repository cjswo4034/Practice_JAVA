package algorithm.programmers.kakao;

public class 문자열_압축 {
	public static void main(String[] args) {
		문자열_압축 prob = new 문자열_압축();
		prob.compress("a", 3);
	}
	
	// 1 ~ n / 2 까지 문자를 잘랐을 때 최소 길이 
	public int solution(String s) {
        int answer = s.length();
        for (int i = 1, len = s.length(); i <= len / 2; i++) {
			answer = Math.min(answer, compress(s, i));
		}
        return answer;
    }
	
	// 나누어 떨어질 때만 value 만큼 s를 잘라 압축한다.
	private int compress(String s, int value) {
		int cnt = 1;
		String prev = "", curr = "", tmp = "";
		StringBuilder sb = new StringBuilder();

		if (s.length() % value != 0)
			tmp = s.substring(s.length() / value * value);
		
		for (int i = 0; i + value <= s.length(); i += value) {
			curr = s.substring(i, i + value);
			if (!curr.equals(prev)) {
				if (cnt > 1) sb.append(cnt);
				sb.append(prev);
				cnt = 1;
			} else cnt++;
			prev = curr;
		}

		if (cnt > 1) sb.append(cnt);
		sb.append(prev).append(tmp);

		return sb.length();
	}
}