package algorithm.prev.programmers.kakaoRecruit_2018;

import java.util.Arrays;

public class 길찾기게임 {
    static int pre, post;
    public static void main(String[] args) {
        길찾기게임 prob = new 길찾기게임();
        int[][] ans = prob.solution(new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
        for (int[] arr : ans){
            System.out.println(Arrays.toString(arr));
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++)
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        Arrays.sort(nodes);

        Node head = nodes[0];
        for (int i = 1; i < nodes.length; i++) head.insertNode(nodes[i]);

        int[][] answer = new int[2][nodeinfo.length];
        getPreOrder(head, answer);
        getPostOrder(head, answer);

        return answer;
    }

    private void getPreOrder(Node node, int[][] answer){
        if (node != null){
            answer[0][pre++] = node.idx;
            getPreOrder(node.left, answer);
            getPreOrder(node.right, answer);
        }
    }

    private void getPostOrder(Node node, int[][] answer){
        if (node != null){
            getPostOrder(node.left, answer);
            getPostOrder(node.right, answer);
            answer[1][post++] = node.idx;
        }
    }

    class Node implements Comparable<Node>{
        int x, y, idx;
        Node left, right;

        Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        void insertNode(Node node){
            if (this.x > node.x) {
                if (left == null) left = node;
                else left.insertNode(node);
            } else {
                if (right == null) right = node;
                else right.insertNode(node);
            }
        }

        @Override
        public int compareTo(Node o) {
            if (this.y == o.y) return Integer.compare(this.x, o.x);
            return -Integer.compare(this.y, o.y);
        }
    }
}
