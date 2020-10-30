package algorithm;

import java.io.*;
import java.util.*;

public class util {
    static ArrayList<Point> Firstshot = new ArrayList<>();
    static ArrayList<Point> Secondshot = new ArrayList<>();
    static Point[] enemy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        enemy = new Point[N];
        String s = "success";
        String f = "failure";

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            enemy[i] = new Point(x, y);
        }
        if (N > 4) {
            Ready2Roll();
            if (Secondshot.isEmpty())
                System.out.println(f);
            else {
                for (int i = 5; i < N; i++) {
                    if (CCW(Secondshot.get(0), Secondshot.get(1), enemy[i])) {
                        continue;
                    }
                    if (Firstshot.size() < 2) {
                        Firstshot.add(enemy[i]);
                        continue;
                    } else {
                        if (CCW(Firstshot.get(0), Firstshot.get(1), enemy[i])) {
                            continue;
                        }
                    }
                    System.out.println(f);
                    break;
                }
                System.out.println(s);
            }

        } else System.out.println(s);
    }

    private static void Ready2Roll() {
        int save1 = -1, save2 = -1, save3 = -1;
        out: for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 5; k++) {
                    if (CCW(enemy[i], enemy[j], enemy[k])) {
                        Secondshot.add(enemy[i]);
                        Secondshot.add(enemy[j]);
                        Secondshot.add(enemy[k]);
                        save1 = i;
                        save2 = j;
                        save3 = k;
                        break out;
                    }
                }
            }

        }
        if (!Secondshot.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                if (i != save1 || i != save2 || i != save3) {
                    if (!CCW(Secondshot.get(0), Secondshot.get(1), enemy[i]))
                        Firstshot.add(enemy[i]);
                }
            }
        }

    }

    private static boolean CCW(Point p1, Point p2, Point p3) {
        long S = p1.getX() * p2.getY() + p2.getX() * p3.getY() + p3.getX() * p1.getY();
        S -= p1.getY() * p2.getX() + p2.getY() * p3.getX() + p3.getY() * p1.getX();

        System.out.println(p1 + " : " + p2 + " : " + p3 + " : " + " = " + S);
        if (S == 0)
            return true;
        return false;
    }

    private static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}