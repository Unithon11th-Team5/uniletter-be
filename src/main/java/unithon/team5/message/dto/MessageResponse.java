package unithon.team5.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon.team5.event.Event;
import unithon.team5.event.EventType;
import unithon.team5.message.Message;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private UUID id;
    private String senderName;
    private MessageEventResponse event;
    private String content;
    private LocalDate sentAt;
    private EventType type;

    public static MessageResponse of(final Message message, final Event event) {
        if (event == null) {
            return new MessageResponse(
                    message.getId(),
                    message.getSenderName(),
                    null,
                    message.getContent(),
                    message.getSendPlannedAt(),
                    message.getType()
            );
        }
        return new MessageResponse(
                message.getId(),
                message.getSenderName(),
                MessageEventResponse.of(event),
                message.getContent(),
                message.getSendPlannedAt(),
                message.getType()
        );
    }
}
