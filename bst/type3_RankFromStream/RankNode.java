package ds_algo_study.bst.type3_RankFromStream;

public class RankNode {
    final int val;
    RankNode left;
    RankNode right;
    int leftSize;
    
    public RankNode(int val) {
        this.val = val;
        leftSize = 0;
    }
}
