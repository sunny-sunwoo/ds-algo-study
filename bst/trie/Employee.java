package ds_algo_study.bst.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * In the interviews, I should mention that I will implement set/get method.
 * hashCode, equals methods required too. (because it's used in the Map.)
 * 
 * [Note] Internal working of HashMap in Java.
 * Hashing is a process of converting an object into integer form by using the method hashCode(). 
 * It's necessary to write hashCode() & equals() method properly for better performance of HashMap.
 * 
 * 1. hashCode() method is used to get the hash Code of an object. 
 * hashCode() method of object class returns the memory reference of object in integer form. 
 * In HashMap, hashCode() is used to calculate the bucket and therefore calculate the index.
 * 
 * 2.equals method is used to check that 2 objects are equal or not. 
 * This method is provided by Object class. 
 * HashMap uses equals() to compare the key whether the are equal or not. 
 * 
 * 3. 
 * A bucket is one element of HashMap array. It is used to store nodes. 
 * Two or more nodes can have the same bucket. 
 * In that case LL structure is used to connect the nodes. (Separate Chaining)
 * 
 * Buckets are different in capacity. A relation between bucket and capacity is as follows:
 * capacity = number of buckets * load factor
 * 
 * 4. Index Calculation in HM
 * we generate index to minimize the size of array. Basically following operation is performed to calculate index.
 * index = hashCode(key) & (n-1).
 * where n is number of buckets or the size of array.
 * 
 * @see <a href="https://www.geeksforgeeks.org/internal-working-of-hashmap-java/">geeks for geeks</a>
 * @author Sunny Park
 *
 */
public class Employee {
    private final String id;
    private final List<Employee> team;
    private String managerId;
    private Integer reportingNumber;
    
    public Employee(String id) {
        this.id = id;
        team = new ArrayList<>();
    }
    
    public void addMember(Employee e) {
        team.add(e);
    }
    
    public void setManagerId(String mgrId) {
        managerId = mgrId;
    }
    
    public String getId() {
        return id;
    }
    
    public Integer getReportingNumber() {
        if (reportingNumber != null) return reportingNumber;
        return getReportingNumber(getTeam());
    }
    
    private List<Employee> getTeam() {
        return team;
    }
    
    private int getReportingNumber(List<Employee> team) {
        int total = 0;
        for (Employee emp : team) {
            total += 1;
            total += emp.getReportingNumber().intValue();
        }
        return total;
    }
 }
