package unithon.uniletter.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unithon.uniletter.common.error.ForbiddenException;
import unithon.uniletter.common.error.NotFoundException;
import unithon.uniletter.event.Event;
import unithon.uniletter.event.EventType;
import unithon.uniletter.event.repository.EventRepository;
import unithon.uniletter.member.Member;
import unithon.uniletter.member.repository.MemberRepository;
import unithon.uniletter.message.dto.MessageResponse;
import unithon.uniletter.message.repository.MessageRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

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
    public List<Event> findMemberEventAfterToday(final String nickName) {
        if (memberRepository.existsByNickname(nickName)) {
            throw new NotFoundException("닉네임에 해당하는 멤버를 찾을 수 없습니다.");
        }
        return eventRepository.findEventsAfterToday(nickName, LocalDate.now());
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
