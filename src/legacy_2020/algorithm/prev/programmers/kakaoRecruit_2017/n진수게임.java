package algorithm.prev.programmers.kakaoRecruit_2017;

public class n진수게임 {
    public static void main(String[] args) {
        n진수게임 prob = new n진수게임();
        System.out.println(prob.solution(2, 4, 2, 1));
        System.out.println(prob.solution(16, 16, 2, 1));
        System.out.println(prob.solution(16, 16, 2, 2));
    }

    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();

        int curr = 0, cnt = 0; // curr : 현재 변환할 숫자, cnt : 현재 진행된 순번
        String tmp;            // sb.length() : 본인 차례에 대답할 숫자들의 갯수
        loop: while (sb.length() < t) {
            tmp = Integer.toString(curr++, n);
            for (char ch : tmp.toCharArray()) {
                cnt++;
                if (cnt % p == 0) {
                    if (Character.isAlphabetic(ch)) sb.append((char) (ch - 32));
                    else sb.append(ch);
                    p += m;
                }
                if (sb.length() >= t) break loop;
            }
        }
        return sb.toString();
    }
}
