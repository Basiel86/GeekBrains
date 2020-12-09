package ru.geekbrains.lesson2;

public class HomeWork2 {

    public static void main(String[] args) {

        int[] Arr = {1, 1, 0, 1, 0, 1, 1};
        int[] Arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] Arr3 = {30, 14, 2, -10, 1, 2, 2, 3, 37};


        //1
        printArrayOneDimm(Arr);
        invertArray(Arr);
        printArrayOneDimm(Arr);

        //2
        fillArray();

        //3
        multiplyArrayElemLess6By2();

        //4
        arrayDiagonals(10);

        //5
        printArrayOneDimm(Arr3);
        arrayMinMaxPrint(Arr3);

        //6
        printArrayOneDimm(Arr3);
        System.out.println(arraySearchEqualSumms(Arr3));

        //7
        printArrayOneDimm(Arr2);
        arrayShiftByN(Arr2,5);
        printArrayOneDimm(Arr2);
    }


// 1 Инвертирование массива

    static int[] invertArray(int[] Array) {
        for (int i = 0; i < Array.length; i++) {
            if (Array[i] == 0) {
                Array[i] = 1;
            } else {
                Array[i] = 0;
            }
        }
        return Array;
    }

//2 создание и заполнение массива через цикл

    static void fillArray() {
        int[] Arr = new int[8];
        for (int i = 0; i < Arr.length; i++) {
            switch (i) {
                case 0:
                    Arr[i] = 0;
                    break;
                case 1:
                    Arr[i] = 3;
                    break;
                case 2:
                    Arr[i] = 6;
                    break;
                case 3:
                    Arr[i] = 9;
                    break;
                case 4:
                    Arr[i] = 12;
                    break;
                case 5:
                    Arr[i] = 15;
                    break;
                case 6:
                    Arr[i] = 18;
                    break;
                case 7:
                    Arr[i] = 21;
                    break;
            }
        }
        printArrayOneDimm(Arr);
        //arr = {0, 3, 6, 9, 12, 15, 18, 21};
    }

    // 3 создать кастом массив и умножить элементы менее 6ти на 2

    static void multiplyArrayElemLess6By2() {
        int[] Arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArrayOneDimm(Arr);
        for (int i = 0; i < Arr.length; i++) {
            if (Arr[i] < 6) Arr[i] *= 2;
        }
        printArrayOneDimm(Arr);
    }


    // 4 диагональная линия в массиве из 3ек

    static void arrayDiagonals(int arraySize) {
        int[][] Arr = new int[arraySize][arraySize];
        for (int i = 0; i < Arr.length; i++) {
            Arr[i][i] = 1;
            Arr[i][arraySize - i - 1] = 1;
        }
        printArrayTwoDimm(Arr);
    }

    // 5

    static void arrayMinMaxPrint(int[] Arr) {
        // присваиваем значение первого элемента гарантированно попадая
        int minVal = Arr[0];
        int maxVal = Arr[0];
        for (int i = 0; i < Arr.length; i++) {
            if (Arr[i] < minVal) {
                minVal = Arr[i];
            }
            if (Arr[i] > maxVal) {
                maxVal = Arr[i];
            }
        }
        if (minVal < maxVal) {
            System.out.println("Минимальное значение массива: " + minVal);
            System.out.println("Максимальное значение массива: " + maxVal);
        } else {
            System.out.println("Элементы массива одинаковы и равны " + minVal);
        }
    }

    //6 поиск равных сумм внутри массива

    static boolean arraySearchEqualSumms(int[] Arr) {

        if (Arr.length == 1) {
            System.out.println("Массив из 1го элемента - нечего сравнивать");
            return false;
        }

        int fromLeft = 0;
        int fromRigth = Arr.length - 1;
        int sumLeft = Arr[0];
        int sumRigth = Arr[Arr.length - 1];

        // движемся с концов к центру сравнивая и увеличивая суммы
        // выходим по соприкосновении индексов
        while (fromRigth - fromLeft > 1) {
            if (sumLeft < sumRigth) {
                sumLeft += Arr[++fromLeft];
            } else {
                sumRigth += Arr[--fromRigth];
            }
        }

        System.out.println("Левая сумма: " + sumLeft);
        System.out.println("Правая сумма: " + sumRigth);
        return (sumLeft == sumRigth);
    }


    // 7 Создал 2 дополнительные функции для сдвига массива вверх и вниз на 1н порядок

    // сдвиг массива вниз на 1цу
    static int[] arrayShiftDWN(int[] Arr) {
        int tmpVal = 0;

        for (int i = Arr.length - 1; i > 0; i--) {

            if (i == Arr.length - 1) {
                tmpVal = Arr[i];
            }

            Arr[i] = Arr[i - 1];
        }
        Arr[0] = tmpVal;
        return Arr;
    }

    // сдвиг массива вверх на 1цу
    static int[] arrayShiftUP(int[] Arr) {
        int tmpVal = 0;

        for (int i = 1; i < Arr.length; i++) {

            if (i == 1) {
                tmpVal = Arr[0];
            }

            Arr[i - 1] = Arr[i];
        }
        Arr[Arr.length - 1] = tmpVal;
        return Arr;
    }

    // основной метод для сдвига на N значений
    static int[] arrayShiftByN(int[] Arr, int shiftVal) {
        boolean direction = false;

        // сперва определяю направление куда нам надо шифтить
        if (shiftVal < 0) {
            direction = false;
        } else {
            direction = true;
        }

        // оставляю целое число и дабы не делать лишней работы, убираю ненужные итерации остатоком от деления
        shiftVal = Math.abs(shiftVal);
        shiftVal %= Arr.length;


        if (shiftVal == 0) {
            System.out.println("Результирующий массив равен исходному");
            return Arr;
        }

        for (int i = 1; i <= shiftVal; i++) {
            if (direction == true) {
                arrayShiftDWN(Arr);
            } else {
                arrayShiftUP(Arr);
            }
        }

        return Arr;
    }


    // процедура вывода массива в консоль для проверок

    static void printArrayOneDimm(int[] Array) {
        for (int i = 0; i < Array.length; i++) {
            System.out.print(Array[i] + " ");
        }
        System.out.println();
    }

    static void printArrayTwoDimm(int[][] Array) {
        for (int i = 0; i < Array[0].length; i++) {
            for (int j = 0; j < Array[1].length; j++) {
                System.out.print(Array[i][j] + " ");
            }
            System.out.println();
        }
    }
}


