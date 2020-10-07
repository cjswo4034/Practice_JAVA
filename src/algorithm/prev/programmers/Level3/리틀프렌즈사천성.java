package algorithm.prev.programmers.Level3;

import java.util.*;

public class 리틀프렌즈사천성 {
    public static void main(String[] args) {
        리틀프렌즈사천성 사천성 = new 리틀프렌즈사천성();
        Solution solution = 사천성.new Solution();
        System.out.println(solution.solution(3, 3, new String[]{"DBA", "C*A", "CDB"}));
        System.out.println(solution.solution(2, 4, new String[]{"NRYN", "ARYA"}));
        System.out.println(solution.solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}));
        System.out.println(solution.solution(2, 2, new String[]{"AB", "BA"}));
        System.out.println(solution.solution(2, 2, new String[]{"A*", "A."}));
        System.out.println(solution.solution(2, 3, new String[]{"A**", "..A"}));
        System.out.println(solution.solution(1, 2, new String[]{"AA"}));
        System.out.println(solution.solution(1, 3, new String[]{"A.A"}));
    }

    class Solution {
        public String solution(int m, int n, String[] board) {
            char[][] map = new char[m][];

            Map<Character, Integer> set = new HashMap<>();
            List<Pair> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                map[i] = board[i].toCharArray();
                for (int j = 0; j < n; j++) {
                    if (Character.isUpperCase(map[i][j])) {
                        if (!set.containsKey(map[i][j])) {
                            set.put(map[i][j], set.size());
                            list.add(new Pair(map[i][j], i, j));
                        } else list.get(set.get(map[i][j])).setSecond(i, j);
                    }
                }
            }

            list.sort(Comparator.comparingInt(p -> p.ch));
            StringBuilder res = new StringBuilder();
            int i = 0;
            while (i < list.size()) {
                Pair p = list.get(i);
                if (!p.visited && isRemovable(p, map)) {
                    res.append(p.ch);
                    checkMap(map, p);
                    i = 0;
                } else i++;
            }
            return res.length() == list.size() ? res.toString() : "IMPOSSIBLE";
        }

        private void checkMap(char[][] map, Pair p) {
            map[p.x1][p.y1] = '.';
            map[p.x2][p.y2] = '.';
            p.visited = true;
        }

        private boolean isRemovable(Pair p, char[][] map) {
            // x1: 위쪽, x2: 아래쪽, y1: 왼쪽, y2: 오른쪽
            int x1 = Math.min(p.x1, p.x2), y1 = Math.min(p.y1, p.y2);
            int x2 = Math.max(p.x1, p.x2), y2 = Math.max(p.y1, p.y2);
            boolean flagY1 = true, flagY2 = true;
            while (x2 >= x1) {
                if (map[x1][y1] != '.' && map[x1][y1] != p.ch) flagY1 = false;
                if (map[x1][y2] != '.' && map[x1][y2] != p.ch) flagY2 = false;
                if (!(flagY1 || flagY2)) return false;  // 둘다 실패
                x1++;
            }

            x1 = Math.min(p.x1, p.x2);
            boolean flagX1 = true, flagX2 = true;
            while (y2 >= y1) {
                if (map[x1][y1] != '.' && map[x1][y1] != p.ch) flagX1 = false;
                if (map[x2][y1] != '.' && map[x2][y1] != p.ch) flagX2 = false;
                if (!(flagX1 || flagX2)) return false;  // 둘다 실패
                y1++;
            }

            if (p.line == 2) return (flagX1 && flagY2) || (flagX2 && flagY1);
            else if (p.line == 1) return (flagX1 && flagY1) || (flagX2 && flagY2);
            return true;
        }

        class Pair {
            boolean visited;
            char ch;
            int line; // 1: 1, 3분면, 2: 2, 4분면
            int x1, y1;
            int x2, y2;

            Pair(char ch, int x1, int y1){
                this.ch = ch;
                this.x1 = x1;
                this.y1 = y1;
            }

            void setSecond(int x2, int y2){
                this.x2 = x2;
                this.y2 = y2;
                setLeftLine();
            }

            void setLeftLine(){
                int a = this.x1 - this.x2;
                int b = this.y1 - this.y2;
                if (a * b > 0) this.line = 2;
                else if (a * b < 0) this.line = 1;
                else this.line = 0;
            }

            @Override
            public String toString() {
                return "Pair{" +
                        ", line=" + line +
                        ", ch=" + ch +
                        ", x1=" + x1 +
                        ", y1=" + y1 +
                        ", x2=" + x2 +
                        ", y2=" + y2 +
                        '}';
            }
        }
    }
}