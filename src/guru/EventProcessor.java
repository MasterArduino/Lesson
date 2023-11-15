package guru;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class EventProcessor implements Callable<Void> {
    private Map<String, Event> eventMap;
    private int mode;

    public EventProcessor(Map<String, Event> eventMap, int mode) {
        this.eventMap = eventMap;
        this.mode = mode;
    }

    @Override
    public Void call() throws Exception {
        while (true) {
            LocalDateTime now = LocalDateTime.now();

            switch (mode) {
                case 1:
                    // Режим 1: За час до старта мероприятия
                    for (Event event : eventMap.values()) {
                        LocalDateTime eventStart = event.getDate();
                        Duration duration = Duration.between(now, eventStart);

                        if (duration.toHours() == 1) {
                            System.out.println("Режим 1: Название мероприятия - " + event.getName() +
                                    ", Время до старта - " + duration.toMinutes() + " минут");
                        }
                    }
                    break;

                case 2:
                    // Режим 2: Пришла нотификация о начале мероприятия
                    for (Event event : eventMap.values()) {
                        if (!event.isActive() && now.isAfter(event.getDate())) {
                            event.setActive(true);
                            System.out.println("Режим 2: Началось мероприятие - " + event.getName());
                        }
                    }
                    break;

                case 3:
                    // Режим 3: Присылай сообщение каждую минуту для активных мероприятий
                    for (Event event : eventMap.values()) {
                        if (event.isActive() && now.isAfter(event.getDate())) {
                            System.out.println("Режим 3: Мероприятие уже началось - " + event.getName());
                        }
                    }
                    break;
            }

            // Пауза между проверками
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
