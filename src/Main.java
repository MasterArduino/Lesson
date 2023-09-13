import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums1[]= {1,1,2,0,0,0};
        int m =3;
        int nums2[]= {1,1,6};
        int n = 3;
        solution.merge(nums1,m,nums2,n);
// Выводим объединенный массив nums1
        for (int i = 0; i < m + n; i++) {
            System.out.print(nums1[i] + " ");



        }
        String s1 = "Hello";
        String s2 = String.copyValueOf(s1.toCharArray());
        boolean result = s1 == s2;
        System.out.println("Result: " + result);
    }
}
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int j = 0, i = m; j < n; j++) {
            nums1[i] = nums2[j];
            i++;
        }
         Arrays.sort(nums1);
    }
}
class MyMath {

    // Перегруженный метод для сложения двух целых чисел
    public int add(int a, int b) {
        return a + b;
    }

    // Перегруженный метод для сложения двух чисел с плавающей точкой
    public String add(int b, String a) {
        return "a" + b;
    }

    // Перегруженный метод для конкатенации строк
    public String add(String a, String b) {
        return a + b;
    }
}

class Parent {
    void printMessage() {
        System.out.println("Hello from Parent");
    }
}

class Child extends Parent {
    // Перегрузка метода printMessage
    void printMessage() {
        System.out.println("Child says: ");
    }
}