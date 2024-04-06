package unithon.team5.message.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import unithon.team5.event.EventType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    @NotEmpty
    private String receiverNickname;

    @NotEmpty
    private String senderName;

    @NotEmpty
    @Length(max = 300)
    private String content;

    @NotNull
    private EventType type;

    @NotNull
    @Future
    private LocalDate sendPlannedAt;
}
