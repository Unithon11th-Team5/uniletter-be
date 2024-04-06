package unithon.team5.event.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon.team5.event.dto.EventAddRequest;
import unithon.team5.event.dto.EventResponse;
import unithon.team5.event.service.EventService;
import unithon.team5.member.Member;

import java.net.URI;

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
    public ResponseEntity<EventResponse> readAllEvent(@RequestParam final String memberId) {
        final EventResponse response = eventService.findMemberEventAfterToday(memberId)
                .map(EventResponse::from)
                .orElse(null);
        return ResponseEntity.ok(response);
    }
}
