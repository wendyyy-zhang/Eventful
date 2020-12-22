package event_related;

import java.time.LocalDateTime;

public interface iEvent {
    String getName();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();
}
