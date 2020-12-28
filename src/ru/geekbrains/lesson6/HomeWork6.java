package ru.geekbrains.lesson6;


public class HomeWork6 {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();

        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();

        cat1.Run(100);
        cat1.Swim(100);
        cat2.Run(300);

        dog1.Run(200);
        dog1.Swim(1);

        dog2.Run(300);
        dog2.Swim(3);

        dog3.Run(700);
        dog3.Swim(15);

        System.out.println("Всего животных: " + cat1.getAnimalsCount());
        System.out.println("Всего кошек: " + cat1.getCatsCount());
        System.out.println("Всего собак: " + dog1.getDogsCount());

    }

}
