package ds_algo_study.string.T4_SerializeDeserialize;

import java.util.Arrays;

/**
 * [Type 4-1] String <-> String[]
 * Serialization is the process of converting a data structure or object 
 * into a sequence of bits so that it can be stored in a file or memory buffer, 
 * or transmitted across a network connection link to be reconstructed later 
 * in the same or another computer environment.
 * 
 * [Approach]
 * When converting string arr to string,
 * if we use (,) as a delimeter, it can't be included in the arr elem.
 * 
 * To solve this, put the number which represents the length of elem.
 * 2,3,abc,3,a,c
 * => arr length = 2, ["abc", "a,c"]
 * 
 * [Methods]
 * - serialize: use StringBuilder.
 * - deserialize: use 2 helper methods
 *   1) convertNextInt - convert number into int form.
 *   2) getNextPos - find next position.
 * 
 * @author Sunny Park
 *
 */
public class SerializationWithStringArr {
    private static final char COMMA = ',';
    public static String serialize(String[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(arr.length))
          .append(COMMA);
        
        for (String s : arr) {
            sb.append(String.valueOf(s.length()))
              .append(COMMA)
              .append(s);
        }
        return sb.toString();
    }
    
    public static String[] deserialize(String str) {
        int arrayLen = readNextInt(str, 0, COMMA);
        String[] result = new String[arrayLen];
        int pos = readNextPos(str, 0, COMMA);
        
        for (int i = 0; i < arrayLen; i++) {
            int len = readNextInt(str, pos, COMMA);
            pos = readNextPos(str, pos, COMMA);
            result[i] = str.substring(pos, pos + len); // *NOTE* inclusive, exclusive INDEX!(not length)
            pos = pos + len;
        }
        return result;
    }
    
    private static int readNextInt(String str, int currPos, char delimeter) {
        int pos = currPos;
        while (str.charAt(pos) != delimeter) {
            pos++;
        }
        return Integer.parseInt(str.substring(currPos, pos));
    }
    
    private static int readNextPos(String str, int currPos, char delimeter) {
        int pos = currPos;
        while (str.charAt(pos) != delimeter) {
            pos++;
        }
        return pos + 1;
    }
    
    public static void main(String[] args) {
        String[] arr = {"abc", "a,c"};
        System.out.println(Arrays.toString(deserialize(serialize(arr))));
    }
}
