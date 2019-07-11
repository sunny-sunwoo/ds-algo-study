package ds_algo_study.bst;

/**
 * sometimes, iterative is easier than recursive with BST.
 * Q. find successor of the given node!
 * 
 * [Approach1] Recursive
 * 1. inorder traversal -> ascending order.
 * 2. find next
 * 
 * [Approach2] Iterative
 * 1. find the key.
 * 2. to the right once.
 * 3. to the left down to null.
 * 
 * => while loop can have 3 cases.
     * 1) found the key
     * 2) key is greater than curr
     * 3) key is less than curr
 * 
 * @author Sunny Park
 *
 */
public class Type5_Iterative {
    public static TreeNode bstSuccessor(TreeNode root, TreeNode key) {
        TreeNode curr = root;
        TreeNode next = null;
        boolean foundK = false;
        
        while (curr != null) {
            if (curr == key) {
                foundK = true;
                curr = curr.right;
            } else if (curr.val < key.val) { // to the right
                curr = curr.right;
            } else { // to the left
                next = curr;
                curr = curr.left;
            }
        }
        return foundK ? next : null;
    }
    
    public static void main(String[] args) {
        TreeNode root = TreeNode.generate();
        System.out.println(bstSuccessor(root, root));
    }
}
