
# Sorting Algorithm

### Simple sorting: Bubble Sort, Selection Sort, Insertion Sort
+ BS, SS, IS all run in O(n^2) time in the worst case.
However, often IS performs better than the other two
bc/ it may require less # of comparisons depending on the input values(e.g. almost sorted arr)
& uses copying instead of swapping! (NO SWAP in IS)

+ **BS < SS**       - less # of swaps.
  **BS, SS < IS**   - less # of comparions. - no swap. only copy!

*** 

### Better sorting: Merge Sort, Quick Sort
+ In **Merge sort**, dividing the input arr is simple and easy, 
but it's expensive to merge these sorted left & right sub arr. 

+ In **Quick sort**, dividing the original problem into sub-problems(partitioning) is 
more complicated and expensive whereas combining the result from sub-problems together is easy.

+ Why do we use quick sort for pdt sorting instead of using merge sort?
bc/ elems can be sorted when they are placed on the memory.
quick sort is better in terms of space complexity bc/ ms needs an additional result holder.
=> initial cost: Merge sort > Quick sort > Insertion sort
=> So, various languages set own benchmarks and do the insertion sort if the size is less than a specific threshold.

*** 

### Sorting in Java
There are 2 Data type in java: 
1) Primitive Data Type: orders are well defined. no need to worry about stability.
2) Object: need to care about stability.


+ **Before Java6** <br/>
    PDT) Quick sort -> Insertion sort <br/>
    OBJ) Merge sort -> Insertion sort <br/>
    
    as we call recursive calls and the boundary gets smaller, 
    Java translates the sort into the insertion sort. <br/>
    bc/ when elems are almost sorted, IS is fast & best!! 
    
+ **After Java7** <br/>
    PDT) Dual pivot quick sort (using 2 pivots) <br/>
    OBJ) Tim sort (similar to ms + is) // linear time in best case. <br/>
    
+ Dual pivot quick sort 
 - can achive shorter height (smaller depth)
 - then why not, 3 pivots or 4 pivots? 
    -> sorting speed doesn't get faster proportionally to pivot nums.
    bc/ there happens additional cost for setting several pivots and partitioning.


***

### Sorting Algorithms
| Algorithm     | Best        | Average       | Worst       |
| ------------- |:-------:    |:----------:   |:---------   |
|Selection Sort | Ω(n^2)      | θ(n^2)        | O(n^2)      |
|Bubble Sort    | Ω(n)        | θ(n^2)        | O(n^2)      |
|Insertion Sort | Ω(n)        | θ(n^2)        | O(n^2)      |
|Heap Sort      | Ω(n log(n)) | θ(n log(n))   | O(n log(n)) |
|Quick Sort     | Ω(n log(n)) | θ(n log(n))   | O(n^2)      |
|Merge Sort     | Ω(n log(n)) | θ(n log(n))   | O(n log(n)) |
|Bucket Sort    | Ω(n+k)      | θ(n+k)        | O(n^2)      |
|Radix Sort     | Ω(nk)       | θ(nk)         | O(nk)       |


#### Primitive Types vs Non-Primitive Types(Reference Types)
Non-primitive data types are called reference types because they refer to objects.
The main difference between primitive and non-primitive data types are:

+ Primitive types are predefined (already defined) in Java. Non-primitive types are created by the programmer and is not defined by Java (except for String).
Non-primitive types can be used to call methods to perform certain operations, while primitive types cannot.
+ A primitive type has always a value, while non-primitive types can be null.
+ A primitive type starts with a lowercase letter, while non-primitive types starts with an uppercase letter.
+ The size of a primitive type depends on the data type, while non-primitive types have all the same size.