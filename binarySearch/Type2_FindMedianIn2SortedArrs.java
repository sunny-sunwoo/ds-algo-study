package ds_algo_study.binarySearch;

/**
 * Q. There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. 
 * The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * 
 * [Approach] Binary Search
 *  find median == find kth
 *  length n is an odd number -> find (n/2 + 1)th
 *  length n is an even number -> find n/2th + (n/2 + 1)th / 2
 *  
 *  e.g. length = 5 -> find (3rd + 3rd) / 2
 *       length = 6 -> find (3rd + 4th) / 2
 * 
 * @see <a href= "https://leetcode.com/problems/median-of-two-sorted-arrays/"> 
 * Leetcode #4 Median of Two Sorted Arrays </a>
 * @author Sunny Park
 *
 */
public class Type2_FindMedianIn2SortedArrs {

    public static double findMedian(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return 0.0;
        
        int m = arr1.length;
        int n = arr2.length;
        
        int leftK = (m + n + 1) / 2;
        int rightK = (m + n + 2) / 2;
        
        if (n == 0) return (double) (arr1[leftK - 1] + arr1[rightK - 1]) / 2.0;
        if (m == 0) return (double) (arr2[leftK - 1] + arr2[rightK - 1]) / 2.0;
        
        return ((double)findkth(arr1, 0, arr2, 0, leftK) + findkth(arr1, 0, arr2, 0, rightK)) / 2.0;
    }
    
    private static int findkth(int[] arr1, int start1, int[] arr2, int start2, int k) {
        // 1. check if exhausted -> find the answer from the other.
        if (start1 >= arr1.length) {
            return arr2[start2 + k - 1];
        }
        
        if (start2 >= arr2.length) {
            return arr1[start1 + k - 1];
        }
        
        // 2. k == 1
        if (k == 1) {
            return Math.min(arr1[start1], arr2[start2]);
        }
        
        // 3. set mid1, mid2 to compare. smaller one's ptr will move forward.
        int mid1 = Integer.MAX_VALUE;
        int mid2 = Integer.MAX_VALUE;
        
        mid1 = arr1.length > start1 + k/2 - 1 ? arr1[start1 + k/2 - 1] : mid1;
        mid2 = arr2.length > start2 + k/2 - 1 ? arr2[start2 + k/2 - 1] : mid2;
        
        return mid1 < mid2 ? findkth(arr1, start1 + k/2, arr2, start2, k - k/2)
                : findkth(arr1, start1, arr2, start2 + k/2, k - k/2);
    }
    
    public static void main(String[] args) {
        int[] nums1 = new int[0];
        int[] nums2 = {3, 4, 6};
        
        System.out.println(findMedian(nums1, nums2));

    }
}
