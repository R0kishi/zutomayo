package demo1;

import java.time.LocalDateTime;

public class ERyderLog {
    private final String log;
    private final String event;
    private final LocalDateTime timeStamp;
    public ERyderLog(String logId, String eventDescription, LocalDateTime eventTime) {
        this.log = logId;
        this.event = eventDescription;
        this.timeStamp = eventTime;
    }
    public String getLog() {
        return log;
    }
    public String getEvent() {
        return event;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    @Override
    public String toString() {
        return String.format("%s - %s - %s", log, event, timeStamp);
    }
}