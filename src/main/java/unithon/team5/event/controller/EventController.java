package unithon.team5.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unithon.team5.event.service.EventService;
import unithon.team5.event.dto.EventAddRequest;
import unithon.team5.member.Member;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class EventController {

    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<Void> addEvent(@RequestBody final EventAddRequest eventAddRequest, final Member member) {
        final String uuid = eventService.addEvent(member, eventAddRequest.plannedAt(), eventAddRequest.content());
        return ResponseEntity.created(URI.create("/events/" + uuid)).build();
    }
}
