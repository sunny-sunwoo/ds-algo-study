package ds_algo_study.dijkstra_mst;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Data structure to check points are connected efficiently.
 * 3 -> 2, 2 -> 1 equals to 3 - 2 - 1 (all connected)
 *     1          
 *    /                2
 *   2       vs       / \
 *  /                3   1  
 * 3            (more efficient)
 * 
 * Add graph in the form of tree
 * by tracking parent pointers & rank. 
 * 
 * Worst case: LogN (balanced binary tree form)
 * Reference: https://www.geeksforgeeks.org/disjoint-set-data-structures/ 
 * 
 * @author Sunny Park
 */
public class DisjointSet {
    int[] parent;
    int[] rank;
    
    public DisjointSet(int num) {
        parent = new int[num];
        rank = new int[num];
        
        for (int i = 0; i < num; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    public int getUniqueParentNumber() {
        return IntStream.of(parent)
                .boxed().collect(Collectors.toSet()).size();
    }
    
    public boolean isConnected(int v, int w) {
        return find(v) == find(w);
    }
    
    private int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]); 
    }
    
    public DisjointSet merge(int v, int w) {
        int parentV = find(v);
        int parentW = find(w);
        
        if (parentV == parentW) return this;
        
        // update parent
        if (rank[parentV] > rank[parentW]) {
            // parent[w] = parentV;
            parent[parentW] = parentV;
        } else {
            // parent[v] = parentW;
            parent[parentV] = parentW;
        }
        
        // update rank
        if (rank[parentV] == rank[parentW]) {
            rank[parentW]++;
        }
        
        return this;
    }
    
    @Override
    public String toString() {
        return "Parent: " + Arrays.toString(parent) + "\n Rank: " + Arrays.toString(rank);
    }
}
