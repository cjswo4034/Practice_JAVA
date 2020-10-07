package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 다트게임 {
    public static void main(String[] args) {
        다트게임 prob = new 다트게임();
        System.out.println(prob.solution("1S2D*3T"));
        System.out.println(prob.solution("1D2S#10S"));
        System.out.println(prob.solution("1D2S0T"));
        System.out.println(prob.solution("1S*2T*3S"));
        System.out.println(prob.solution("1D#2S*3S"));
        System.out.println(prob.solution("1T2D3D#"));
        System.out.println(prob.solution("1D2S3T*"));
    }

    public int solution(String dartResult) {
        int answer = 0;

        // 1. 회차별 분리
        String[] points = getSeparatePoint(dartResult);

        // 2. 점수별 계산산
        String opt; int[] digitPoints = new int[3];
        for (int i = 0; i < points.length; i++) {
            opt = points[i].replaceAll("[(\\d|A-Z)]", "");
            digitPoints[i] = getPoint(points[i], opt);
            if (i > 0 && opt.length() > 0 && opt.charAt(0) == '*')
                digitPoints[i - 1] *= 2;
        }

        System.out.println(dartResult + " : " + Arrays.toString(points) + " : " + Arrays.toString(digitPoints));
       return IntStream.of(digitPoints).sum();
    }

    private String[] getSeparatePoint(String dartResult){
        String[] str = new String[3];
        int start = 0, cnt = 0; char curr, prev;
        for (int i = 1; i < dartResult.length(); i++) {
            curr = dartResult.charAt(i);
            prev = dartResult.charAt(i - 1);
            if ((!Character.isDigit(prev) && Character.isDigit(curr))){
                str[cnt++] = dartResult.substring(start, i);
                start = i;
            }
        }
        str[cnt] = dartResult.substring(start);
        return str;
    }

    private int getPoint(String strPoint, String opt){
        int point = Integer.parseInt(strPoint.replaceAll("[^\\d]", ""));
        char bonus = strPoint.replaceAll("[^A-Z]", "").charAt(0);

        return getPoint(point, bonus, opt);
    }

    private int getPoint(int point, char bonus, String opt){
        switch (bonus){
            case 'D' : point *= point; break;
            case 'T' : point *= point * point; break;
        }

        if (opt.length() > 0){
            switch (opt.charAt(0)){
                case '*' : point *= 2; break;
                case '#' : point *= -1; break;
            }
        }

        return point;
    }
}