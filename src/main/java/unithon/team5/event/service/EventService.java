package unithon.team5.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unithon.team5.event.Event;
import unithon.team5.event.repository.EventRepository;
import unithon.team5.member.Member;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public String addEvent(final Member member, final LocalDate plannedAt, final String content) {
        final Event event = Event.builder()
                .memberId(member.getId())
                .sendPlannedAt(plannedAt.atStartOfDay())
                .content(content)
                .build();
        final UUID eventId = eventRepository.save(event).getId();
        return eventId.toString();
    }
}