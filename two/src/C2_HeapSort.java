import java.util.Arrays;

/**
 * 堆排序
 * 此算法复杂度为O(NlogN)
 * 额外空间复杂度为O(1)
 * 堆的高度是O(logN)级别的
 * heapInsert()代价是O(logN)，往上走只与父节点比较即一个堆的高度
 * heapify()代价是O(logN)，往下走堆的一侧即一个堆的高度
 */
public class C2_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        //成大根堆
//        for (int i = 0; i < arr.length; i++) {//O(N)
//            heapInsert(arr, i);//O(logN)
//        }

        //第一步这个更快些，但时间复杂度不变
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }


        //完成上一步heapInsert后目前此数组为大根堆
        int heapSize = arr.length;
        //交换最大值和最小值的位置，并把heapSize--，相当于将堆范围减小后，去除最大值，此时这个最大值放在heapSize失效的位置上
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {//O(N)
            heapify(arr, 0, heapSize);//O(logN)
            //继续交换此时大根堆上的最大值与最小值，并把heapSize--
            swap(arr, 0, --heapSize);//O(1)
        }
    }

    //堆插入：为的是将数组变为大根堆
    //某数现在处于index位置，往上继续移动
    public static void heapInsert(int[] arr, int index) {
        //(index - 1) / 2 父位置的数
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //堆化：为的是将最大值去除后，其余部分还能成堆
    //某个数在index位置上，能否向下移动
    //heapSize是堆的大小
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;// 左孩子下标
        while (left < heapSize) {// 下方还有孩子，判断左孩子是否越界
            // 两孩子谁值大，把下标赋给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            // 父与较大孩子之间谁值大，把下标赋给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

}
