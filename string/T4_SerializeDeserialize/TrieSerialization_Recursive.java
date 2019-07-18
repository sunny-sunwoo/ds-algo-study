package ds_algo_study.string.T4_SerializeDeserialize;

import java.awt.font.NumericShaper.Range;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Q. don't include parent id in the serialization.
 *    -> Recursive! 
 *    -> use { } to represent the relationship.
 *    
 * [Intuition]
 *          root
 *      a          b
 *   t  s  r         k
 *  l 
 *  
 *  => root{a{t{l},s,r}, b{k}}
 *        +1 + + -    -   + - -1
 *        
 *                  4 +s, 4 -s! 
 *     // "{" == +1
 *     // "}" == -1 
 *     
 * [serialize] TreeNode -> String
 * use StringBuilder.
 * use recursion!!! 
 *  => curr + { + children + }
 * 
 * [deserialize] String -> TreeNode
 * base case: when str doesn't contain any { // means leaf node.
 * set left, right index by checking the index of {, }
 * use recursion. by passing the rest.
 * create node // while 
 * @author Sunny Park
 *
 */
public class TrieSerialization_Recursive {
    private static class TrieNode {
        String val;
        List<TrieNode> children;
        
        TrieNode(String val) {
            this.val = val;
            children = new ArrayList<>();
        }
        
        TrieNode setChildren(List<TrieNode> input) {
            input.stream().forEach(c -> children.add(c));
            return this;
        }
    }
    
    public static String serialize(TrieNode node) {
        StringBuilder sb = new StringBuilder(node.val);
        if (node.children.isEmpty()) {
            return sb.toString();
        }
        sb.append("{");
        node.children.stream().forEach(c -> {
            sb.append(serialize(c)).append("}");
        });
        return sb.deleteCharAt(sb.length() - 1).append("}").toString();
    }
    
    public static TrieNode deserialize(String str) {
        if (!str.contains("{")) {
            return new TrieNode(str);
        }
        int left = str.indexOf("{");
        int right = str.indexOf("}");
        return new TrieNode(str.substring(0, left))
                .setChildren(split(str.substring(left + 1, right))
                        .stream()
                        .map(s -> deserialize(s))
                        .collect(Collectors.toList()));
    }
    
    private static List<String> split(String str) {
        List<Range> ranges = new ArrayList<>();
        int counter = 0;
        int startingPoint = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '{') {
                counter++;
            }
            
            if (str.charAt(i) == '}') {
                counter--;
            }
            
            if (str.charAt(i) == ',') {
                if (counter == 0) {
                    ranges.add(new Range(startingPoint, i));
                    startingPoint = i + 1;
                }
            }
        }
        ranges.add(new Range(startingPoint, str.length() - 1));
        
        return ranges.stream()
                .map((range) -> str.substring(range.low, range.high))
                .collect(Collectors.toList());
    }
    
    private static class Range {
        int low;
        int high;
        Range(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
}
