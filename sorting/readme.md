
# Sorting Algorithm

### Simple sorting: Bubble Sort, Selection Sort, Insertion Sort
+ BS, SS, IS all run in O(n^2) time in the worst case.
However, often IS performs better than the other two
bc/ it may require less # of comparisons depending on the input values(e.g. almost sorted arr)
& uses copying instead of swapping! (NO SWAP in IS)

+ **BS < SS**       - less $ of swaps.
  **BS, SS < IS**   - less # of comparions. - no swap. only copy!

*** 

### Better sorting: Merge Sort, Quick Sort
+ In **Merge sort**, dividing the input arr is simple and easy, 
but it's expensive to merge these sorted left & right sub arr. 

+ In **Quick sort**, dividing the original problem into sub-problems(partitioning) is 
more complicated and expensive whereas combining the result from sub-problems together is easy.

*** 

### Sorting in Java
There are 2 Data type in java: 
1) Primitive Data Type: orders are well defined. no need to worry about stability.
2) Object: need to care about stability.


+ **Before Java6** <br/>
    PDT) Quick sort -> Insertion sort <br/>
    Obj) Merge sort -> Insertion sort <br/>
    
    as we call recursive calls and the boundary gets smaller, 
    Java translates the sort into the insertion sort. <br/>
    bc/ when elems are almost sorted, IS is fast & best!! 
    
+ **After Java7** <br/>
    PDT: Dual pivot quick sort (using 2 pivots) <br/>
    Obj: Tim sort (similar to ms + is) // linear time in best case. <br/>



#### Primitive Types vs Non-Primitive Types(Reference Types)
Non-primitive data types are called reference types because they refer to objects.
The main difference between primitive and non-primitive data types are:

+ Primitive types are predefined (already defined) in Java. Non-primitive types are created by the programmer and is not defined by Java (except for String).
Non-primitive types can be used to call methods to perform certain operations, while primitive types cannot.
+ A primitive type has always a value, while non-primitive types can be null.
+ A primitive type starts with a lowercase letter, while non-primitive types starts with an uppercase letter.
+ The size of a primitive type depends on the data type, while non-primitive types have all the same size.