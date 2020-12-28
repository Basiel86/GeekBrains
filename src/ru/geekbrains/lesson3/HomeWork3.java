package ru.geekbrains.lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class HomeWork3 {

    public static void main(String[] args) {

        guessTheNumber();
        guessTheFruit();

    }

    // Task 1
    public static void guessTheNumber() {

        int rnd;
        int guessNumber = 0;
        int tries = 5;
        do {
            rnd = (int) (Math.random() * 10);

            for (int i = 0; i < tries; i++) {

                System.out.print("Угадайте число от 0 до 9: ");
                guessNumber = scannerValidInt();

                if (guessNumber == rnd) {
                    System.out.println("Угадали!!");
                    break;
                } else if (guessNumber < rnd && i < tries - 1) {
                    System.out.println("Загаданное число БОЛЬШЕ!");
                } else if (guessNumber > rnd && i < tries - 1) {
                    System.out.println("Загаданное число МЕНЬШЕ!");
                }
            }

            if (guessNumber != rnd) {
                System.out.print("Не угадали!, загаданное число было равным: " + rnd + ". ");
            }

            do {
                System.out.print("Хотите повторить? (1 - повторить / 0 - нет): ");
                guessNumber = scannerValidInt();
            } while (guessNumber != 1 && guessNumber != 0);

        } while (guessNumber != 0);

    }

    public static int scannerValidInt() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.print("Введите ЦЕЛОЕ число: ");
            scan.next();
        }
        return scan.nextInt();
    }

// Task 2
//someString.matches("[A-F]");


    public static void guessTheFruit() {

        String rndFruit;
        Scanner scan = new Scanner(System.in);
        String guessFruit;

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        System.out.println("Guess any fruit from list below");
        System.out.println(Arrays.toString(words));
        System.out.println("print EXIT for break");

        rndFruit = words[(int) (Math.random() * words.length)];
        //System.out.println("Guess word = " + rndFruit);

        for (; ; ) {
            System.out.print("Enter any fruit name: ");
            guessFruit = scan.nextLine();

            if (guessFruit.equals("EXIT")) {
                break;
            }

            if (guessFruit.equals(rndFruit)) {
                System.out.println("SUCCESS!");
                break;
            } else {
                System.out.println("Matched elements: " + Arrays.toString(matchChars(guessFruit, rndFruit)));
            }
        }

    }

    public static String[] matchChars(String what, String with) {
        // заменил решетки на подчеркивания, не так бьет по глазам при чтении во время вывода
        String[] blankArr = {"_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_"};
        int charPosition = 0;
        while (charPosition != what.length() && charPosition != with.length()) {
            if (what.charAt(charPosition) == with.charAt(charPosition)) {
                blankArr[charPosition] = Character.toString(what.charAt(charPosition));
                charPosition++;
            } else {
                charPosition++;
            }
        }
        return blankArr;
    }
}


