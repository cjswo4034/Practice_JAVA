package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// gold 2
public class 인간대포_10473 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double fromX = Double.parseDouble(st.nextToken());
        double fromY = Double.parseDouble(st.nextToken());
        double [] from = new double[]{fromX, fromY};

        st = new StringTokenizer(br.readLine());
        double toX = Double.parseDouble(st.nextToken());
        double toY = Double.parseDouble(st.nextToken());
        double [] to = new double[]{toX, toY};
        int n = Integer.parseInt(br.readLine());

        double [] dist = new double[n + 2];
        Arrays.fill(dist, 10000000);

        double [][] arr = new double[n][2];
        double [][] diff = new double[n + 2][n + 2];
        diff[0][n + 1] = getDiffWalking(from, to);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Double.parseDouble(st.nextToken());
            arr[i][1] = Double.parseDouble(st.nextToken());
            diff[0][i + 1] = getDiffWalking(from, arr[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++){
                if (j == n) diff[i + 1][j + 1] = diff[j + 1][i + 1] = Math.min(getDiffWalking(arr[i], to), getDiffCannon(arr[i], to));
                else diff[i + 1][j + 1] = diff[j + 1][i + 1] = Math.min(getDiffWalking(arr[i], arr[j]), getDiffCannon(arr[i], arr[j]));
            }
        }

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingDouble(Point::getDistance));
        pq.add(new Point(0, 0.0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            for (int i = 1; i < n + 2; i++) {
                if (dist[i] > curr.distance + diff[curr.node][i]) {
                    dist[i] = curr.distance + diff[curr.node][i];
                    pq.add(new Point(i, dist[i]));
                }
            }
        }

        System.out.printf("%.6f\n", dist[n + 1]);
    }

    private static double getDiffCannon(double[] x, double[] y) {
        return Math.abs(Math.sqrt((x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1])) - 50) / 5 + 2;
    }

    private static double getDiffWalking(double[] x, double[] y) {
        return Math.sqrt((x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1])) / 5;
    }

    static class Point {
        int node;
        double distance;

        public Point(int node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        public double getDistance() {
            return distance;
        }
    }
}
