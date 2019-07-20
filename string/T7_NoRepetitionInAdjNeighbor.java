package ds_algo_study.string;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Validate if it's possible to make all adj strings are not same.
 * 
 * e.g. 
 * aab -> true. aba
 * aaab -> false. can't avoid having aa by moving b.
 * 
 * [Approach] use maxHeap to rm top1,2 char by 1.
 * 1. create a maxheap of counters.
 * 2. iterate through each char and put in the heap
 * 3. while heap size >= 2,
 *    - decrease top 2 chars by 1.
 * 4. rt if heap is empty or last char count <= 1.
 * 
 * @author Sunny Park
 *
 */
public class T7_NoRepetitionInAdjNeighbor {
    public static boolean validateString(String s) {
        PriorityQueue<CharAndCount> maxHeap 
        = new PriorityQueue<>(Comparator.comparing(CharAndCount::getCount).reversed());
        
        populate(maxHeap, s);
        // maxHeap.stream().forEach(e -> System.out.println(e));
        
        while (maxHeap.size() >= 2) {
            CharAndCount first = maxHeap.poll();
            CharAndCount second = maxHeap.poll();
            if (first.hasNext()) {
                maxHeap.offer(first.minusOne());
            }
            if (second.hasNext()) {
                maxHeap.offer(second.minusOne());
            }
        }
        
        return maxHeap.isEmpty() || maxHeap.poll().lessThanOne();
    }
    
    private static void populate(PriorityQueue<CharAndCount> maxHeap, String s) {
        Map<Character, Integer> cache = new HashMap<>();
        for (char c : s.toCharArray()) {
            cache.put(c, cache.getOrDefault(c, 0) + 1);
        }
        cache.entrySet()
        .stream()
        .forEach(e -> maxHeap.offer(new CharAndCount(e.getKey(), e.getValue())));
    }
    
    private static class CharAndCount {
        char ch;
        int cnt;
        
        CharAndCount(char ch, int cnt) {
            this.ch = ch;
            this.cnt = cnt;
        }
        
        int getCount() {
            return this.cnt;
        }
        
        boolean hasNext() {
            return cnt > 1;
        }
        
        CharAndCount minusOne() {
            this.cnt--;
            return this;
        }
        
        boolean lessThanOne() {
            return this.cnt <= 1;
        }
        
        @Override
        public String toString() {
            return ch + " = " + cnt;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(validateString("aab"));
        System.out.println(validateString("aaaaaab"));
        System.out.println(validateString("aaabbbbcccc"));
    }
}
