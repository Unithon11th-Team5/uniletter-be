package unithon.team5.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon.team5.message.Message;
import unithon.team5.message.MessageType;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private UUID id;
    private String senderName;
    private String content;
    private LocalDate sentAt;
    private MessageType type;

    public static MessageResponse of(final Message message) {
        return new MessageResponse(
                message.getId(),
                message.getSenderName(),
                message.getContent(),
                message.getSendPlannedAt(),
                message.getType()
        );
    }
}
