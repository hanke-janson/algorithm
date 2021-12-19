import java.util.PriorityQueue;

/**
 * O(N+logK)，若K特别小甚至可以认为这个是O(N)的算法
 */
public class C2_SortArrayDistanceLessK {

    public void sortedArrDistanceLessK(int[] arr, int k) {
        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        //把前k+1个数放到小根堆中去
        for (; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        //将小根堆中的最小值弹出放到i位置，新加一个数放到小根堆中
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        //系统提供，黑盒
        PriorityQueue<Object> heap = new PriorityQueue<>();
        heap.add(8);
        heap.add(4);
        heap.add(4);
        heap.add(9);
        heap.add(10);
        heap.add(3);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());//O(logN)
        }
    }
}
