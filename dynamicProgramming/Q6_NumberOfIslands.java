package ds_algo_study.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Count number of islands.
 * 
 * [Approach] dfs
 * To keep the input arrays in same state,
 * use visited set! instead of changing input directly.
 * 
 * @author Sunny Park
 *
 */
public class Q6_NumberOfIslands {
    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,0}, {1,1,0,1,0}, {1,0,1,0,0}, {0,0,0,1,1}};
        System.out.println(numOfIslands(grid));

    }
    public static int numOfIslands(int[][] grid) {
        Set<Point> visited = new HashSet<>();
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (visited.contains(Point.of(i, j))) continue;
                if (grid[i][j] == 1) {
                    total++;
                    dfs(visited, grid, i, j);
                }
            }
        }
        return total;
    }
    
    private static void dfs(Set<Point> visited, int[][] grid, int i, int j) {
        Point curr = Point.of(i, j);
        visited.add(curr);
        
        for (Point neighbor : curr.getNeighbors()) {
            if (visited.contains(neighbor)) continue;
            if (!neighbor.isValidRange(grid.length, grid[0].length)) continue;
            
            if (grid[neighbor.y][neighbor.x] == 1) {
                dfs(visited, grid, neighbor.y, neighbor.x);
            }
        }
    }
    
    private static class Point {
        int y;
        int x;
        
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        static Point of(int y, int x) {
            return new Point(y, x);
        }
        
        public List<Point> getNeighbors() {
            return Arrays.asList(Point.of(y + 1, x), Point.of(y - 1, x), 
                    Point.of(y, x + 1), Point.of(y, x - 1));
        }
        
        public boolean isValidRange(int yBound, int xBound) {
            if (y < 0 || x < 0) return false;
            return (y < yBound) && (x < xBound);
        }
        
        // should override equals, hashCode.
        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) return false;
            Point other = (Point) obj;
            return (this.y == other.y) && (this.x == other.x);
        }
    }
}
