package ds_algo_study.bst.type3_RankFromStream;

import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        System.out.println(Arrays.toString(arr));
        trackNums(arr); // call this first to insert stream
        test(1);
        test(4);
        test(13);
    }
    
    private static void trackNums(int[] arr) {
        for (int num : arr) {
            RankFromStream.track(num);
        }
    }
    
    private static void test(int num) {
        System.out.format("\nRank of %d = %d", num, RankFromStream.getRank(num));
    }
}
