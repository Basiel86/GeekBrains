package ru.geekbrains.lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }
    public void info(){
        System.out.println("plate: " + food);
    }

    public int getFood() {
        return food;
    }

    public boolean decreaseFood(int appetite) {
        if(this.food>=appetite){
            this.food-=appetite;
            return true;
        }else{
            System.out.println("Not enough food");
            return false;
        }
    }

    public void addFood(int food){
        this.food+=food;
        System.out.println("Plate is full to " + this.food);
    }
}
