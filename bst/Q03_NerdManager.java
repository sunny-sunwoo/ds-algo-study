package ds_algo_study.bst;

import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * [Approach] use TreeMap 
 * (WRONG ANSWER- missed the case when new pair can't be added.)
 * 1. check lowerEntry until we have no more floorEntry
 *    1) check if the value(pScore) is also lower
 *    2) remove
 * 2. put the curr entry
 * 3. return the size.
 *  
 * [Solution] 
 * 1. check if curr point is dominated. -> if so, return curr size.
 * 2. removeDominated.
 * 3. put curr point.
 * 4. rt the size.
 * 
 * [Time/Space]
 * Space: O(n), n = valid points
 * Time: O(NlogN), 
 *   -> find candidates: logN, 
 *   -> in the worst case, deletes every items: N * logN 
 * 
 * @author Sunny Park
 *
 */
public class Q03_NerdManager {
    private final NavigableMap<Integer, Integer> nerdMap = new TreeMap<>();
    
    public int register(int jScore, int pScore) {
        if (isDominated(jScore, pScore)) {
            return nerdMap.size();
        }
        removeDominated(jScore, pScore);
        nerdMap.put(jScore, pScore);
        nerdMap.entrySet().stream().forEach(e -> System.out.println(e));
        return nerdMap.size();
    }
    
    private boolean isDominated(int x, int y) {
        if (nerdMap.isEmpty()) return false;
        Map.Entry<Integer, Integer> rightBound = nerdMap.higherEntry(x);
        if (rightBound == null) return false;
        return rightBound.getValue() > y;
    }
    
    private void removeDominated(int jScore, int pScore) {
        Map.Entry<Integer, Integer> candid = nerdMap.lowerEntry(jScore);
        Set<Map.Entry<Integer, Integer>> removals = new HashSet<>();
        while (candid != null) {
            if (candid.getValue() < pScore) {
                removals.add(candid);
            }
            candid = nerdMap.lowerEntry(candid.getKey());
        }
        removals.stream().forEach(entry -> nerdMap.remove(entry.getKey()));
    }
    
    
    //WRONG
    public int register_wrong(int jScore, int pScore) {
        Map.Entry<Integer, Integer> candid = nerdMap.lowerEntry(jScore);
        Set<Integer> removals = new HashSet<>();
        while (candid != null) {
            if (candid.getValue() < pScore) {
                removals.add(candid.getKey());
            } 
            candid = nerdMap.lowerEntry(candid.getKey());
        }
        removals.stream().forEach(e -> nerdMap.remove(e));
        nerdMap.put(jScore, pScore);
        nerdMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ", " + e.getValue()));
        return nerdMap.size();
    }
    
    public static void main(String[] args) {
        Q3NerdManager manager = new Q3NerdManager();
        System.out.println(manager.register(4, 3)); // 1
        System.out.println(manager.register(2, 5)); // 2
        System.out.println(manager.register(1, 8)); // 3
        System.out.println(manager.register(5, 6)); // 2
        System.out.println(manager.register(1, 1));
    }
}
