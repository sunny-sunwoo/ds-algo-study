package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * N Queens
 * 
 * Q. Given a chessboard, find all of the placements of N queens
 * so that no 2 queens are attacking each other.
 * 
 * [Approach1] Brute force
 * We can just generate all possible placements and validate each one.
 * 2 approaches -> selection in 2 dimensions or linearize chessboard.
 * 
 * - Time complexity: O(4 ^ depth + validation) 
 *      branching factor: 4
 *      depth: n ^ 2
 *      + significant effort to validate results
 *      
 * - space complexity: O(4 ^ depth * N)
 * 
 * 
 * [Approach2] Backtracking - Optimization
 * - linearization works better.
 * - optimizations
 *      only select one cell per row.
 *      only place queen in available cell.
 *      same diagonal: left(row - col), right(row + col)
 *      
 * - Time complexity: O(n^n * n)
 *      branching factor: n
 *      depth: n
 *      
 * - Space complexity: O(n^n * n)
 *      Worst case: have to store every combination.
 *      
 * Sol) 
 * 1. base case: 
 *      - path size == n -> add path to the result
 *      - row exceeds n. -> rt.
 *      
 * 2. logic
 *      - iterate through the availableCols. 
 *        *note* availableCols.toArray(new Integer[]{}) <- to avoid concurrentModificationException
 *        1) check diagonal. -> invalid: continue
 *        2) remove from availableCols.
 *        3) update diagonals (left: row - col, right: row + col)
 *        
 *        4) recurse on next call. (row + 1)
 *        
 *        5) backtrack availableCols, leftDiag, rightDiag.
 *        
 *     - recurse on next call. 
 *       bc/ curr row can be skipped.
 *      
 * @author Sunny Park
 * 
 */
public class P3_6NQueens {
    private static class Cell {
        int row;
        int col;
        
        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Cell)) return false;
            Cell other = (Cell) o;
            return (this.row == other.row) && (this.col == other.col);
        }
    }
    
    /**
     * [Approach1] find all combinations! and filter out.
     * @param n
     * @return
     */
    public static List<List<Cell>> nQueens_bruteforce(int n) {
        List<List<Cell>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 0, 0, n);
        // filter result to check if it doesn't break the rule.
        return result;
    }
    
    private static void backtrack(List<List<Cell>> result, List<Cell> path, int row, int col, int n) {
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        if (row == n || col == n) return;
        
        path.add(new Cell(row, col));
        backtrack(result, path, row, col + 1, n);
        backtrack(result, path, row, 0, n);
        
        path.remove(path.size() - 1);
        backtrack(result, path, row, col + 1, n);
        backtrack(result, path, row, 0, n);
    }
    
    /**
     * [Approach2] Optimized ver - find valid placements only!! 
     * @param n
     * @return
     */
    public static List<List<Cell>> nQueens_optimized(int n) {
        List<List<Cell>> result = new ArrayList<List<Cell>>();
        Set<Integer> availableCols = init(n);
        nQueens(n, result, new ArrayList<Cell>(), availableCols, 
                new HashSet<Integer>(), new HashSet<Integer>(), 0);
        return result;
    }
    
     // base case: path size check, row & col check. available cols check.
    private static void nQueens(int n, List<List<Cell>> result, List<Cell> path, Set<Integer> availableCols,
            Set<Integer> leftDiag, Set<Integer> rightDiag, int row) {
        
        if (path.size() == n) {
            result.add(new ArrayList<Cell>(path));
            return;
        }
        if (row >= n) return;
        
        for (Integer col : availableCols.toArray(new Integer[] {})) {
            if (leftDiag.contains(row - col) || rightDiag.contains(row + col)) continue;
            path.add(new Cell(row, col));
            availableCols.remove(col);
            leftDiag.add(row - col);
            rightDiag.add(row + col);
            
            nQueens(n, result, path, availableCols, leftDiag, rightDiag, row + 1);
            
            path.remove(path.size() - 1);
            availableCols.add(col);
            leftDiag.remove(row - col);
            rightDiag.remove(row + col);
        }
        nQueens(n, result, path, availableCols, leftDiag, rightDiag, row + 1);
    }
    
    private static HashSet<Integer> init(int n) {
        HashSet<Integer> cols = new HashSet<>();
        for (int i = 0; i < n; i++) {
            cols.add(i);
        }
        return cols;
    }
    
    public static void main(String[] args) {
        System.out.println(nQueens_optimized(5));
    }
    
}
