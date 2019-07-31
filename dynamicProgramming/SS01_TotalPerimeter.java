package ds_algo_study.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;


/**
 * RRWB
 * BRWB
 * BRWB
 * => find the max perimeter.
 * 
 * [Approach] bfs
 * 1. create a q and visited
 * 2. iterate through each cell
 * 3. for all neighbors(4 dirs from the curr cell)
 *    1) if neighbor is NOT in valid range OR different color? count++
 *    2) if neighbor is in valid range AND same color? add to the q and visited.
 * 4. keep max for each chunk.
 *  
 * @author Sunny Park
 *
 */
public class SS01_TotalPerimeter {
    public static int totalPerimeter(char[][] grid) {
        Queue<Point> q = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        
        int maxPerimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Point curr = Point.of(i,j);
                if (visited.contains(curr)) continue;
                q.offer(curr);
                visited.add(curr);
                int perimeter = 0;
                while (!q.isEmpty()) {
                    Point top = q.poll();
                    for (Point neighbor : top.getNeighbors()) {
                        if (visited.contains(neighbor)) continue;
                        if (!neighbor.hasValidRange(grid.length, grid[0].length) 
                                || grid[neighbor.y][neighbor.x] != grid[top.y][top.x]) {
                            perimeter++;
                            continue;
                        }
                        
                        if (neighbor.hasValidRange(grid.length, grid[0].length)
                                && grid[neighbor.y][neighbor.x] == grid[top.y][top.x]) {
                            q.offer(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
                maxPerimeter = Math.max(maxPerimeter, perimeter);
            }
        }
        return maxPerimeter;
    }
    
    private static class Point {
        int y;
        int x;
        
        private Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        static Point of (int y, int x) {
            return new Point(y, x);
        }
        
        boolean hasValidRange(int yBound, int xBound) {
            if (y < 0 || x < 0) {
                return false;
            }
            
            return y < yBound && x < xBound;
        }
        
        List<Point> getNeighbors() {
            return Arrays.asList(Point.of(y + 1, x), Point.of(y - 1, x),
                    Point.of(y, x + 1), Point.of(y, x - 1));
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
        
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point other = (Point) o;
            return this.y == other.y && this.x == other.x;
        }
    }
    
    public static void main(String[] args) {
        char[][] grid = {{'R','R','W','B'}, {'B','R','W','B'}, {'B','R','W','B'}};
        System.out.println(totalPerimeter(grid));
    }
}
