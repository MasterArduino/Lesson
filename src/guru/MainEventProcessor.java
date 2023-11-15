package guru;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class MainEventProcessor {

    public static void main(String[] args) {
        Map<String, Event> eventMap = new HashMap<>();

        // Создаем мероприятия
        eventMap.put("Event1", new Event("Event1", LocalDateTime.now().plusHours(2), "Description1"));
        eventMap.put("Event2", new Event("Event2", LocalDateTime.now().plusHours(3), "Description2"));
        eventMap.put("Event3", new Event("Event3", LocalDateTime.now().plusHours(4), "Description3"));

        // Создаем ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Режим 1: За час до старта мероприятия
        Callable<Void> eventProcessor1 = new EventProcessor(eventMap, 1);
        executorService.submit(eventProcessor1);

        // Режим 2: Пришла нотификация о начале мероприятия
        Callable<Void> eventProcessor2 = new EventProcessor(eventMap, 2);
        executorService.submit(eventProcessor2);

        // Режим 3: Присылай сообщение каждую минуту для активных мероприятий
        Callable<Void> eventProcessor3 = new EventProcessor(eventMap, 3);
        executorService.submit(eventProcessor3);
    }
}
