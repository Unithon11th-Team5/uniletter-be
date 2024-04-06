package unithon.uniletter.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon.uniletter.event.Event;
import unithon.uniletter.event.EventType;

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
