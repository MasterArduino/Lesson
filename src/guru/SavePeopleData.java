package guru;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class Person {
    private String name;
    private String surname;
    private int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public static Person createRandomPerson() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Isabella", "Jack"};
        String[] surnames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

        Random random = new Random();
        String randomName = names[random.nextInt(names.length)];
        String randomSurname = surnames[random.nextInt(surnames.length)];
        int randomAge = 18 + random.nextInt(50); // Генерация возраста от 18 до 67

        return new Person(randomName, randomSurname, randomAge);
    }

    public String toString() {
        return "Name: " + name + ", Surname: " + surname + ", Age: " + age;
    }
}

public class SavePeopleData {
    public static void main(String[] args) {
        String fileName = "people.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < 10; i++) {
                Person person = Person.createRandomPerson();
                writer.write(person.toString());
                writer.newLine();
            }
            System.out.println("Данные сохранены в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
