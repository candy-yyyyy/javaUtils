import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.val = 2;
        TreeNode treeNode1 = new TreeNode();
        treeNode1.val = 3;
        treeNode.right = treeNode1;
        TreeNode treeNode2 = new TreeNode();
        treeNode2.val = 1;
        treeNode.left = treeNode2;
        TreeNode treeNode3 = new TreeNode();
        treeNode3.val = 1;
        treeNode2.left = treeNode3;
        Queue<TreeNode> queue=new LinkedList<>();//建立层次遍历的队列
        queue.add(treeNode);
        System.out.println(queue.poll());
        System.out.println(queue.size());

    }
}
