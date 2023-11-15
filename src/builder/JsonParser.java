package builder;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class JsonParser {
    public static void main(String[] args) {
        try {
            // Чтение JSON из файла в виде массива байт
            byte[] jsonData = Files.readAllBytes(Paths.get("builder/academy.json"));

            // Создание объекта ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Преобразование массива байт в объект
            UserData userData = objectMapper.readValue(jsonData, UserData.class);

            // Вывод объекта на печать
            System.out.println(userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UserData {
    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private int[] phoneNumbers;
    private String role;
    private String[] cities;


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permanent=" + permanent +
                ", address=" + address +
                ", phoneNumbers=" + Arrays.toString(phoneNumbers) +
                ", role='" + role + '\'' +
                ", cities=" + Arrays.toString(cities) +
                '}';
    }
}

class Address {
    private String street;
    private String city;
    private int zipcode;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}
