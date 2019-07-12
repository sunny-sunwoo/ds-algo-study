package ds_algo_study.bst.trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Create a employee trie!
 * bc/ 1 manager : X employees. 
 * 
 * 1. populate empId-Employee map
 * 2. add employee to the team list.
 * 3. set manager id for each employee.
 * 4. build a HM empId-reporting num
 * 
 * @author Sunny Park
 *
 */

public class ReportingNumberFinder {
    public static Map<String, Integer> find(List<Pair<String, String>> info) {
        Map<String, Employee> empById = new HashMap<>();
        for (Pair<String, String> pair : info) {
            String mgrId = pair.getLeft();
            String empId = pair.getRight();
            
            Employee manager = empById.getOrDefault(mgrId, new Employee(mgrId));
            Employee employee = empById.getOrDefault(empId, new Employee(empId));
            
            manager.addMember(employee);
            employee.setManagerId(mgrId);
        }
        
        Map<String, Integer> result = new HashMap<>();
        for (Employee e : empById.values()) {
            result.put(e.getId(), e.getReportingNumber());
        }
        return result;
    }
}
