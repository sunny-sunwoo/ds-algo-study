package ds_algo_study.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Sets;

import javafx.scene.effect.Light.Point;

/**
 * Q5. Ocean Border(BFS with memoization)
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" 
 * touches the right and bottom edges.
 * 
 * Water can only flow in four directions (up, down, left, or right) from a cell 
 * to another one with height equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * 
 * 
 * @author Sunny Park
 *
 */
public class Q5_OceanBorder {
    public static char[][] findBorder(int[][] m) {
        return toResult(findBorder_internally(m), m.length, m[0].length);
    }
    
    private static Set<Point> findBorder_internally(int[][] m) {
        Set<Point> allPoints = toPoint(m);
        return Sets.difference(allPoints, 
                Sets.union(findReachableOcean(m, Point.of(0,0)), 
                        findReachableOcean(m, Point.of(m.length - 1, m[0].length - 1))));
    }
    
    private static Set<Point> findReachableOcean(int[][] m, Point startingPoint) {
        Set<Point> visited = new HashSet<>();
        Queue<Point> q = new LinkedList<>();
        q.offer(startingPoint);
        visited.add(startingPoint);
        
        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (Point neighbor : curr.getNeighbors()) {
                if (visited.contains(neighbor)) continue;
                if (!neighbor.isValidRange(m.length, m[0].length)) continue;
                if (m[neighbor.y][neighbor.x] >= m[curr.y][curr.x]) {
                    q.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visited;
    }
    
    private static char[][] toResult(Set<Point> points, int rowLen, int colLen) {
        char[][] result = new char[rowLen][colLen];
        points.stream().forEach(p -> result[p.y][p.x] = 'B');
        return result;
    }
    
    private static Set<Point> toPoint(int[][] m){
        Set<Point> points = new HashSet<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                points.add(Point.of(i, j));
            }
        }
        return points;
    }
    
    private static class Point {
        int y;
        int x;
        
        private Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        public static Point of(int y, int x) {
            return new Point(y,x);
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
