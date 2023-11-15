package guru;

import java.io.*;
import java.util.Random;

public class DirectoryAndFileManipulation {
    public static void main(String[] args) {

        String rootDirectoryPath = "C:\\Users\\Lenovo\\Desktop\\JAva";
        createDirectories(rootDirectoryPath, 3);

        // Создание 5 файлов с 10 произвольными числами в конечной папке
        String finalDirectoryPath = rootDirectoryPath + "\\guruJava"; // Путь к конечной папке
        createFilesWithNumbers(finalDirectoryPath, 5, 10);

        // Запись содержимого файлов в один файл
        mergeFilesContent(finalDirectoryPath);

        // Создание файла, содержащего список файлов данного каталога
        createFileList(finalDirectoryPath);
    }

    // Метод для создания цепочки папок
    private static void createDirectories(String rootPath, int depth) {
        File root = new File(rootPath);
        if (!root.exists()) {
            root.mkdirs();
            System.out.println("Цепочка папок создана.");
        } else {
            System.out.println("Цепочка папок уже существует.");
        }
        if (depth > 1) {
            for (int i = 1; i < depth; i++) {
                File subDirectory = new File(root, "folder" + i);
                subDirectory.mkdirs();
            }
        }
    }

    // Метод для создания файлов с произвольными числами
    private static void createFilesWithNumbers(String directoryPath, int fileCount, int numbersCount) {
        Random random = new Random();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        for (int i = 1; i <= fileCount; i++) {
            File file = new File(directory, "file" + i + ".txt");
            try (FileWriter fileWriter = new FileWriter(file);
                 BufferedWriter writer = new BufferedWriter(fileWriter)) {
                for (int j = 0; j < numbersCount; j++) {
                    writer.write(String.valueOf(random.nextInt(100)));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Метод для объединения содержимого файлов в один файл
    private static void mergeFilesContent(String directoryPath) {
        File finalFile = new File(directoryPath, "mergedFile.txt");
        try (FileWriter fileWriter = new FileWriter(finalFile);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (int i = 1; i <= 5; i++) {
                File file = new File(directoryPath, "file" + i + ".txt");
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для создания файла, содержащего список файлов данного каталога
    private static void createFileList(String directoryPath) {
        File directory = new File(directoryPath);
        File fileList = new File(directoryPath, "fileList.txt");
        try (PrintWriter writer = new PrintWriter(fileList)) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    writer.println(file.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}