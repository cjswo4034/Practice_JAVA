package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* [Platinum 5] 그리디, 정렬
 * 1. s > e인 경우 (0번 이전 정거장에서 출발하여 0번 이후 정거장에서 도착)
 *    - s 오름차순, e 내림차순으로 정렬한 뒤 이전 버스 노선의 도착지들 중 가장 큰 값보다 같거나 작으면 X
 * 2. s < e인 경우 (출발지와 도착지가 모두 0번 이후)
 *    - s 오름차순, e 내림차순으로 정렬한 뒤 이전 버스 노선의 도착지들 중 가장 큰 값보다 같거나 작으면 X
 *    - 출발지가 1의 노선에서 가장 작은 출발지보다 같거나 크다면 X
 *    - 도착지가 1의 노선에서 가장 큰 도착지보다 같거나 작다면 X
 * */
public class 버스노선_10165 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        RangeComparator rangeComparator = new RangeComparator();
        List<Range> list1 = new ArrayList<>();  // s < e
        List<Range> list2 = new ArrayList<>();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int minS = Integer.MAX_VALUE, maxE = Integer.MIN_VALUE, lastE = -1;
        for (int i = 1, s, e; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            if (s < e) list1.add(new Range(i, s, e));
            else {
                if (minS > s) minS = s;
                if (maxE < e) maxE = e;
                list2.add(new Range(i, s, e + n));
            }
        }

        list1.sort(rangeComparator);
        list2.sort(rangeComparator);

        List<Integer> ans = new ArrayList<>();
        for (Range range: list1) {
            if (minS <= range.s) continue;
            if (maxE >= range.e || lastE >= range.e) continue;

            lastE = range.e;
            ans.add(range.idx);
        }

        lastE = -1;
        for (Range range: list2) {
            if (range.e <= lastE) continue;
            lastE = range.e;
            ans.add(range.idx);
        }

        ans.sort(Integer::compareTo);

        StringBuilder sb = new StringBuilder();
        for (Integer e: ans) sb.append(e).append(" ");
        System.out.print(sb);
    }

    static class RangeComparator implements Comparator<Range> {
        @Override
        public int compare(Range r1, Range r2) {
            if (r1.s != r2.s) return Long.compare(r1.s, r2.s);
            return -Long.compare(r1.e, r2.e);
        }
    }

    static class Range {
        int s, e, idx;

        public Range(int idx, int s, int e) {
            this.idx = idx;
            this.s = s;
            this.e = e;
        }
    }
}
