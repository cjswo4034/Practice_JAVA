package algorithm.prev.programmers.Level3;

import java.util.*;
import java.util.stream.IntStream;

public class _42579 {
    /*
    public class Music implements Comparable<Music>{
        private int played;
        private int id;
        private String genre;

        public Music(String genre, int played, int id) {
            this.genre = genre;
            this.played = played;
            this.id = id;
        }

        @Override
        public int compareTo(Music other) {
            if(this.played == other.played) return this.id - other.id;
            return other.played - this.played;
        }

        public String getGenre() {return genre;}
    }

    public int[] solution(String[] genres, int[] plays) {
        return IntStream.range(0, genres.length)
                .mapToObj(i -> new Music(genres[i], plays[i], i))
                .collect(Collectors.groupingBy(Music::getGenre))
                .entrySet().stream()
                .sorted((a, b) -> sum(b.getValue()) - sum(a.getValue()))
                .flatMap(x->x.getValue().stream().sorted().limit(2))
                .mapToInt(x->x.id).toArray();
    }

    private int sum(List<Music> value) {
        int answer = 0;
        for (Music music : value) {
            answer+=music.played;
        }
        return answer;
    }*/

    public static void main(String[] args) {
        _42579 problem = new _42579();
        System.out.println(Arrays.toString(problem.solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 500, 800, 2500})));
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreIdx = new HashMap<>();
        Map<String, Integer> genreCnt = new HashMap<>();
        for (int i = 0, idx = 0; i < genres.length; i++) {
            if (genreCnt.containsKey(genres[i]))
                genreCnt.put(genres[i], genreCnt.get(genres[i]) + plays[i]);
            else {
                genreCnt.put(genres[i], plays[i]);
                genreIdx.put(genres[i], idx++);
            }
        }

        int idx = 0;
        ArrayList<Entry>[] list = new ArrayList[genreIdx.keySet().size()];
        for (int i = 0; i < genreIdx.keySet().size(); i++)
            list[i] = new ArrayList<>();

        IntStream.range(0, genres.length)
                .mapToObj(i -> new Entry(genres[i], plays[i], i))
                .map(entry -> {
                    list[genreIdx.get(entry.genre)].add(entry);
                    return list[genreIdx.get(entry.genre)];
                })
                .forEach(Collections::sort);

        Entry[] entries = new Entry[genreCnt.keySet().size()];
        for (Map.Entry<String, Integer> entry : genreCnt.entrySet()){
            entries[idx++] = new Entry(entry.getKey(), entry.getValue(), genreIdx.get(entry.getKey()));
        }
        Arrays.sort(entries);

        int answerSize = 0;
        for (ArrayList<Entry> entryArrayList : list) {
            answerSize += Math.min(entryArrayList.size(), 2);
        }

        int[] answer = new int[answerSize];
        int currGenreIdx, answerIdx = 0;
        for (Entry entry : entries){
            currGenreIdx = genreIdx.get(entry.genre);
            for (int i = 0, leng = Math.min(list[currGenreIdx].size(), 2); i < leng; i++) {
                answer[answerIdx++] = list[currGenreIdx].get(i).idx;
            }
        }

        return answer;
    }

    static class Entry implements Comparable<Entry> {
        String genre;
        int plays;
        int idx;

        Entry(String genre, int plays, int idx) {
            this.genre = genre;
            this.plays = plays;
            this.idx = idx;
        }

        public int compareTo(Entry entry){
            if (this.plays == entry.plays) return this.genre.compareTo(entry.genre);
            return -Integer.compare(this.plays, entry.plays);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "genre='" + genre + '\'' +
                    ", plays=" + plays +
                    ", idx=" + idx +
                    '}';
        }
    }
}
