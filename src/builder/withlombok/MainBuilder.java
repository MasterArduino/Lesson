package builder.withlombok;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainBuilder {
    public static void main(String[] args) {
        Address address = new Address("Russia", "Lipetsk", "Green", 123);
        BirthInfo birthInfo = new BirthInfo(
                LocalDate.of(1998, 5, 15),
                LocalTime.of(8, 30),
                "Hospital",
                3.5,
                50.0
        );

        Person person = Person.builder()
                .name("Yan")
                .age(25)
                .address(address)
                .birthInfo(birthInfo)
                .build();

        System.out.println(person);
    }
}
