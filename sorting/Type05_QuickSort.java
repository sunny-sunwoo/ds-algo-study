package ds_algo_study.sorting;

/**
 * Quick Sort: Remember Pivot & Partition!
 *  - by Tony Hoare(who invented "null")
 *  
 * 1) Concept
 *  - 3 steps: partition -> quicksort(before pivot) -> quicksort(after pivot)
 *      Partition array(sub arrays) into left & right group. (curr elem compared to the pivot.)
 *      Call itself to sort the left group.
 *      Call itself to sort the right group.
 *             
 *  - pivot: used to determine which group a value should be placed. 
 *           left    | pv |   right  
 *                      ^ at its final position after each round. 
 *                       
 *  2) worst case? {7, 6, 5, 4, 3, 2, 1} 
 *    - (n - 1) recursive calls needed. & k(sub arr length) comparisons per every call.
 *      => O(n^2) time.
 *  
 *  - solution
 *    1. median.
 *       by picking the median as a pivot, sorting can guarantee O(NLogN) time.(LogN level.)
 *       but, there is a overhead for calculation of median.
 *       
 *    2. random value OR pseudo median(median of 3 elems: first, mid, last)
 *       with this improvement, Quicksort can achieve O(NlogN) time.
 *       => *NOTE* Worst case is still O(n^2)
 *      
 *  
 * @author Sunny Park
 *
 */
public class Type05_QuickSort {

}
