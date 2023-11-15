package builder;
import java.util.Scanner;

class Person {
    private String firstName;
    private String lastName;
    private int birthYear;
    private String address;

    private Person() {

    }

    public static class Builder {
        private Person person;

        public Builder() {
            person = new Person();
        }

        public Builder setFirstName(String firstName) {
            person.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            person.lastName = lastName;
            return this;
        }

        public Builder setBirthYear(int birthYear) {
            person.birthYear = birthYear;
            return this;
        }

        public Builder setAddress(String address) {
            person.address = address;
            return this;
        }

        public Person build() {
            return person;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthYear=" + birthYear +
                ", address='" + address + '\'' +
                '}';
    }
}

public class MainBuilder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();

        System.out.println("Введите фамилию:");
        String lastName = scanner.nextLine();

        System.out.println("Введите год рождения:");
        int birthYear = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после ввода числа

        System.out.println("Введите адрес:");
        String address = scanner.nextLine();

        // Использование строителя для создания объекта Person
        Person person = new Person.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthYear(birthYear)
                .setAddress(address)
                .build();

        System.out.println("Созданный объект Person: " + person);
    }
}
