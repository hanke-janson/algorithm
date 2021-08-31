package topic1;

import java.util.Arrays;

/**
 * 归并排序
 * T(N)=2*T(N/2)+O(N)
 * a=2,b=2,d=1
 * log(2,2) = 1 --> O(N * logN)
 * 没有浪费掉比较行为，比较信息变成了一个整体部分，所以比O(N^2)更好
 */
public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);// 求中点
        mergeSort(arr, l, mid);//前半段递归
        mergeSort(arr, mid + 1, r);//后半段递归
        merge(arr, l, mid, r);//合并两段
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];//初始化一个辅助数组，大小为l到r上的个数
        int i = 0;//help数组下标
        int p1 = l;//arr数组前半段下标
        int p2 = m + 1;//arr数组后半段下标
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];//比较两段数，较小的那个数加到help数组中
        }
        //以下两个while只会中一个
        while (p1 <= m) {//若p1没越界，拷贝p1段剩余的数到help数组中
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {//若p2没越界，拷贝p2段剩余的数到help数组中
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {//把help中的数拷贝到原数组arr中
            arr[l + i] = help[i];
        }
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
            mergeSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        mergeSort(arr);
        printArray(arr);

    }

}
