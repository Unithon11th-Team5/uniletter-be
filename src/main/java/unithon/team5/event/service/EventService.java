package unithon.team5.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unithon.team5.common.error.ForbiddenException;
import unithon.team5.event.Event;
import unithon.team5.event.EventType;
import unithon.team5.event.repository.EventRepository;
import unithon.team5.member.Member;
import unithon.team5.message.dto.MessageResponse;
import unithon.team5.message.repository.MessageRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public String addEvent(final Member member, final LocalDate plannedAt, final String content, final EventType type) {
        final Event event = Event.builder()
                .memberId(member.getId())
                .plannedAt(plannedAt)
                .content(content)
                .type(type)
                .build();
        final UUID eventId = eventRepository.save(event).getId();
        return eventId.toString();
    }

    @Transactional(readOnly = true)
    public List<Event> findMemberEventAfterToday(final String memberId) {
        final UUID memberUUID = UUID.fromString(memberId);
        return eventRepository.findEventsAfterToday(memberUUID, LocalDate.now());
    }

    public List<EventType> findEventTypeAll() {
        return Arrays.stream(EventType.values())
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> getMessagesFromEvent(final UUID eventId, final Member member) {

        final Event findEvent = eventRepository.getById(eventId);

        if (!findEvent.getMemberId().equals(member.getId())) {
            throw new ForbiddenException(String.format("[%s] event's member is not equal with current member [%s]", eventId, member.getId()));
        }

        return messageRepository.findAllByEventIdOrderBySendPlannedAtDesc(eventId).stream()
                .map(message -> MessageResponse.of(message, findEvent))
                .toList();
    }
}
