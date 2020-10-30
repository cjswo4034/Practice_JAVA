package algorithm.prev.programmers.Level3;

public class _1830 {

    public static void main(String[] args) {
        System.out.println(solution("HaEaLaLaObWORLDb"));
        System.out.println(solution("SpIpGpOpNpGJqOqA"));
        System.out.println(solution("AxAxAxAoBoBoB"));
        System.out.println(solution("aIaAM"));
        System.out.println(solution("baHELLOabWORLD"));
        System.out.println(solution("aHbEbLbLbOacWdOdRdLdDc"));
        System.out.println(solution("bAaOb"));
        System.out.println(solution("TxTxTxbAb"));
        System.out.println(solution("bTxTxTaTxTbkABaCDk"));
        System.out.println(solution("xAaAbAaAx"));
    }

    public static String solution(String input) {
        int[] cnt = checkInit(input); // 소문자 위치 확인
        boolean[] check = new boolean[input.length()];

        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (Character.isLowerCase(curr) && cnt[curr - 'a'] <= 0){   // 소문자 갯수가 0 이하일 때
                return "invalid";
            } else if (Character.isLowerCase(curr) && cnt[curr - 'a'] != 2){ // 소문자이면서 규칙 1
                if (check[i - 1]) return "invalid";
                while (i < input.length() && cnt[curr - 'a'] > 0){ // 소문자의 갯수가 0이 될 때까지
                    if (Character.isUpperCase(input.charAt(i))){
                        sb.append(input.charAt(i));
                        check[i] = true;
                    } else if (curr == input.charAt(i)) {
                        cnt[curr - 'a']--;
                    } else return "invalid";
                    i++;
                }
                if (Character.isLowerCase(input.charAt(i))) return "invalid";
                sb.append(input.charAt(i)).append(" ");
                check[i] = true;
            } else if (Character.isLowerCase(curr) && cnt[curr - 'a'] == 2){ // 소문자이면서 규칙 2
                if (Character.isLowerCase(++i)) return "invalid";
                if (!check(i - 1, input.lastIndexOf(curr), input)) return "invalid";
                if (sb.length() - 1 >= 0 && sb.charAt(sb.length() - 1) != ' ') sb.append(" ");

                while (i < input.length() && input.charAt(i) != curr){
                    if (Character.isUpperCase(input.charAt(i))){
                        sb.append(input.charAt(i));
                        check[i] = true;
                    }

                    /* else if (input.charAt(i) != curr) {
                        char tmp = input.charAt(i);
                        while (i < input.length() && cnt[tmp - 'a'] > 0){ // 소문자의 갯수가 0이 될 때까지
                            if (Character.isUpperCase(input.charAt(i))){
                                sb.append(input.charAt(i));
                                check[i] = true;
                            } else if (tmp == input.charAt(i)) {
                                cnt[tmp - 'a']--;
                            } else return "invalid";
                            i++;
                        }
                        sb.append(input.charAt(i));
                        check[i] = true;
                    }*/
                    i++;
                }
                sb.append(" ");
            } else if (Character.isUpperCase(curr)){
                sb.append(input.charAt(i));
            } else return "invalid";
        }

        i = 0;
        while (i < sb.length())
            if (Character.isLowerCase(sb.charAt(i++))) return "invalid";
        return sb.toString().trim();
    }

    private static int[] checkInit(String input) {
        int[] arr = new int['z' - 'a' + 1];
        for (int i = 0; i < input.length(); i++) {
            if (Character.isLowerCase(input.charAt(i)))
                arr[input.charAt(i) - 'a']++;
        }
        return arr;
    }

    private static boolean check(int start, int end, String input){
        // HaEaLaLaObWORLDb
        if (Character.isLowerCase(input.charAt(start + 1)) || Character.isLowerCase(input.charAt(end - 1))) return false;
        int upperCnt = 0, lowerCnt = 0;
        char chr = ' ';
        for (int i = start + 1; i < end; i++) {
            if (Character.isLowerCase(input.charAt(i))) {
                if (chr != ' ' && chr != input.charAt(i)) return false;
                chr = input.charAt(i);
                lowerCnt++;
            } else upperCnt++;
        }

        return upperCnt - 1 == lowerCnt || lowerCnt == 0;
    }
}