package ds_algo_study.string.T4_SerializeDeserialize;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * String <-> Trie
 * String should contain {data, id, parentId}
 * 
 * [Serialization] Trie -> String
 * - using Queue. 
 * - if parent is null, append "NULL"
 * 
 * [Deserialization] String -> Trie
 * - split by delimeter
 * - for loop increment by 3.
 * - check if parent is not null -> set parent accordingly.
 * 
 * @author Sunny Park
 *
 */
public class SerializationWithTrie {
    private static class TreeNode {
        String val;
        String id;
        String parentId;
        TreeNode parent;
        List<TreeNode> children;
        
        public TreeNode(String val, String id, String pId) {
            this.val = val;
            this.id = id;
            this.parentId = pId;
            children = new ArrayList<>();
        }
        
        public TreeNode getParent() {
            return parent;
        }
        
        public static void setParent(TreeNode parent, TreeNode child) {
            child.parent = parent;
            parent.children.add(child);
        }
        
        @Override
        public String toString() {
            
            return "(val:" + this.val + ")"  + " (id:" + this.id + ")"  + " child num:" + children.size() + " // ";
        }
    }
    
    public static String serialize(TreeNode root) {
        checkNotNull(root);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            sb.append(top.val).append(",")
              .append(top.id).append(",")
              .append(top.getParent() != null ? top.getParent().id : "NULL").append(",");
            if (!top.children.isEmpty()) {
                top.children.stream().forEach(n -> q.offer(n));
            }
        }
        return sb.toString();
    }
    
    public static TreeNode deserialize(String stringForm) {
        checkNotNull(stringForm);
        String[] data = stringForm.split(",");
        int len = data.length;
        checkArgument(len != 0 && len % 3 == 0);
        
        Map<String, TreeNode> cache = new HashMap<>();
        for (int i = 0; i < data.length; i += 3) {
            TreeNode newNode = new TreeNode(data[i], data[i + 1], 
                    data[i+2].equals("NULL") ? null : data[i + 2]);
            if (cache.containsKey(data[i + 2])) {
                TreeNode.setParent(cache.get(data[i + 2]), newNode); // static method!
            }
            cache.put(data[i + 1], newNode); // don't forget!
        }
        cache.entrySet().stream().forEach(entry -> System.out.println(entry)); // debugging
        return cache.get(data[1]); 
    }
    
    public static void main(String[] args) {
        String data = "a,1,NULL,b,2,1,c,3,2,aa,4,1,abc,5,1";
        System.out.print(serialize(deserialize(data)));
    }
}
