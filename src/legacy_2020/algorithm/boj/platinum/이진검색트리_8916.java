package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/* [Platinum 4] 이진트리탐색, 조합
 * - 순열에서 루트의 위치가 하위노드보다 먼저 나오기만 하면 됨.
 * - 그 외에는 신경 쓸 필요없음.
 * - 따라서, 어떤 노드의 조합 * 왼쪽 노드의 조합 * 오른쪽 노드의 조합
 * */
public class 이진검색트리_8916 {
    static long[][] binom = new long[20][20];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Binarytree root;
        for (int i = 0; i < 20; i++) Arrays.fill(binom[i], -1);
        for (int i = 0; i < 20; i++) for (int j = 0; j <= i; j++) setBinom(i, j);

        int t = Integer.parseInt(br.readLine());
        for (int i = 0, n; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            root = new Binarytree(Integer.parseInt(st.nextToken()));
            for (int j = 1; j < n; j++) {
                root.insert(new Binarytree(Integer.parseInt(st.nextToken())));
            }

            root.postOrder();
            bw.write(root.combination + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void setBinom(int n, int r) {
        if (binom[n][r] != -1) return;
        if (n == r || r == 0) binom[n][r] = 1;
        else {
            setBinom(n - 1, r - 1);
            setBinom(n - 1, r);
            binom[n][r] = binom[n - 1][r - 1] + binom[n - 1][r];
        }
    }

    static class Binarytree {
        private final int value;
        private int elements;
        private long combination;
        private Binarytree right, left;

        public Binarytree(int value) {
            this.value = value;
            this.elements = 1;
        }

        public void insert(Binarytree binarytree) {
            if (binarytree.value < this.value) {
                if (left == null) left = binarytree;
                else left.insert(binarytree);
            } else {
                if (right == null) right = binarytree;
                else right.insert(binarytree);
            }
            this.elements++;
        }

        public void postOrder() {
            if (left != null) left.postOrder();
            if (right != null) right.postOrder();
            this.calcualteCombi();
        }

        public void calcualteCombi() {
            if (left == null && right == null) this.combination = 1;
            else if (left == null) this.combination = right.combination;
            else if (right == null) this.combination = left.combination;
            else {
                long temp = binom[right.elements + left.elements][right.elements];
                this.combination = (temp * left.combination * right.combination) % 9999991;
            }
        }
    }
}