package unithon.uniletter.message.dto;

import java.util.List;

public record UnreadMessageResponse(List<MessageResponse> messages) {
}
