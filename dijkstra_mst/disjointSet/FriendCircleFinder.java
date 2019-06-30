package ds_algo_study.dijkstra_mst.disjointSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ds_algo_study.dijkstra_mst.DisjointSet;

/**
 * Q) find circle num. 
 * input: String list / output: 2 (2 circles - friend 0,1,2 / friend 3)
 * YYNN (friend 0 - friend 1)
 * YYYN (friend 1 - friend 0, 2)
 * NYYN (friend 2 - friend 1)
 * NNNY (friend 3 - no friend)
 * 
 * Approach1: using union finder
 * Approach2: using DFS (T.C is better)
 * @author Sunny Park
 */

public class FriendCircleFinder {
    private final DisjointSet finder;
    
    public FriendCircleFinder(int num) {
        finder = new DisjointSet(num);
    }
    
    public static int findCircleNumber(List<String> input) {
        int size = input.size();
        FriendCircleFinder friendCircleFinder = new FriendCircleFinder(size);
        for (int i = 0; i < input.size(); i++) {
            String relationship = input.get(i);
            for (int j = 0; j < relationship.length(); j++) {
                if (i == j) continue;
                if (friendCircleFinder.finder.isConnected(i, j)) continue;
                
                if (relationship.charAt(j) == 'Y') {
                    friendCircleFinder.finder.merge(i, j);
                }
            }
        }
//        System.out.println(Arrays.toString(friendCircleFinder.finder.parent));
        return friendCircleFinder.finder.getUniqueParentNumber();
    }
    
    public static int findCircleNumberDFS(List<String> input) {
        int total = 0;
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> inputList = init(input);
        
        for (int i = 0; i < input.size(); i++) {
            total += dfs(inputList, visited, i);
        }
        return total;
    }
    
    private static int dfs(List<List<Integer>> inputList, Set<Integer> visited, int curr) {
        if (visited.contains(curr)) return 0;
        visited.add(curr);
        for (Integer neighbor : inputList.get(curr)) {
            dfs(inputList, visited, neighbor.intValue());
        }
        return 1;
    }
    
    private static List<List<Integer>> init(List<String> input) {
        List<List<Integer>> inputList = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            inputList.add(new ArrayList<>());
        }
        
        for (int i = 0; i < input.size(); i++) {
            String curr = input.get(i);
            for (int j = 0; j < curr.length(); j++) {
                if (i == j) continue;
                if (curr.charAt(j) == 'Y') {
                    inputList.get(i).add(j);
                }
            }
        }
        return inputList;
    }
    
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("YYNN");
        input.add("YYYN");
        input.add("NYYN");
        input.add("NNNY");
        
        System.out.println(findCircleNumber(input));
        System.out.println(findCircleNumberDFS(input));
        
    }
}
