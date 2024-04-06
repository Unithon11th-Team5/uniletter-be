package unithon.team5.message.dto;

import java.util.List;

public record UnreadMessageResponse(List<MessageResponse> messages) {
}
