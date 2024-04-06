package unithon.team5.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon.team5.message.MessageType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    @NotEmpty
    private String receiverNickname;
    @NotEmpty
    private String senderName;
    @NotEmpty
    private String content;
    @NotNull
    private MessageType type;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime sendPlannedAt;
}
