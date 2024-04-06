package unithon.team5.event.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon.team5.event.dto.*;
import unithon.team5.event.service.EventService;
import unithon.team5.member.Member;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class EventController implements EventControllerDocs {

    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<Void> addEvent(@RequestBody @Valid final EventAddRequest eventAddRequest, final Member member) {
        final String uuid = eventService.addEvent(member, eventAddRequest.plannedAt(), eventAddRequest.content(), eventAddRequest.type());
        return ResponseEntity.created(URI.create("/events/" + uuid)).build();
    }

    @GetMapping("/events")
    public ResponseEntity<EventListResponse> readAllEvent(@RequestParam final String memberId) {
        final var events = eventService.findMemberEventAfterToday(memberId);
        final List<EventResponse> responses = EventResponse.createList(events);
        return ResponseEntity.ok(new EventListResponse(responses));
    }

    @GetMapping("/events/types")
    public ResponseEntity<TypeListResponse> readAllTypes() {
        final List<TypeResponse> responses = eventService.findEventTypeAll().stream()
                .map(TypeResponse::from)
                .toList();

        return ResponseEntity.ok(new TypeListResponse(responses));
    }
}
