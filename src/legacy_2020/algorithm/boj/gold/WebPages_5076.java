package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// gold 5
public class WebPages_5076 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern = Pattern.compile("(?<=<)([^>]+?)(?=>)");
        String line;
        while ((line = br.readLine()) != null) {
            if(line.equals("#")) break;
            System.out.println(solution(pattern.matcher(line)));
        }
    }

    private static String solution(Matcher matcher) {
        Stack<String> stack = new Stack<>();
        String tag;
        while (matcher.find()) {
            tag = matcher.group();
            if (tag.contains(" ") && !tag.split(" ")[1].equals("/"))
                tag = tag.split(" ")[0];
            if (!tag.contains("/")) {
                stack.add(tag);
            } else {
                if (tag.indexOf("/") == tag.length() - 1) continue;
                tag = tag.replaceAll("/", "");
                if (!stack.isEmpty() && tag.equals(stack.peek())) stack.pop();
                else return "illegal";
            }
        }
        return stack.isEmpty() ? "legal" : "illegal";
    }
}
