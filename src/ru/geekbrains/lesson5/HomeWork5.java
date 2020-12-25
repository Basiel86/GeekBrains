package ru.geekbrains.lesson5;

class Person {

    private final String fio;
    private String position;
    private String email;
    private String phonenumber;
    private int salary;
    private int age;

    Person(String fio, String position, String email, String phonenumber, int salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phonenumber = phonenumber;
        this.salary = salary;
        this.age = age;
    }

    void printEmploye() {
        System.out.println("ФИО: " + fio);
        System.out.println("Должность: " + position);
        System.out.println("E-mail: " + email);
        System.out.println("Телефон: " + phonenumber);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println("________________");
    }

    int getAge() {
        return age;
    }
}

public class HomeWork5 {

    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 3000, 24);
        persArray[1] = new Person("Petrov Petr", "Pipe Fitter", "ppetrov@mailbox.com", "892368314", 1000, 42);
        persArray[2] = new Person("Sidorov Sergey", "Cleaner", "ssidorov@mailbox.com", "892568913", 1500, 23);
        persArray[3] = new Person("Sichkin Semen", "IT", "s_sichkin@mailbox.com", "892983176", 2500, 33);
        persArray[4] = new Person("Petruhin Anton", "HR", "a_petruhin@mailbox.com", "892531476", 2000, 46);

        System.out.println("Список работников старше 40 лет:");
        for (Person person : persArray) {
            if(person.getAge()>=40){
                person.printEmploye();
            }
        }
    }
}
