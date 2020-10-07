package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.Arrays;

public class 파일명_정렬 {
    public static void main(String[] args) {
        파일명_정렬 prob = new 파일명_정렬();
        System.out.println(Arrays.toString(prob.solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
        System.out.println(Arrays.toString(prob.solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"})));
    }

    public String[] solution(String[] input) {
        String[] answer = new String[input.length];
        File[] files = new File[input.length];

        for (int i = 0; i < files.length; i++) files[i] = getFile(input[i], i);
        Arrays.sort(files);

        for (int i = 0; i < files.length; i++) answer[i] = files[i].getOrigin();
        return answer;
    }

    private File getFile(String strFile, int index){
        int from, to = strFile.length() - 1;
        for (from = 0; from < strFile.length(); from++) {
            if (Character.isDigit(strFile.charAt(from))){
                to = from;
                while (to < strFile.length() && Character.isDigit(strFile.charAt(to))) to++;
                break;
            }
        }

        String first = strFile.substring(0, to);
        String tail = strFile.substring(to);

        return getFile(strFile, first, tail, index);
    }

    private File getFile(String origin, String first, String tail, int index){
        String head = first.replaceAll("[\\d]", "");
        int number = Integer.parseInt(first.replaceAll("[^\\d]", ""));
        return new File(origin, head, tail, number, index);
    }

    class File implements Comparable<File> {
        String origin, head, tail;
        int number, index;

        File(String origin, String head, String tail, int number, int index) {
            this.origin = origin;
            this.head = head;
            this.tail = tail;
            this.number = number;
            this.index = index;
        }

        String getOrigin() {
            return origin;
        }

        @Override
        public int compareTo(File o) {
            if (this.head.compareToIgnoreCase(o.head) == 0){
                if (this.number == o.number) return Integer.compare(this.index, o.index);
                else return Integer.compare(this.number, o.number);
            }
            return this.head.compareToIgnoreCase(o.head);
        }
    }
}