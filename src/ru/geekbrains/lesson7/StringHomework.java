package ru.geekbrains.lesson7;

import java.util.Locale;

public class StringHomework {
    public static void main(String[] args) {

        String str = "I like Java!!!";

        StringProcess(str);
    }

    public static void StringProcess(String str) {
// a) Напишите метод, который принимает в качестве параметра любую строку, например “I like Java!!!”.
        System.out.println(str);
// б) Распечатать последний символ строки. Используем метод String.charAt().
        System.out.print("Last symbol is \"");
        System.out.println(str.charAt(str.length() - 1) + "\"");
// в) Проверить, заканчивается ли ваша строка подстрокой “!!!”. Используем метод String.endsWith().
        String endCheck = "!!!";
        System.out.println("String ends with \"" + endCheck + "\" : " + str.endsWith(endCheck));
// г) Проверить, начинается ли ваша строка подстрокой “I like”. Используем метод String.startsWith().
        String startCheck = "I Like";
        System.out.println("String starts with \"" + startCheck + "\" : " + str.startsWith(startCheck));
// д) Проверить, содержит ли ваша строка подстроку “Java”. Используем метод String.contains().
        String containsCheck = "Java";
        System.out.println("String contains \"" + containsCheck + "\" : " + str.contains(containsCheck));
// e) Найти позицию подстроки “Java” в строке “I like Java!!!”.
        System.out.println("The position of \"" + containsCheck + "\" : " + str.indexOf(containsCheck));
// ж) Заменить все символы “а” на “о”.
        System.out.println(str.replace('a', 'o'));
// з) Преобразуйте строку к верхнему регистру.
        System.out.println(str.toUpperCase(Locale.ROOT));
// и) Преобразуйте строку к нижнему регистру.
        System.out.println(str.toLowerCase(Locale.ROOT));
// к) Вырезать строку Java c помощью метода String.substring().
        String cutValue = "Java";
        int cutValuePos = str.indexOf(cutValue);
        int cutValueLength = cutValue.length();
        System.out.println(str.substring(0, cutValuePos) + str.substring(cutValuePos + cutValueLength, str.length()));
    }
}
