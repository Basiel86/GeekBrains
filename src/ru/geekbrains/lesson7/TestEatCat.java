package ru.geekbrains.lesson7;

public class TestEatCat {

    public static void main(String[] args) {
        Cat[] Cats = {new Cat("Barsik",5,false),
                      new Cat("Kesha",21,false),
                      new Cat("Tom",90,false),
                      new Cat("Felix",10,false),
                      new Cat("Fluffy",65,false)};
        Plate plate = new Plate(100);

        for (Cat cat : Cats) {
            plate.info();
            cat.eat(plate);
            if (plate.getFood() < 15) {
                plate.addFood(17);
            }
        }

        for (Cat cat : Cats) {
            System.out.println(cat.getName() + "'s satiety: " + cat.getSatiety());
        }
    }

}
