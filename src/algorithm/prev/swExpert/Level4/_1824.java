package algorithm.prev.swExpert.Level4;
import java.io.*;
import java.util.StringTokenizer;

public class _1824 {
    static int w, h, direction, mem;
    static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};    // l, r, u, d
    static int[][] map2;
    static char[][] map;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[w][];

            int sibal = 0;
            for (int i = 0; i < w; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < h; j++) {
                    if(map[i][j] == '?') sibal++;
                }
            }

            result = "NO";
            sibal = sibal == 0 ? 1 : sibal * 5;
            while(sibal-- > 0) {
                map2 = new int[w][h];
                direction = 1; mem = 0;
                int x = 0, y = 0;
                while (true) {
                    char current = map[x][y];
                    switch (current) {
                        case '<': direction = 0; break;
                        case '>': direction = 1; break;
                        case '^': direction = 2; break;
                        case 'v': direction = 3; break;
                        case '_': direction = mem == 0 ? 1 : 0; break;
                        case '|': direction = mem == 0 ? 3 : 2; break;
                        case '?': direction = (int) (Math.random() * 4); break;
                        case '.': break;
                        case '@': result = "YES"; break;
                        case '+': mem += mem == 15 ? -15 : 1; break;
                        case '-': mem -= mem == 0 ? -15 : 1; break;
                        default: mem = current - '0'; break;
                    }
                    if (current == '@') break;
                    map2[x][y]++;
                    if (Math.abs(map2[x][y]) > 15) break;

                    int nx = dir[direction][0] + x;
                    int ny = dir[direction][1] + y;

                    if (nx >= w) nx = 0;
                    if (nx < 0) nx = w - 1;
                    if (ny >= h) ny = 0;
                    if (ny < 0) ny = h - 1;

                    x = nx;
                    y = ny;
                }
                if(result.equals("YES")) break;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}