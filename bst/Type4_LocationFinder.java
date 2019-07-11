package ds_algo_study.bst;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Q. Which DS would be appropriate in the situation
 * when input (location ID and time) is given, 
 * and we want to find a closest location.
 * 
 * e.g. 
 * {9, "loca1"}, {10, "loca2"}, {10, "loca3"}, {12, "loca4"}
 * 
 * find(8) -> nothing
 * find(10) -> loca3 (overrides loca2)
 * 
 * [Note]
 * 1. data input + search simultaneously? -> use "BST"
 * 2. BST in Java
 *    [interface]           [implementation]
 *    NavigableSet/Map      TreeSet/Map
 *    -> NavigableMap<Long, String> finder=  new TreeMap<>();
 *    
 * 3. methods
 *      1) to get the least on the right side
 *      ceilingKey, ceilingEntry: Return the least key/entry greater than or equal to the given key
 *      higherKey, higherEntry: Return the least key/entry strictly greater than the given key.
 *      
 *      2) to get the greatest on the left side
 *      floorKey, floorEntry: Return the greatest key/entry less than or equal to the given key.
 *      lowerKey, lowerEntry: Return the greatest key/entry strictly less than the given key.
 *      
 * 4. long VS double.
 *    -> I shouldn't use double for the KEY, WHY? 
 *    -> key should be precise. double can cause issues.
 *    
 *    To compare 2 double or float values, 
 *    we should Float.compare(a, b) OR Double.compare(a, b)
 *    
 *    bc/ performing any math on floating points can lead to imprecise results
 *    
 *    
 * 
 * @see <a href="https://www.geeksforgeeks.org/treemap-in-java/">geeks for geeks</a>
 * @author Sunny Park
 *
 */
public class Type4_LocationFinder {
    private NavigableMap<Long, String> controller = new TreeMap<>();
    
    public Type4_LocationFinder insert(long time, String loca) {
        
        controller.put(time, loca);
        return this;
    }
    
    public Optional<String> find(long timestamp) {
        Map.Entry<Long, String> closest = controller.floorEntry(timestamp);
        return closest == null ? Optional.empty() : Optional.of(closest.getValue());
    }
    
    public static void main(String[] args) {
        double a = 1.000001; 
        double b = 0.000001; 
        double c = a-b; // 1.0000000
        System.out.println(c);
        System.out.println(c == 1.000000);
    }
}
