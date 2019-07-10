package ds_algo_study.bst.type3_RankFromStream;

/**
 * 
 * RankFromStream.
 * Q. Scores are updated repeatedly. 
 * We want to know the rank which should be updated accordingly each time.
 * How to design the ds?
 * 
 * Data structures I could have used: 
 *              insert (keeping sorted status)          getRank       
 * Array            O(N)                                O(logN) using binary search
 * LinkedList       O(N)                                O(N)    can't do binary search on LL
 * HashMap          O(1)                                O(N)    anyway no order in HM.
 * BST              O(logN)                             O(logN) (assuming it's balanced)
 *                  ^ If I keep all ranks, it might have been O(N).
 *                    logN by keeping the left size only.
 * @author Sunny Park
 *
 */
public class RankFromStream {
    private static RankNode root = null;
    
    public static void track(int x) {
        if (root == null) {
            root = new RankNode(x);
        } else {
            insert(x, root);
        }
    }
    
    /**
     * insert keeping leftSize.
     * compare (node vs x)
     *  1. if x <= node.val -> to the left 
     *  2. if x > node.val -> to the right
     * @param x
     * @param node
     */
    
    private static void insert(int x, RankNode node) {
        if (x <= node.val) {
            node.leftSize++;
            if (node.left == null) {
                node.left = new RankNode(x);
                return;
            } 
            insert(x, node.left);
        } else {
            if (node.right == null) {
                node.right = new RankNode(x);
                return;
            } 
            insert(x, node.right);
        }
    }
    
    public static int getRank(int x) {
        return getRank(x, root);
    }
    
    /**
     * determine based on the comparison.
     * 1. x == node.val -> 1 + curr left size
     * 2. x < node.val -> next call
     * 3. x > node.val -> 1 + curr left size + next call
     * 
     * @param x
     * @param node
     * @return
     */
    private static int getRank(int x, RankNode node) {
        if (node == null) {
            return 0;
        } else if (x == node.val) {
            return 1 + node.leftSize;
        } else if (x < node.val) {
            return getRank(x, node.left);
        } else {
            return 1 + node.leftSize + getRank(x, node.right);
        }
    }
}
