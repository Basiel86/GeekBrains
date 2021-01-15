package ru.geekbrains.lesson7;

public class Cat {
    private final String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite, boolean satiety) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = satiety;
    }

    public void eat(Plate plate) {
        if (satiety == false) {
            System.out.println(name + " eating " + appetite);
            if (plate.decreaseFood(getAppetite())) {
                appetite = 0;
                satiety = true;
            }

        } else {
            System.out.println(name + " is full");
        }
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean getSatiety() {
        return satiety;
    }

    public String getName(){
        return this.name;
    }

}
