package ds_algo_study.sorting;

public class Type04_MergeSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }
    
    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int leftNum = mid - left + 1;
        int rightNum = right - mid;
        
        int[] leftArr = new int[leftNum];
        int[] rightArr = new int[rightNum];
        
        System.arraycopy(arr, left, leftArr, 0, leftNum);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightNum);
        
        int i = 0, j = 0;
        int p = left;
        
        
    }
}
