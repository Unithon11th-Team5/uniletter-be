package unithon.team5.message.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon.team5.member.Member;
import unithon.team5.message.dto.MessageRequest;
import unithon.team5.message.dto.MessageResponse;
import unithon.team5.message.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody final MessageRequest request, final Member member) {
        messageService.sendMessage(request, member);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unread")
    public ResponseEntity<List<MessageResponse>> getUnreadMessages(final Member member) {
        return ResponseEntity.ok(messageService.getUnreadMessages(member));
    }
}
