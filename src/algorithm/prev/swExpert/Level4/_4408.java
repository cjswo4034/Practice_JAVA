package algorithm.prev.swExpert.Level4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _4408 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        boolean[] check;
        LinkedList<Point> list;
        int T = Integer.parseInt(br.readLine().trim()), n;
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine().trim());
            list = new LinkedList<>();
            check = new boolean[n];

            int from, to;
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                from = from % 2 == 0 ? from / 2 : (from + 1) / 2;
                to = to % 2 == 0 ? to / 2 : (to + 1) / 2;
                list.add(from <= to ? new Point(from, to) : new Point(to, from));
            }

            list.sort((o1, o2) -> {
                if (o1.x == o2.x) return Integer.compare(o1.y, o2.y);
                return Integer.compare(o1.x, o2.x);
            });

            int end, count = 0;
            for (int i = 0; i < n; i++) {
                if (!check[i]) {
                    end = list.get(i).y;
                    for (int j = i + 1; j < n; j++) {
                        if (check[j]) continue;
                        if (end < list.get(j).x){
                            check[j] = true;
                            end = list.get(j).y;
                        }
                    }
                    count++;
                }
            }

            sb.append(count).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}