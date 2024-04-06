package unithon.team5.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon.team5.event.Event;
import unithon.team5.event.EventType;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageEventResponse {

    private UUID id;
    private EventType type;
    private String content;
    private LocalDate plannedAt;

    public static MessageEventResponse of(Event event) {
        return new MessageEventResponse(
                event.getId(),
                event.getType(),
                event.getContent(),
                event.getPlannedAt()
        );
    }
}
