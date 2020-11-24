
package ru.geekbrains.lesson1;

public class HomeWork1 {
    // 1
    public static void main(String[] args) {
        //2
        byte byt = 127;
        short shr = 32000;
        int i = 5;
        long j = 123;
        float fl = 0.5f;
        double dbl = 0.7654;
        char ch = 'z';
        boolean fls = false;

        System.out.println(method3(2,4,10,2));
        System.out.println(method4(11,6));
        method5(-7);
        System.out.println(method6(-5));
        method7("User");
        method8one(1900);
        method8one(2000);
        method8one(2100);
        method8two(1900);
        method8two(2000);
        method8two(2100);
    }

    // 3
    static int method3(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    //4
    static boolean method4(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    //5
    static void method5(int a) {
        if (a >= 0) {
            System.out.println(a + " - положительное число");
        } else {
            System.out.println(a + " - отрицательное число");
        }
    }

    //6
    static boolean method6(int a) {
        return a < 0;
    }

    //7
    static void method7(String str) {
        System.out.println("Привет, " + str + "!");
    }

    //8
    // первой реализацией пошел от проверки на високосность, что привело к громоздкости, решил пойти от обратного
    // второй метод от обратного вышел 2ды короче
    static void method8one(int year) {

        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 == 0) {
                System.out.println(year + " год - Високосный");
            } else if (year % 100 != 0 && year % 400 != 0) {
                System.out.println(year + " год - Високосный");
            } else System.out.println(year + " год - НЕ Високосный");
        } else {
            System.out.println(year + " год - НЕ Високосный");
        }
    }

    static void method8two(int year) {

        if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
            System.out.println(year + " год - НЕ Високосный");
        } else {
            System.out.println(year + " год - Високосный");
        }
    }

}