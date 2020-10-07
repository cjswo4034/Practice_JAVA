package algorithm.boj.sliver;

import java.util.Scanner;

// Silver_1
public class Special_Christmas_Tree_11805 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            sb.append("Case ").append(i).append(": ");
            long h = sc.nextLong();
            long l = sc.nextLong();
            sb.append(solve1(h, l)).append(" : ").append(solve2(h, l));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long solve1(long h, long l) {
        if (h == 1) return l == 1 ? 2 : 3;
        else return (h - 1) * l + 3;
    }

    private static long solve2(long h, long l) {
        if (l == 1) return h + 1;
        long i = 0, s = 1, s1 = 0, s2 = 0;
        for (i = 2; i <= l; i *= 2) {
            if (i == 2) s1 = (h - s2) * (i - i / 2 + 1);
            else s1 = (h - s2) * (i - i / 2);
            s += s1;
            s2++;
        }
        long q;
        if (l == i / 2) q = 0;
        else q = l - i / 2;
        s += q * (h - s2);
        return s;
    }
}
