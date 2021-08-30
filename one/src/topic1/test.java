package topic1;

/**
 * 举个例子，当时间复杂度相同时只能跑一下然后进行比较
 */
public class test {

    public static void process1() { //O(N)
        int N = 1000;
        int a = 0;
        for (int i = 0; i < N; i++) {
            a = 2 + 5;
            a = 4 * 7;
            a = 6 * 8;
        }
    }

    public static void process2() { //O(N)
        int N = 1000;
        int a = 0;
        for (int i = 0; i < N; i++) {
            a = 3 | 6;
            a = 3 & 4;
            a = 4 ^ 785;
        }
    }

    public static void main(String[] args) {
        process1();
        process2();
    }
}
