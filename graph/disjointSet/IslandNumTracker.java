package ds_algo_study.graph.disjointSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ds_algo_study.graph.DisjointSet;

/**
 * how to track island's number
 * while adding new lands.
 * 
 * Intuition. using DisjointSet
 * when adding -> num++
 * check if any neighbor is connected -> num--
 * @author Sunny Park
 */
public class IslandNumTracker {
    private final DisjointSet ds;
    private int islandNumber = 0;
    private final int[][] grid;
    
    public IslandNumTracker(int[][] grid) {
        this.grid = grid;
        ds = new DisjointSet(grid.length * grid[0].length);
        init(ds, grid);
    }
    
    private void init(DisjointSet ds, int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    addIsland(i, j);
                }
            }
        }
    }
    
    public List<Integer> numIslands2(int[][] positions) {
        List<Integer> result = new ArrayList<>();
        for (int[] position : positions) {
            result.add(addIsland(position[0], position[1]));
        }
        return result;
    }
    
    public int addIsland(int row, int col) {
        System.out.println(row + " //  " + col);
        Point curr = Point.of(row, col);
        if (grid[curr.y][curr.x] == 1) return islandNumber;
        
        grid[curr.y][curr.x] = 1;
        islandNumber++;
        
        for (Point neighbor : curr.getNeighbors()) {
            if (!neighbor.isWithinValidRange(grid.length, grid[0].length)) {
                continue;
            }
            
            if (grid[neighbor.y][neighbor.x] == 0) continue;
            
            int currPoint = toUFPoint(curr);
            int neighborPoint = toUFPoint(neighbor);
            if (!ds.isConnected(currPoint, neighborPoint)) {
                ds.merge(currPoint, neighborPoint);
                islandNumber--;
            }
        }
        
        // print out the status for test.
        System.out.println(ds);
        for (int[] each : grid) {
            System.out.println(Arrays.toString(each));
        }
        return islandNumber;
    }
    
    private int toUFPoint(Point p) {
        return p.y * grid[0].length + p.x;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] each : grid) {
            sb.append(Arrays.toString(each))
            .append("\n");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    
    private static class Point {
        int y;
        int x;
        private Point (int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        public static Point of(int y, int x) {
            return new Point(y, x);
        }
        
        public List<Point> getNeighbors() {
            return Arrays.asList(Point.of(y + 1, x), 
                                Point.of(y - 1, x), 
                                Point.of(y, x + 1),
                                Point.of(y, x - 1));
        }
        
        public boolean isWithinValidRange(int yBound, int xBound) {
            if (y < 0 || x < 0) return false;
            return (y < yBound) && (x < xBound);
        }
        
        @Override
        public String toString() {
            return y + ", " + x;
        }
    }
    
    public static void main(String[] args) {
        int[][] positions = {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
//        int[][] grid = {{1, 1, 1}, {1, 0, 1}, {0, 1, 0}};
        int[][] grid = new int[3][3];
        IslandNumTracker tracker = new IslandNumTracker(grid);
//        System.out.println(tracker.addIsland(1, 1));
        System.out.println(tracker.numIslands2(positions));
    }
    
    
}
