package guru;

public class MethodGuru {
    public static void main(String[] args) {
        method1();
    }
    public static void method1() {
        System.out.println("Метод 1");
        method2();
        System.out.println("Метод 1 продолжение");
    }
    public static void method2() {
        System.out.println("Метод 2");
        method3();
        System.out.println("Метод 2 продолжение");
    }
    public static void method3() {
        System.out.println("Метод 3");
    }
}
