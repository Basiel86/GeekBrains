package ru.geekbrains.lesson6;

class Dog extends Animal {
    static int DogsCount;

    Dog() {
        super.increaseAnimalCout();
        DogsCount++;
    }

    @Override
    void Run(int dist) {
        final int RUN_MAX_DIST = 500;
        if (dist > RUN_MAX_DIST) {
            System.out.println("Собака не может пробежать более " + RUN_MAX_DIST + super.metersControl(dist));
        } else {
            System.out.println("Собака пробежала " + dist + super.metersControl(dist));
        }
    }

    @Override
    void Swim(int dist) {
        final int SWIM_MAX_DIST = 10;
        if (dist > SWIM_MAX_DIST) {
            System.out.println("Собака не может проплыть более " + SWIM_MAX_DIST + super.metersControl(dist));
        } else {
            System.out.println("Собака проплыла " + dist + super.metersControl(dist));
        }
    }

    int getDogsCount() {
        return DogsCount;
    }
}
