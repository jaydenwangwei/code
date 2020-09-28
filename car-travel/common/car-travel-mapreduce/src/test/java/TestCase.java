public class TestCase {
    public static void main(String[] args) {

        // 第一种方式
        multiple(10, 20);

        // 第二种方式
        int a = 10, b = 20;
        multiple(a, b);

    }

    private static void multiple(int a, int b) {
        int result = a * b;
        System.out.println(result);
    }
}
