package ds_algo_study.bst;


public class TreeNode {
    static int cnt = 1;
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode() {
        this.val = cnt;
        cnt++;
    }
    
    @Override
    public String toString() {
        if (left == null && right == null) return "Node-" + String.valueOf(val);
        return left + " // " + "Node-" + this.val + " // " + right;
    }
    
    public static TreeNode generate() {
        TreeNode n1 = new TreeNode();
        TreeNode n2 = new TreeNode();
        TreeNode n3 = new TreeNode();
        TreeNode n4 = new TreeNode();
        TreeNode n5 = new TreeNode();
        TreeNode n6 = new TreeNode();
        TreeNode n7 = new TreeNode();
        
        n2.left = n1;
        n2.right = n3;
        n4.left = n2;
        n4.right = n6;
        n6.left = n5;
        n6.right = n7;
        
        return n4;
    }
}
