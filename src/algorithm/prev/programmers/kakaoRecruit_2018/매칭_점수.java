package algorithm.prev.programmers.kakaoRecruit_2018;

import java.util.ArrayList;

public class 매칭_점수 {
    public static void main(String[] args) {
        매칭_점수 prob = new 매칭_점수();
        System.out.println(prob.solution("blind", new String[]{
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"}));
    }

    public int solution(String word, String[] pages){
        String[] str = pages[0].split("\\n");
        for (String s : str){
            System.out.println(s);
        }
        return 0;
    }

    class Html{
        double basicCnt, matchingCnt;
        String url, contents;
        ArrayList<Html> externalLink;

        Html(){
            this.externalLink = new ArrayList<>();
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public void addContents(String contents){
            this.contents += contents;
        }

        public void addExternalLink(Html html) {
            this.externalLink.add(html);
        }

        public void calculateBasicCnt(String keyWord){
            String[] tmp = this.contents.split(" ");
            for (String str : tmp){
                int from = str.indexOf(keyWord);
                if (from >= 0){
                    if (from > 0 && Character.isAlphabetic(str.charAt(from - 1))) continue;
                    if (from + keyWord.length() < str.length() - 1 &&
                            Character.isAlphabetic(str.charAt(from + keyWord.length() - 1))) continue;
                    this.basicCnt++;
                }
            }
        }

        public void calculateExternalMatchingCNt(){
            for (Html html : externalLink)
                html.matchingCnt += (this.basicCnt / this.externalLink.size());
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Html)) return false;
            Html html = (Html)obj;
            return this.url.equals(html.url);
        }

        @Override
        public int hashCode() {
            return this.url.hashCode();
        }
    }
}