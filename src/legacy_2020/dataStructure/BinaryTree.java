package dataStructure;

import java.util.Arrays;

public class BinaryTree {
    TreeNode root;

    public BinaryTree(){}

    public BinaryTree(int[] elements){
        this.allAll(elements);
    }

    public void allAll(int[] elements){
    }

    public TreeNode add(int[] elements, int l, int r){
        if (l > r) {
            return null;
        }
        int m = (l + r) / 2;
        TreeNode treeNode = new TreeNode(m);
        treeNode.l = add(elements, l, m - 1);
        treeNode.r = add(elements, m + 1, r);
        return treeNode;
    }

    private int getLength(int length){
        int res = 1;
        while (length > res){
            res <<= 1;
        }
        return res;
    }

    class TreeNode {
        TreeNode l, r;
        int value;

        TreeNode(int value){
            this.value = value;
        }
    }
}
