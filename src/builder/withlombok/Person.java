package builder.withlombok;

public class Person {
    private String name;
    private int age;
    private Address address;
    private BirthInfo birthInfo;

    private Person() {}

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {
        private final Person person = new Person();

        public PersonBuilder name(String name) {
            person.name = name;
            return this;
        }

        public PersonBuilder age(int age) {
            person.age = age;
            return this;
        }

        public PersonBuilder address(Address address) {
            person.address = address;
            return this;
        }

        public PersonBuilder birthInfo(BirthInfo birthInfo) {
            person.birthInfo = birthInfo;
            return this;
        }

        public Person build() {
            return person;
        }
    }
}
