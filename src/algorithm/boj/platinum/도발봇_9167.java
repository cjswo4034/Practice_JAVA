package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// [Platinum 4] BNF, 구현, 파싱
public class 도발봇_9167 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Token token = new Token();
        Pos pos = new Pos();
        Parser parser = new Parser(token, pos);

        String line = br.readLine();
        do {
            line = line.trim().replaceAll("[ \t]+", " ");  // 1. 연속적인 공백 제거
            sb.append("Knight: ").append(line).append("\n");
            sb.append(parser.parse(erase(line))).append("\n");                // 2. 단어를 이루는 영문자, 공백 외의 문자 제거
            line = br.readLine();
        } while (line != null && !line.isEmpty());

        System.out.print(sb);
    }

    static String erase(String line) {
        return line.trim().replaceAll("[^a-zA-Z ]", "");
    }

    static class Parser {
        private final Token TOKEN;
        private final Pos POS;
        private final char[] HOLYGRAIL = "theholygrail".toCharArray();

        public Parser(Token token, Pos POS) {
            this.TOKEN = token;
            this.POS = POS;
        }

        public String parse(String line) {
            StringBuilder sb = new StringBuilder();
            int wordCnt = line.split(" ").length;
            int cnt = wordCnt / 3;

            // 1. check t-h-e-h-o-l-y-g-r-a-i-l
            if (containTheHolyGrail(line.toLowerCase())) {
                sb.append("Taunter: (A childish hand gesture).\n");
                cnt--;
            }

            // 2. 도발할 횟수
            if (wordCnt % 3 != 0) cnt++;

            // 3. 2에서 구한 횟수만큼 도발. taunt == 1은 도발 2회
            while (cnt > 0) {
                cnt--;
                if (POS.isDoubleCount()) cnt--;
                sb.append("Taunter: ").append(tant()).append(".\n");
            }

            return sb.toString();
        }

        // 문장의 처음은 대문자
        private String firstLetterToUpperCase(String line) {
            return Character.toUpperCase(line.charAt(0)) + line.substring(1);
        }

        // 1. tant ::= <sentence> | <taunt> <sentence> | <noun>! | <sentence>
        private String tant() {
            int pos = POS.getTaunt();

            StringBuilder sb = new StringBuilder();
            if (pos == 2) sb.append(TOKEN.getToken("noun")).append("!");
            else if (pos == 1) sb.append(tant()).append(" ").append(firstLetterToUpperCase(sentence()));
            else sb.append(sentence());

            return firstLetterToUpperCase(sb.toString());
        }

        // 2. sentence ::= <past-rel> <noun-phrase> | <present-rel> <noun-phrase> | <past-rel> <article> <noun>
        private String sentence() {
            int pos = POS.getSentence();
            StringBuilder sb = new StringBuilder("your ");

            if (pos == 1) sb.append(presentRel());
            else sb.append(pastRel());

            sb.append(" ");

            if (pos == 2) {
                sb.append(TOKEN.getToken("article")).append(" ").append(TOKEN.getToken("noun"));
            } else sb.append(nounPhrase());

            return sb.toString();
        }

        // 3. noun-phrase ::= <article> <modified-noun>
        private String nounPhrase() {
            return TOKEN.getToken("article") + " " + modifiedNoun();
        }

        // 4. modified-noun ::= <noun> | <modifier> <noun>
        private String modifiedNoun() {
            int pos = POS.getModifiedNoun();
            if (pos == 0) return TOKEN.getToken("noun");
            else return modifier() + " " + TOKEN.getToken("noun");
        }

        // 5. modifier ::= <adjective> | <adverb> <adjective>
        private String modifier() {
            int pos = POS.getModifier();
            if (pos == 0) return TOKEN.getToken("adjective");
            return TOKEN.getToken("adverb") + " " + TOKEN.getToken("adjective");
        }

        // 6. present-rel ::= your <present-person> <present-verb>
        private String presentRel() {
            return TOKEN.getToken("present-person") + " " + TOKEN.getToken("present-verb");
        }

        // 7. past-rel ::= your <past-person> <past-verb>
        private String pastRel() {
            return TOKEN.getToken("past-person") + " " + TOKEN.getToken("past-verb");
        }

        private boolean containTheHolyGrail(String line) {
            int pos = -1, i = 0;
            while (i < HOLYGRAIL.length && (pos = line.indexOf(HOLYGRAIL[i], pos + 1)) != -1) i++;

            return i == HOLYGRAIL.length;
        }
    }

    static class Pos {
        private int taunt = -1;
        private int sentence = -1;
        private int modifiedNoun = 1;
        private int modifier = 1;

        public boolean isDoubleCount() {
            return taunt == 0;
        }

        public int getTaunt() {
            return taunt = (taunt + 1) % 4;
        }

        public int getSentence() {
            return sentence = (sentence + 1) % 3;
        }

        public int getModifiedNoun() {
            return modifiedNoun ^= 1;
        }

        public int getModifier() {
            return modifier ^= 1;
        }
    }

    static class Token {
        private final Queue<String> PRESENT_PERSON = new LinkedList<>();
        private final Queue<String> PAST_PERSON = new LinkedList<>();
        private final Queue<String> NOUN = new LinkedList<>();
        private final Queue<String> PRESENT_VERB = new LinkedList<>();
        private final Queue<String> PAST_VERB = new LinkedList<>();
        private final Queue<String> ADJECTIVE = new LinkedList<>();
        private final Queue<String> ADVERB = new LinkedList<>();

        Token() {
            PRESENT_PERSON.addAll(Arrays.asList("steed | king | first-born".split(" \\| ")));
            PAST_PERSON.addAll(Arrays.asList("mother | father | grandmother | grandfather | godfather".split(" \\| ")));
            PRESENT_VERB.addAll(Arrays.asList("is | masquerades as".split(" \\| ")));
            PAST_VERB.addAll(Arrays.asList("was | personified".split(" \\| ")));
            ADJECTIVE.addAll(Arrays.asList("silly | wicked | sordid | naughty | repulsive | malodorous | ill-tempered".split(" \\| ")));
            ADVERB.addAll(Arrays.asList("conspicuously | categorically | positively | cruelly | incontrovertibly".split(" \\| ")));
            NOUN.addAll(Arrays.asList("hamster | coconut | duck | herring | newt | peril | chicken | vole | parrot | mouse | twit".split(" \\| ")));
        }

        String getToken(String type) {
            String res = null;
            Queue<String> q = null;

            switch (type) {
                case "present-person": q = PRESENT_PERSON; break;
                case "past-person": q = PAST_PERSON; break;
                case "present-verb": q = PRESENT_VERB;break;
                case "past-verb": q = PAST_VERB; break;
                case "adjective": q = ADJECTIVE; break;
                case "adverb": q = ADVERB; break;
                case "noun": q = NOUN; break;
                default: return "a";
            }

            res = q.peek();
            q.offer(q.poll());

            return res;
        }
    }
}