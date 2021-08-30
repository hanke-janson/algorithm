package topic5;

public class test {
    public static void main(String[] args) {
        int a = 10;
        int b = 1846;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }
}
