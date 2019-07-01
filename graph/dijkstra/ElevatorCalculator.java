package ds_algo_study.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.google.common.collect.Lists;

/**
 * Q. Given 1 ~ Nth floor, M elevators, elevator info.
 * Find the minimum number of shifts.
 * 
 * E.g. Elevator info.
 * Elevator1 : 4 - 7 - 10
 * Elevator2 : 7 - 12
 * Elevator3 : 4 - 8 - 12
 * 
 * 10th -> 8th floor ? 
 * 2 (take 1 -> 3 instead of 1 -> 2 -> 3)
 * 
 * Intuition: using Dijkstra, 
 * Within the same elevator, all edge weight are set as 1. 
 * Find the shortest path from the src to the dest.
 * 
 * + further thoughts: which elevator, path to pick?
 * @author Sunny Park
 */
public class ElevatorCalculator {
    private static Map<Integer, Vertex> floorMap = new HashMap<>();
    private static Map<Vertex, Double> pathMap = new HashMap<>();
    // private static Map<Integer, List<Edge>> elevatorInfo = new HashMap<>();
    // private static int elevatorNumber = 0;

    
    public static void addFloors(List<Integer> list) {
        for (Integer floor : list) {
            if (!floorMap.containsKey(floor)) {
                floorMap.put(floor, new Vertex(floor.intValue()));
            }
        }
        connect(list);
        // elevatorNumber++;
    }
    
    private static void connect(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int s = list.get(i);
            Vertex start = floorMap.get(s);
            for (int j = i + 1; j < list.size(); j++) {
                int e = list.get(j);
                Vertex end = floorMap.get(e);
                Edge e1 = new Edge(end, 1.0);
                Edge e2 = new Edge(start, 1.0);
                start.addEdge(e1);
                end.addEdge(e2);
                // elevatorInfo.put(elevatorNumber, Arrays.asList(e1, e2));
            }
        }
    }
    
    private static void retrieveShortestPath(int input) {
        Vertex src = floorMap.get(input);
        pathMap = new HashMap<>();
        
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
        minHeap.offer(src.setMinDistance(0.0));
        pathMap.put(src, 0.0);
        
        while (!minHeap.isEmpty()) {
            Vertex top = minHeap.poll();
            for (Edge e : top.getAdjacencies()) {
                Vertex target = e.getTarget();
                double newMinDist = top.getMinDistance() + e.getWeight();
                if (pathMap.containsKey(target) && target.getMinDistance() < newMinDist) {
                    continue;
                }
                
                if (target.getMinDistance() > newMinDist) {
                    pathMap.put(target.setMinDistance(newMinDist).setPrevious(top), newMinDist);
                    minHeap.offer(target);
                }
            }
        }
    }
    
    public static int findMinimumShift(int start, int dest) {
        retrieveShortestPath(start);
        return (pathMap.get(floorMap.get(dest))).intValue();
    }
    
    public static List<Integer> findPath(int start, int dest) {
        List<Integer> result = new ArrayList<>();
        Vertex curr = floorMap.get(dest);
        Vertex startVertex = floorMap.get(start);
        while (curr != startVertex) {
            result.add(Integer.valueOf(curr.getName()));
            curr = curr.getPrevious();
        }
        result.add(Integer.valueOf(startVertex.getName()));
        return Lists.reverse(result);
    }
    

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.addAll(Arrays.asList(4, 7, 10));
        addFloors(list1);
        
        List<Integer> list2 = new ArrayList<>();
        list2.addAll(Arrays.asList(7, 12));
        addFloors(list2);
        
        List<Integer> list3 = new ArrayList<>();
        list3.addAll(Arrays.asList(4, 8, 12));
        addFloors(list3);

        System.out.println(findMinimumShift(10, 8));
        System.out.println(findPath(10, 8));
    }
}
