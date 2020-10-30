package algorithm.prev.programmers.kakaoRecruit_2018;

import java.util.Arrays;

public class 실패율 {
    public static void main(String[] args) {
        실패율 prob = new 실패율();
        System.out.println(Arrays.toString(prob.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
        System.out.println(Arrays.toString(prob.solution(3, new int[]{2, 2, 2})));
    }

    public int[] solution(int N, int[] inputs) {
        int[] answer = new int[N];

        Stage[] stages = new Stage[N];
        for (int i = 1; i <= N; i++) stages[i - 1] = new Stage(i);

        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0, leng = Math.min(inputs[i], N); j < leng; j++)
                stages[j].increaseQualifierCount();

            if (inputs[i] == N + 1) continue;
            stages[inputs[i] - 1].increaseChallengerCount();
        }

        for (Stage stage : stages) stage.setFailRate();
        Arrays.sort(stages);

        for (int i = 0; i < N; i++) answer[i] = stages[i].idx;

        return answer;
    }

    class Stage implements Comparable<Stage>{
        int idx, challenger, qualifier;
        double failRate;

        Stage(int idx) {
            this.idx = idx;
        }

        void increaseChallengerCount(){
            this.challenger++;
        }

        void increaseQualifierCount(){
            this.qualifier++;
        }

        void setFailRate(){
            if (this.challenger * this.qualifier == 0) this.failRate = 0;
            else this.failRate = (double)this.challenger / this.qualifier;
        }

        @Override
        public int compareTo(Stage o) {
            if (this.failRate == o.failRate) return Integer.compare(this.idx, o.idx);
            return -Double.compare(this.failRate, o.failRate);
        }

        @Override
        public String toString() {
            return "Stage{" +
                    "idx=" + idx +
                    ", challenger=" + challenger +
                    ", qualifier=" + qualifier +
                    ", failRate=" + failRate +
                    '}';
        }
    }
}