package ds_algo_study.bst;

/**
 * Validate if 2 BSTs can be converted to each other.
 * 
 * [Note]
 * Think about current node first.
 * Defer the rest using recursion.
 * 
 * @author Sunny Park
 */
public class Q04_ConvertBST {
    public static boolean validateBST(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            return n1 == null && n2 == null;
        }
        
        if (n1.val != n2.val) return false;
        return (validateBST(n1.left, n2.left) && validateBST(n1.right, n2.right)) 
                || validateBST(n1.left, n2.right) && validateBST(n1.right, n2.left);
    }
}
