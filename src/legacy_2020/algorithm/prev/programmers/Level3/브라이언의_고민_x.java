package algorithm.prev.programmers.Level3;

import java.util.*;

public class 브라이언의_고민_x {
    public static void main(String[] args) {
        브라이언의_고민_x prob = new 브라이언의_고민_x();
        System.out.println(prob.solution("AaAaAbBbCcCcC"));
        System.out.println(prob.solution("HaEaLaLaObWORLDb"));
        // sentence에 공백이 있는경우
    }

    public String solution(String sentence) {
        StringBuilder sb = new StringBuilder();
        int[] visited = new int[sentence.length()];
        Map<Character, Alphabet> map = new HashMap<>();
        for (int i = 0; i < sentence.length(); i++) {
            Alphabet alpha;
            char ch = sentence.charAt(i);
            if (ch == ' ') return "invalid";
            if (Character.isLowerCase(ch)){
                map.putIfAbsent(ch, new Alphabet(ch, i));
                alpha = map.get(ch);
                alpha.to = i;
                alpha.count++;
                alpha.index = map.size();
            }
        }
        if (map.size() != 0){
            String checkResult;
            for (Alphabet alpha: map.values()) {
                if (alpha.count != 2) {
                    checkResult = checkRule1(alpha, visited, sentence);
                    if (checkResult.equals("invalid")) return checkResult;
                    else alpha.result = checkResult;
                }
            }

            for (Alphabet alpha: map.values()) {
                if (alpha.count == 2) {
                    checkResult = checkRule1(alpha, visited, sentence);
                    if (!checkResult.equals("invalid")) alpha.result = checkResult;
                }
            }

            for (Alphabet alpha: map.values()) {
                if (alpha.count == 2 && !alpha.visited) {
                    checkResult = checkRule2(alpha, visited, sentence);
                    if (checkResult.equals("invalid")) return checkResult;
                    else alpha.result = checkResult;
                }
            }


            List<Alphabet> list = new ArrayList<>(map.values());
            list.sort(Comparator.comparingInt(o -> o.index));
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == 0)
                    if (Character.isLowerCase(sentence.charAt(i))) return "invalid";
                    else sb.append(sentence.charAt(i));
                else {
                    Alphabet alpha = list.get(visited[i] - 1);
                    if (i > 0 && visited[i - 1] == 0) sb.append(" ");
                    sb.append(alpha.result).append(" ");
                    i = alpha.to + (alpha.rule1 ? 2 : 1);
                }
            }
            return sb.toString().trim();
        }
        return sentence;
    }

    private String checkRule1(Alphabet alpha, int[] visited, String sentence){
        alpha.rule1 = true;
        for (int i = alpha.from - 1; i <= alpha.to + 1; i++) {
            if (i < 0 || i >= visited.length || visited[i] != 0) return "invalid";
            if (Character.isLowerCase(sentence.charAt(i)) && sentence.charAt(i) != alpha.ch) return "invalid";
            if (i <= alpha.to && Character.isUpperCase(sentence.charAt(i)) && Character.isUpperCase(sentence.charAt(i + 1))) return "invalid";
        }
        alpha.visited = true;
        for (int i = alpha.from - 1; i <= alpha.to + 1; i++) visited[i] = alpha.index;
        return sentence.substring(alpha.from - 1, alpha.to + 2).replaceAll("[a-z]", "");
    }

    private String checkRule2(Alphabet alpha, int[] visited, String sentence) {
        int flag = visited[alpha.from + 1];
        for (int i = alpha.from + 1; i < alpha.to; i++) {
            if (i < 0 || i >= visited.length || visited[i] != 0) return "invalid";
            if (flag != visited[i]) return "invalid";
        }
        alpha.visited = true;
        for (int i = alpha.from; i <= alpha.to; i++) visited[i] = alpha.index;
        return sentence.substring(alpha.from + 1, alpha.to).replaceAll("[a-z]", "");
    }

    class Alphabet{
        char ch;
        int from, to, count, index;
        boolean rule1, visited;
        String result;

        Alphabet(char ch, int from){
            this.ch = ch;
            this.from = from;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ch);
        }
    }
}
