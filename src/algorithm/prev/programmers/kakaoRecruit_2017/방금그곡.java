package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 방금그곡 {
    // 가능하다면 Music 개체를 생성해서 리스트에 삽입
    // 1. 재생 시간기준 내림차순으로 정렬
    // 2. 인덱스 기준 오름차순으로 정렬
    // 0번째 객체 반환, 빈 리스트일 경우 (None) 반환

    public static void main(String[] args) {
        방금그곡 prob = new 방금그곡();
        System.out.println(prob.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(prob.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(prob.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:06,WORLD,ABCDEF","13:00,13:06,WORLD3,ABCDEF", "13:00,13:06,WORLD2,ABCDEF"}));
    }

    public String solution(String m, String[] musicInfos) {
        ArrayList<Music> list = new ArrayList<>();
        String[] tmp; Music tmpMusic;
        for (int i = 0 ; i < musicInfos.length ; i++){
            tmp = musicInfos[i].split(",");     // 0: 시작, 1: 끝, 2: 이름, 3: 악보
            tmpMusic = getMusic(tmp[2], tmp[3], m, getRunningTime(tmp[0], tmp[1]), i);  // 이름, 정보, m, 러닝타임, 인덱스
            if (tmpMusic != null) list.add(tmpMusic);
        }
        if (list.isEmpty()) return "(None)";
        Collections.sort(list);
        System.out.println(Arrays.toString(list.toArray()));
        return list.get(0).name;
    }

    private int getRunningTime(String from, String to){
        String[] tmpFrom = from.split(":");
        String[] tmpTo = to.split(":");
        int intFrom = Integer.parseInt(tmpFrom[0]) * 60 + Integer.parseInt(tmpFrom[1]);
        int intTo = Integer.parseInt(tmpTo[0]) * 60 + Integer.parseInt(tmpTo[1]);
        if (intFrom > intTo) return 24 * 60 - intFrom;
        return intTo - intFrom;
    }

    // (1) 문자열을 재생시간에 맞춰 변환
    // (2) 변환된 문자열에 m이 포함되는지 확인
    // (3) 된다면 Music 객체 반환
    private String getOriginMusicInfo(String musicInfo, int runningTime){ // (1)
        StringBuilder sb = new StringBuilder();
        int index = 0; char curr;
        while (runningTime-- > 0){
            curr = musicInfo.charAt(index++);
            sb.append(curr);
            if (index >= musicInfo.length()) {
                index = 0;
            } else if (musicInfo.charAt(index) == '#'){
                sb.append('#'); index++;
                if (index >= musicInfo.length()) index = 0;
            }
        }
        return sb.toString();
    }

    private boolean enableConvertToMusic(String originMusicInfo, String m){ // (2)
        if (m.length() > originMusicInfo.length() || !originMusicInfo.contains(m)) return false;
        for (int i = originMusicInfo.indexOf(m); i < originMusicInfo.length(); ) {
            if (i + m.length() < originMusicInfo.length() && originMusicInfo.charAt(i + m.length()) == '#') {
                i = originMusicInfo.indexOf(m, i + m.length());
                if (i < 0) return false;
            } else return true;
        }
        return false;
    }

    private Music getMusic(String name, String info, String m, int runningTime, int index){ // (3)
        String originMusicInfo = getOriginMusicInfo(info, runningTime);
        return enableConvertToMusic(originMusicInfo, m) ? new Music(name, runningTime, index) : null;
    }

    class Music implements Comparable<Music>{
        String name;
        int runningTime, index;

        Music(String name, int runningTime, int index) {
            this.name = name;
            this.runningTime = runningTime;
            this.index = index;
        }

        @Override
        public int compareTo(Music o) {
            if (this.runningTime == o.runningTime) return Integer.compare(this.index, o.index);
            return -Integer.compare(this.runningTime, o.runningTime);
        }
    }
}