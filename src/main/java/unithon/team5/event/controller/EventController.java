package unithon.team5.event.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon.team5.event.dto.*;
import unithon.team5.event.service.EventService;
import unithon.team5.member.Member;
import unithon.team5.message.dto.MessageListResponse;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController implements EventControllerDocs {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Void> addEvent(@RequestBody @Valid final EventAddRequest eventAddRequest, final Member member) {
        final String uuid = eventService.addEvent(member, eventAddRequest.plannedAt(), eventAddRequest.content(), eventAddRequest.type());
        return ResponseEntity.created(URI.create("/events/" + uuid)).build();
    }


    @GetMapping
    public ResponseEntity<EventListResponse> readAllEvent(@RequestParam final String memberId) {
        final var events = eventService.findMemberEventAfterToday(memberId);
        final List<EventResponse> responses = EventResponse.createList(events);
        return ResponseEntity.ok(new EventListResponse(responses));
    }

    @GetMapping("/types")
    public ResponseEntity<TypeListResponse> readAllTypes() {
        final List<TypeResponse> responses = eventService.findEventTypeAll().stream()
                .map(TypeResponse::from)
                .toList();

        return ResponseEntity.ok(new TypeListResponse(responses));
    }

    @GetMapping("/messages")
    public ResponseEntity<MessageListResponse> getMessages(@RequestParam UUID eventId, final Member member) {
        return ResponseEntity.ok(new MessageListResponse(eventService.getMessagesFromEvent(eventId, member)));
    }
}
