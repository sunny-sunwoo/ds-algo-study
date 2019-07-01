package ds_algo_study.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

/**
 * Q. Given List<List<Integer>> input,
 * each list has the neighbor wizard's number.
 * cost to the neighbor = (neighbor wizard number - this wizard number) ^ 2
 * 
 * Find the minimum costs to get to last wizard (#4) from the wizard 0.
 * 
 * e.g.
 * wizard#0 : [1, 2, 4] (wizard#0 can go to 1, 2, 4. each cost is 1, 4, 16)
 * wizard#1 : [3, 4] (wizard#1 can go to 3, 4. each cost is 4, 9)
 * 
 * output: 0 1 4
 * in this case, 0 -> 4(cost 16)  << 0 -> 1 -> 4 (cost 1 + 9 : better route)
 * 
 * [Intuition] apply Dijkstra
 * List<List<Integer>> input should be passed to the *preprocess* method.
 * -> apply dijkstra efficiently.
 * 
 * @author Sunny Park
 */
public class WizardRouteFinder {
    public static List<Integer> findWizardRoute(List<List<Integer>> input) {
        Pair<Vertex, Vertex> srcAndDest = init(input);
        retrieveShortestPath(srcAndDest.getLeft());
        return Lists.reverse(postProcess(srcAndDest.getRight()));
    }
    
    private static void retrieveShortestPath(Vertex src) {
        Map<Vertex, Double> cache = new HashMap<>();
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
        minHeap.offer(src.setMinDistance(0.0));
        
        while (!minHeap.isEmpty()) {
            Vertex top = minHeap.poll();
            for (Edge e : top.getAdjacencies()) {
                Vertex target = e.getTarget();
                double candidDistance = top.getMinDistance() + e.getWeight();
                
                if (cache.containsKey(target) && target.getMinDistance() < candidDistance) {
                    continue;
                }
                
                if (candidDistance < target.getMinDistance()) {
                    cache.put(target.setPrevious(top).setMinDistance(candidDistance), candidDistance);
                    minHeap.offer(target);
                }
            }
        }
    }
    
    private static Pair<Vertex, Vertex> init(List<List<Integer>> input) {
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            Vertex curr = vertexMap.getOrDefault(i, new Vertex(i));
            List<Integer> neighbors = input.get(i);
            
            for (int n : neighbors) {
                Vertex neighbor = vertexMap.getOrDefault(n, new Vertex(n));
                curr.addEdge(new Edge(neighbor, Math.pow(n - i, 2)));
                vertexMap.put(n, neighbor);
            }
            vertexMap.put(i, curr);
        }
        return Pair.of(vertexMap.get(0), vertexMap.get(vertexMap.size() - 1));
    }
    
    private static List<Integer> postProcess(Vertex dest) {
        List<Integer> result = new ArrayList<>();
        
        Vertex curr = dest;
        while (curr != null) {
            result.add(Integer.valueOf(curr.getName()));
            curr = curr.getPrevious();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            input.add(new ArrayList<>());
        }
        input.get(0).addAll(Arrays.asList(1, 2, 4));
        input.get(1).addAll(Arrays.asList(3, 4));
        
        System.out.println(findWizardRoute(input));
        
    }
    
}
