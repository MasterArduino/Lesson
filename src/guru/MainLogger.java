package guru;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


enum LogLevel {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL
}

class Logger {
    private static Logger instance;
    private LogLevel logLevel;

    private Logger(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public static  Logger getInstance(LogLevel logLevel) {
        if (instance == null) {
            instance = new Logger(logLevel);
        }
        return instance;
    }

    public void log(LogLevel messageLevel, String message) {
        if (messageLevel.compareTo(logLevel) >= 0) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
                String logMessage = String.format("[%s] %s %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), messageLevel, message);
                writer.println(logMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MainLogger {
    public static void main(String[] args) {

        Logger logger = Logger.getInstance(LogLevel.DEBUG);
        logger.log(LogLevel.INFO, "Это информационное сообщение.");
        logger.log(LogLevel.ERROR, "Это сообщение об ошибке.");
    }
}
