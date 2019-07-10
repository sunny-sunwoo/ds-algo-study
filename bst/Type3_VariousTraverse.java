package ds_algo_study.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.google.common.base.Joiner;

public class Type3_VariousTraverse {
    /**
     *   a
     *  b c
     * d e f
     * 
     * print: (a) -> (c, b) -> (d, e, f)
     * 
     * @param root
     */
    public static void zigzagBST(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> curr = new Stack<>();
        Stack<TreeNode> next = new Stack<>();
        curr.push(root);
        zigzag(result, curr, next, true);
        printList(result);
    }
    
    private static void zigzag(List<List<Integer>> result, Stack<TreeNode> curr, Stack<TreeNode> next, boolean isLeft) {
        if (curr.isEmpty()) return;
        List<Integer> currLv = new ArrayList<>();
        while (!curr.isEmpty()) {
            TreeNode top = curr.pop();
            if (top == null) continue;
            currLv.add(top.val);
            next.push(isLeft ? top.left : top.right);
            next.push(isLeft? top.right : top.left);
        }
        
        if (!currLv.isEmpty()) {
            result.add(currLv);
        }
        zigzag(result, next, curr, !isLeft);
    }
    
    private static void printList(List<List<Integer>> result) {
        System.out.println(Joiner.on("->").join(result));
    }
    
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode();
        TreeNode n2 = new TreeNode();
        TreeNode n3 = new TreeNode();
        TreeNode n4 = new TreeNode();
        TreeNode n5 = new TreeNode();
        TreeNode n6 = new TreeNode();
        TreeNode n7 = new TreeNode();
        
        n2.left = n1;
        n2.right = null;
        n4.left = n2;
        n4.right = n6;
        n6.left = null;
        n6.right = n7;
        
        
        zigzagBST(n4);
    }
}
