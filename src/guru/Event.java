package guru;

import java.time.LocalDateTime;
import java.util.Map;

public class Event {
    private String name;
    private LocalDateTime date;
    private String description;
    private boolean isActive;

    public Event(String name, LocalDateTime date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.isActive = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
