package algorithm.prev.programmers.Level2;

// 1) 완탐 : 현재 올림수(기울기 * x좌표) - 이전 내림수(기울기 * x좌표)의 총합
// 2) (w / 최대공약수, h / 최대공약수)로 문제를 축소 후 완탐으로 풀기 -> 100... / 99... 일 경우 시간초과

public class _62048_x {
    public static void main(String[] args) {
        _62048_x prob = new _62048_x();
        System.out.println(prob.solution(8, 12));
        System.out.println(prob.solution(2, 3));
        System.out.println(prob.solution(3, 5));
        System.out.println(prob.solution(3, 6));
    }

    public long solution(int w,int h) {
        if (w == 1 || h == 1) return 0;
        if (w == h) return (w - 1) * (long)h;

        double slope = Math.max(w, h) / (double)Math.min(w, h);
        long answer = 0;
        for (int i = 0; i < Math.min(w, h); i++) {
            answer += Math.ceil(slope * i) - Math.floor(slope * (i - 1));
        }

        return answer;
    }
}