package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;

public class P2_2GenerateAllSubstrings {
    public static List<String> generateAllSubstrings(String s) {
        List<String> result = new ArrayList<>();
        iterateFirstChar(result, s, 0);
        return result;
    }
    
    private static void iterateFirstChar(List<String> result, String s, int i) {
        if (i >= s.length()) {
            return;
        }
        iterateSecondChar(result, s, i, i + 1);
        iterateFirstChar(result, s, i + 1);
    }
    
    private static void iterateSecondChar(List<String> result, String s, int i, int j) {
        if (j > s.length()) {
            return;
        }
        result.add(s.substring(i, j));
        iterateSecondChar(result, s, i, j + 1);
    }
    
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(generateAllSubstrings(s));
    }
}
