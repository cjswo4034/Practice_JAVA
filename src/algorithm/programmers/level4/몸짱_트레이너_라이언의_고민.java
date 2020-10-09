package algorithm.programmers.level4;

import java.util.ArrayList;
import java.util.List;

public class 몸짱_트레이너_라이언의_고민 {
    public static void main(String[] args) {
        몸짱_트레이너_라이언의_고민 prob = new 몸짱_트레이너_라이언의_고민();
        for (int i = 1; i <= 10; i++) {
            prob.n = i;
            int len = (i % 2 == 1) ? i * i / 2 + 1 : i * i / 2;

            System.out.println(i + " ~ " + len);
            for (int j = 2; j <= len; j++) {
                System.out.print(prob.binarySearch(j, 2, i * i - 2) + " ");
            }
            System.out.println();
        }
    }

    static final int INF = 987654321;
    int n;

    /**
     * @param n         정 사각형 한 변의 길이.
     * @param m         손님 수.
     * @param timetable 손님의 예약된 입실/퇴실 시간.
     * @return 최대한 떨어뜨렸을 때 최소거리.
     */
    public int solution(int n, int m, int[][] timetable) {
        this.n = n;
        int maxCnt = 0;  // 같은 시간 대에 락커를 사용중인 사람의 최대 수

        List<Integer> timeIn = new ArrayList<>();
        List<Integer> timeOut = new ArrayList<>();
        for (int[] tt: timetable) {
            timeIn.add(tt[0]);
            timeOut.add(tt[1]);
        }
        timeIn.sort(Integer::compare);
        timeOut.sort(Integer::compare);

        int inIdx = 0, outIdx = 0, p = 0;
        while (inIdx < m) {
            if (timeIn.get(inIdx) <= timeOut.get(outIdx)) {
                inIdx++;
                if (maxCnt < ++p) maxCnt = p;
            } else {
                outIdx++; p--;
            }
        }

        int mid = (n % 2 == 1) ? (n + 1) * n / 2 : n * n / 2;
        if (maxCnt < 2) return 0;
        if (maxCnt > mid) return 1;
        return binarySearch(maxCnt, 0, n * n - 2);
    }

    public int binarySearch(int cnt, int l, int r) {
        int res = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;

            if (getMaximumDistance(cnt, 0, 0, mid, new ArrayList<>())) {
                l = mid + 1;
                res = Math.max(res, mid);
            } else r = mid - 1;
        }
        return res;
    }

    /**
     * 사람들에게 락커를 최대한 떨어뜨려서 할당할 때 락커의 최소거리
     *
     * @param cnt     락커를 할당해야되는 사람의 수
     * @param row     조사중인 락커의 행
     * @param col     조사중인 락커의 열
     * @param minDist 할당될 락커의 최소 거리
     * @param points  사람들에게 할당된 락커의 좌표
     */
    public boolean getMaximumDistance(int cnt, int row, int col, int minDist, List<Point> points) {
        // 1. cnt가 0이 될 때만 true
        if (cnt == 0) return true;

        // 2. 남은 락커에 minDist를 유지하면서 cnt만큼 할당할 수 없을 때 (남은 좌표의 개수 x 최소거리 > 남은 칸 return)
        if (n * n - n * row + col < (cnt - 1) * minDist) return false;

        // 3. row가 범위를 넘어서면 False
        if (row == n) return false;

        // 4. col이 범위를 넘어서면 function(row + 1, 0)부터 탐색
        if (col == n) return getMaximumDistance(cnt, row + 1, 0, minDist, points);

        // 사람들에게 할당한 락커가 없거나
        // 지금 락커를 할당 했을 때 락커 사이 거리의 최소값이 minDist 보다 크면 락커를 할당하고 다음 탐색
        points.add(new Point(row, col));
        int distance = getMaximumDistance(points);
        if (distance == INF) distance = 0;
        if ((points.size() == 1 || distance >= minDist) && (getMaximumDistance(cnt - 1, row, col + 1, minDist, points)))
            return true;
        if (points.size() > 1) {
            points.remove(points.size() - 1);
            return getMaximumDistance(cnt, row, col + 1, minDist, points);
        }
        return false;
    }

    public int getMaximumDistance(List<Point> points) {
        int res = INF, tmp;
        Point lastP = points.get(points.size() - 1);
        for (int i = 0; i < points.size() - 1; i++) {
            tmp = lastP.getDistance(points.get(i));
            if (res > tmp) res = tmp;
        }
        return res == INF ? 0 : res;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int getDistance(Point p) {
            return Math.abs(r - p.r) + Math.abs(c - p.c);
        }
    }
}