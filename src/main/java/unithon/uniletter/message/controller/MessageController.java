package unithon.uniletter.message.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon.uniletter.member.Member;
import unithon.uniletter.message.dto.MessageListResponse;
import unithon.uniletter.message.dto.MessageRequest;
import unithon.uniletter.message.dto.MessageResponse;
import unithon.uniletter.message.dto.UnreadMessageResponse;
import unithon.uniletter.message.service.MessageService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/messages")
public class MessageController implements MessageControllerDocs {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody final MessageRequest request, final Member member) {
        messageService.sendMessage(request, member);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unread")
    public ResponseEntity<UnreadMessageResponse> getUnreadMessages(final Member member) {
        return ResponseEntity.ok(new UnreadMessageResponse(messageService.getUnreadMessages(member)));
    }

    @GetMapping
    public ResponseEntity<MessageListResponse> getAllMessages(final Member member) {
        final List<MessageResponse> allMessages = messageService.getAllMessages(member);
        return ResponseEntity.ok(new MessageListResponse(allMessages));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getSingleMessage(@PathVariable UUID id, final Member member) {
        return ResponseEntity.ok(messageService.getMessage(id, member));
    }
}