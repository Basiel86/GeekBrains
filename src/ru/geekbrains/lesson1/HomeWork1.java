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

         System.out.println(solveEquation(2,4,10,2));
         System.out.println(sumInTenToTwentyRange(11,6));
         isPositive(-7);
         System.out.println(isNegative(-5));
         printHelloInput("User");
         isLeapYear(1900);
         isLeapYear(2000);
         isLeapYear(2100);
         isLeapYearSnd(1900);
         isLeapYearSnd(2000);
         isLeapYearSnd(2100);
     }

     // 3
     static float solveEquation (float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

     //4
     static boolean sumInTenToTwentyRange(int a, int b) {
         int sum = a + b;
         return sum >= 10 && sum <= 20;
     }

     //5
     static void isPositive(int a) {
         if (a >= 0) {
             System.out.println(a + " - положительное число");
         } else {
             System.out.println(a + " - отрицательное число");
         }
     }

     //6
     static boolean isNegative(int a) {
        return a < 0;
    }

     //7
     static void printHelloInput(String str) {
        System.out.println("Привет, " + str + "!");
    }

     //8
     // первой реализацией пошел от проверки на високосность, что привело к громоздкости, решил пойти от обратного
     // второй метод от обратного вышел 2ды короче
     static void isLeapYear(int year) {

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

     static void isLeapYearSnd(int year) {

         if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
             System.out.println(year + " год - НЕ Високосный");
         } else {
             System.out.println(year + " год - Високосный");
         }
     }
 }