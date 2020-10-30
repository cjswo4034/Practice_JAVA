package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
* 0. 각각의 구슬 arr[0][i]에 대해서
* 1. 첫 시작위치에서 arr[1][:]를 빼서 등속도 후보를 구한다.
* 2. 각각의 등속도에 대해 arr[t][i] = arr[0][i] + 등속도 * t 가 존재하지 않는 등속도를 제거한다.
* 3. 남은 등속도가 1이 될 때까지 반복한다.
* */

class Solution1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Bead[] beads = new Bead[n];

        // 1. 구슬 i의 처음 위치를 구한다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            beads[i] = new Bead(Integer.parseInt(st.nextToken()));
        }

        // 2. 구슬 i에 대한 등속도를 구한다. (크기가 전부 1이면 종료)
        st = new StringTokenizer(br.readLine());
        for (int i = 0, tmp; i < n; i++) {
            tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) beads[j].speed.add(tmp - beads[j].x);
        }

        // 3. 등속도가 존재하는지 확인한다.
        for (int t = 2; t <= n; t++) {
            st = new StringTokenizer(br.readLine());
            Set<Integer> s, current = new HashSet<>();
            for (int i = 0; i < n; i++) current.add(Integer.parseInt(st.nextToken()));
            for (int i = 0; i < n; i++) {
                if (beads[i].speed.size() == 1) continue;

                s = new HashSet<>();
                for (int e: beads[i].speed)
                    if (current.contains(beads[i].x + e * t)) s.add(e);
                beads[i].speed = intersection(beads[i].speed, s);
            }
        }

        System.out.print(getAnswer(beads));
    }

    private static String getAnswer(Bead[] beads) {
        StringBuilder sb = new StringBuilder();

        Arrays.sort(beads);
        for (Bead bead: beads)
            sb.append(bead).append("\n");
        return sb.toString();
    }

    private static Set<Integer> intersection(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> a, b, res = new HashSet<>();
        if (s1.size() <= s2.size()) {
            a = s1;
            b = s2;
        } else {
            b = s1;
            a = s2;
        }

        for (Integer e: a) {
            if (b.contains(e))
                res.add(e);
        }

        return res;
    }

    static class Bead implements Comparable<Bead>{
        int x;
        Set<Integer> speed = new HashSet<>();

        public Bead(int x) {
            this.x = x;
        }

        @Override
        public int compareTo(Bead o) {
            return Integer.compare(x, o.x);
        }

        @Override
        public String toString() {
            return x + " " + speed.iterator().next();
        }
    }
}

public class 구슬굴리기_1728 {
    static int n;
    static int[][] arr;
    static Bead[] beads;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n];
        beads = new Bead[n];

        for (int i = 0; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            int x = arr[0][i];
            for (int j = 0; j < n; j++) {
                int v = arr[1][j] - x;
                if (check(x, v)) {
                    beads[i] = new Bead(x, v);
                    break;
                }
            }
        }

        Arrays.sort(beads, Comparator.comparingInt(b -> b.x));

        for (Bead bead: beads) sb.append(bead).append("\n");
        System.out.println(sb);
    }

    static boolean check(int s, int v) {
        for (int t = 2; t <= n; t++) {
            for (int i = 0; i < n; i++) {
                if (s + v * t == arr[t][i]) break;

                if (i == n - 1) return false;
            }
        }
        return true;
    }

    static class Bead {
        int x, v;

        public Bead(int x, int v) {
            this.x = x;
            this.v = v;
        }

        @Override
        public String toString() {
            return x + " " + v;
        }
    }
}