# 算法与数据结构学习

## 算法

### 一、查找算法：

#### 1、线性查找法 [LinearSearch.java](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/algorithm/LinearSearch.java)

### 二、排序算法

#### 1、选择排序法 [SelectionSort.java](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/algorithm/SelectionSort.java)

#### 2、插入排序法 [InsertionSort.java](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/algorithm/InsertionSort.java)

> 对于插入排序法，当数组是有序的时候，时间复杂度将变成O(n)，而选择排序法一直是O(n^2)

测试用例：

```java
public class Main {

    public static void main(String[] args) {
        int[] dataSize = { 10000, 100000 };
        for (int n : dataSize) {

            System.out.println("Random Array:");

            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr1 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest(InsertionSort.class, arr);
            SortingHelper.sortTest(SelectionSort.class, arr1);

            System.out.println("===================");
            System.out.println("Ordered Array:");

            arr = ArrayGenerator.generateOrderedArray(n);
            arr1 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest(InsertionSort.class, arr);
            SortingHelper.sortTest(SelectionSort.class, arr1);

            System.out.println();
        }
    }
}

```

结果：

```txt
Random Array:
InsertionSort success, n = 10000 : 0.158684 s
SelectionSort success, n = 10000 : 0.116741 s
===================
Ordered Array:
InsertionSort success, n = 10000 : 0.000127 s
SelectionSort success, n = 10000 : 0.116045 s

Random Array:
InsertionSort success, n = 100000 : 9.009850 s
SelectionSort success, n = 100000 : 9.330485 s
===================
Ordered Array:
InsertionSort success, n = 100000 : 0.000577 s
SelectionSort success, n = 100000 : 8.880783 s
```

> 可以很明显的看出来，对于有序的数组，插入排序的效率比选择排序的效率要高得多

## 数据结构

### 一、线性结构：

#### 1、动态数组：[ArrayList.java](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/dataStructure/ArrayList.java)

> 关于范型数组的初始化：Java是并不推荐直接使用`E[] data = (E[]) new Object[n];`的方式进行初始化一个范型数组的。

> 建议使用`Object[] data = new Object[n];`，在调用接口时进行强转：`E e = (E) data[index];`。参见：[ArrayList.java#L8](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/dataStructure/ArrayList.java#L8)，[ArrayList.java#L96](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/dataStructure/ArrayList.java#L96)。

#### 2、栈

- 基于动态数组实现的栈：[ArrayStack.java](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/dataStructure/ArrayStack.java)

#### 3、队列

- 基于动态数组实现的数组队列：[ArrayQueue.java](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/dataStructure/ArrayQueue.java)
- 循环队列：[LoopQueue.java](https://github.com/hanjinfeng0309/AlgorithmAndDataStructure/blob/main/src/main/dataStructure/LoopQueue.java)

**数组队列与循环队列的效率比较（比较10w次的出队操作和入队操作）：**

```java
public class Test {

    private static double testQueue(Queue<Integer> queue, int n) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < n; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int count = 100000;

        Queue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, count);
        System.out.println("Array Queue, time: " + time1 + " s.");

        Queue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, count);
        System.out.println("Loop Queue, time: " + time2 + " s.");
    }
}
```

结果：循环队列的效率要高于普通的数组队列

```
Array Queue, time: 3.070826691 s.
Loop Queue, time: 0.012834873 s.
```

