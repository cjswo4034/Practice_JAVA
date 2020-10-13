package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* [Gold 2]
 * 물통 a, b를 이용하여 물통 a, b 내의 물의 양을 c, d로 맞춘다.
 * - 물통의 용량. 1 <= a < b <= 100,000
 * - 가능한 명령: Fill x, Empty x, Move x to y
 * 1. 각각의 명령들을 수로 표현한다.
 * 2. 물의 용량이 같으면 중복으로 표기한다.
 * 3. 어떤 차례(step)에 물의 용량이 같으면 pass
 * ==>> 그래프 탐색으로 가능
 * */
public class 물통_14867 {
    static int volumeA, volumeB, c, d;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        volumeA = Integer.parseInt(st.nextToken());
        volumeB = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        Bucket bucketA = new Bucket(0, volumeA);
        Bucket bucketB = new Bucket(0, volumeB);

        System.out.println(bfs(c, d));
        System.out.println(bfs(bucketA, bucketB, c, d));
    }

    static int bfs(int c, int d) {
        Queue<Pair> q = new LinkedList<>();
        Set<Pair> set = new HashSet<>();
        int step = 0;

        q.add(new Pair(0, 0));
        set.add(new Pair(0, 0));

        Pair cur, next;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                cur = q.poll();
                if (cur.a == c && cur.b == d) return step;

                for (int cmd = 0; cmd < 6; cmd++) {
                    next = command(cmd, cur.a, cur.b);

                    if (set.contains(next)) continue;

                    set.add(next);
                    q.add(next);
                }
            }
            step++;
        }

        return -1;
    }

    static Pair command(int cmd, int aa, int bb) {
        switch (cmd) {
            case 0: return new Pair(volumeA, bb);
            case 1: return new Pair(aa, volumeB);
            case 2: return new Pair(0, bb);
            case 3: return new Pair(aa, 0);
            case 4: return aa <= volumeB - bb ? new Pair(0, aa + bb) : new Pair(aa - (volumeB - bb), volumeB);
            default: return bb <= volumeA - aa ? new Pair(aa + bb, 0) : new Pair(volumeA, bb - (volumeA - aa));
        }
    }

    static class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            Pair pair = (Pair) o;
            if (a != pair.a) return false;
            return b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    // 객체 지향
    static int bfs(Bucket bucketA, Bucket bucketB, int c, int d) {
        Queue<BucketPair> q = new LinkedList<>();
        Set<BucketPair> set = new HashSet<>();
        BucketPair cur = new BucketPair(bucketA, bucketB), next;
        int step = 0;

        set.add(cur);
        q.add(cur);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                cur = q.poll();
                if (cur.a.amount == c && cur.b.amount == d) return step;

                for (int cmd = 0; cmd < 6; cmd++) {
                    next = command(cmd, cur);

                    if (set.contains(next)) continue;

                    set.add(next);
                    q.add(next);
                }
            }
            step++;
        }

        return -1;
    }

    static BucketPair command(int cmd, BucketPair bp) {
        BucketPair res;
        boolean targetIsA = cmd % 2 == 0;
        if (cmd < 2) res = bp.fill(targetIsA);
        else if (cmd < 4) res = bp.empty(targetIsA);
        else res = bp.move(targetIsA);
        return res;
    }

    static class BucketPair {
        Bucket a, b;

        public BucketPair(Bucket a, Bucket b) {
            this.a = a;
            this.b = b;
        }

        public BucketPair fill(boolean isA) {
            BucketPair res = copy();
            if (isA) res.a.fill();
            else res.b.fill();
            return res;
        }

        public BucketPair empty(boolean isA) {
            BucketPair res = copy();
            if (isA) res.a.empty();
            else res.b.empty();
            return res;
        }

        public BucketPair move(boolean isAtoB) {
            BucketPair res = copy();
            if (isAtoB) res.a.move(res.b);
            else res.b.move(res.a);
            return res;
        }

        public BucketPair copy() {
            return new BucketPair(new Bucket(a), new Bucket(b));
        }

        @Override
        public boolean equals(Object o) {
            BucketPair that = (BucketPair) o;
            if (a.amount != that.a.amount) return false;
            return b.amount == that.b.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a.amount, b.amount);
        }
    }

    static class Bucket {
        int amount, volume;  // 물통에 있는 물의 양, 물통의 크기

        public Bucket(Bucket bucket) {
            this(bucket.amount, bucket.volume);
        }

        public Bucket(int amount, int volume) {
            this.amount = amount;
            this.volume = volume;
        }

        private void fill() {
            amount = volume;
        }

        private void empty() {
            amount = 0;
        }

        /**
         * remain: 물통 to에 채울 수 있는 물의 양
         * - 물통에 채울 수 있는 물의 양이 현재 물통에 있는 물의 양보다 같거나 많다면
         *   1) 물통 to에 현재 물통에 있는 물의 양을 더한다. 2) 현재 물통을 비운다.
         * - 물통에 채울 수 있는 물의 양이 현재 물통에 있는 물의 양보다 적다면
         *   1) 물통 to를 꽉 채운다. 2) 현재 물통의 물을 remain만큼 비운다.
         * @param to 옮길 물통
         */
        private void move(Bucket to) {
            int remain = to.volume - to.amount;
            if (remain >= amount) {
                to.amount += amount;
                empty();
            } else {
                amount -= remain;
                to.fill();
            }
        }
    }
}
