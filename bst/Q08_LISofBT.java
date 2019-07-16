package ds_algo_study.bst;

import com.google.common.primitives.Ints;

/**
 * Longest Increasing Sequence of Binary Tree.
 *      1
 *    2   3
 *  4       5
 *            6   => 4 (depth of the specific path with LIS)  
 * 
 * @author Sunny Park
 */
public class Q08_LISofBT {
    public static int findLIS(TreeNode root) {
        return findLIS_consecutive(root, Integer.MIN_VALUE, 0);
    }
    
    private static int findLIS(TreeNode node, int prevVal, int len) {
        if (node == null) {
            return len;
        }
        
        if (node.val <= prevVal) {
            return Ints.max(len, findLIS(node.left, node.val, 1), 
                    findLIS(node.right, node.val, 1));
        }
        
        return Math.max(findLIS(node.left, node.val, len + 1), 
                findLIS(node.right, node.val, len + 1));
    }
    
    private static int findLIS_consecutive(TreeNode node, int prevVal, int len) {
        if (node == null) {
            return len;
        }
        
        if (node.val != prevVal + 1) {
            return Ints.max(len, findLIS_consecutive(node.left, node.val, 1), 
                    findLIS_consecutive(node.right, node.val, 1));
        }
        
        return Math.max(findLIS_consecutive(node.left, node.val, len + 1), 
                findLIS_consecutive(node.right, node.val, len + 1));
    }
    
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        
        TreeNode n11 = new TreeNode(1);
        TreeNode n22 = new TreeNode(2);
        TreeNode n33 = new TreeNode(3);
        TreeNode n44 = new TreeNode(4);
        TreeNode n55 = new TreeNode(5);
        
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.right = n5;
        n5.right = n6;
        
        n6.left = n11;
        n11.left = n22;
        n22.right = n33;
//        n33.right = n44;
//        n44.right = n55;
        
        System.out.println(findLIS(n1));
        
    }
}
