package guru;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Lenovo\\Desktop\\JAva\\Тест.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder text = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                text.append(line).append("\n");
            }

            fileReader.close();

            // Подсчет количества знаков препинания
            int punctuationCount = countPunctuation(text.toString());

            // Подсчет количества слов
            int wordCount = countWords(text.toString());

            // Вывод результатов
            System.out.println("Количество знаков препинания: " + punctuationCount);
            System.out.println("Количество слов: " + wordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для подсчета количества знаков препинания
    private static int countPunctuation(String text) {
        Pattern pattern = Pattern.compile("[.,!?;:()\\-—\"']");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    // Метод для подсчета количества слов
    private static int countWords(String text) {
        String[] words = text.split("\\s+"); // Разделение текста на слова по пробелам
        return words.length;
    }
}
