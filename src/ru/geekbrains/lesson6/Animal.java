package ru.geekbrains.lesson6;

abstract class Animal {

    private static int AnimalsCount;

    abstract void Run(int dist);
    abstract void Swim(int dist);

    int getAnimalsCount() {
        return AnimalsCount;
    }

    void increaseAnimalCout() {
        AnimalsCount++;
    }

    String metersControl(int dist) {
        String meterEnd = "";
        int lastDigit;

        lastDigit = dist % 10;

        if (lastDigit == 1) return " метр";
        if (lastDigit >= 2 && lastDigit < 5) return " метра";
        if (lastDigit == 0 || lastDigit >= 5) return " метров";

        return "";
    }

}
