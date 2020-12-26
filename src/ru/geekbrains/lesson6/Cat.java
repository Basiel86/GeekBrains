package ru.geekbrains.lesson6;

class Cat extends Animal {
    static int CatsCount;

    Cat() {
        super.increaseAnimalCout();
        CatsCount++;
    }

    @Override
    void Run(int dist) {
        final int RUN_MAX_DIST = 200;
        if (dist > RUN_MAX_DIST) {
            System.out.println("Кошка не может пробежать более " + RUN_MAX_DIST + super.metersControl(dist));
        } else {
            System.out.println("Кошка пробежала " + dist + super.metersControl(dist));
        }
    }

    @Override
    void Swim(int dist) {
        System.out.println("Кошка не умеет плавать");
    }

    int getCatsCount() {
        return CatsCount;
    }
}
