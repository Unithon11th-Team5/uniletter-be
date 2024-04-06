package unithon.uniletter.message.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unithon.uniletter.common.error.ForbiddenException;
import unithon.uniletter.event.repository.EventRepository;
import unithon.uniletter.member.Member;
import unithon.uniletter.member.repository.MemberRepository;
import unithon.uniletter.message.Message;
import unithon.uniletter.message.dto.MessageRequest;
import unithon.uniletter.message.dto.MessageResponse;
import unithon.uniletter.message.repository.MessageRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    // TODO: 추후 알림 기능 구현하면 추가 로직 구현 필요
    public void sendMessage(final MessageRequest request, final Member loginMember) {

        final Member receiver = memberRepository.getByNickname(request.getReceiverNickname());

        messageRepository.save(Message.create(
                loginMember.getId(),
                receiver.getId(),
                request.getEventId(),
                request.getSenderName(),
                request.getContent(),
                request.getType(),
                request.getSendPlannedAt()));
    }

    @Transactional
    public List<MessageResponse> getUnreadMessages(final Member member) {
        final List<Message> messages = messageRepository.findByReceiverIdAndIsReadOrderBySendPlannedAtAsc(member.getId(), false);
        messages.forEach(Message::read);
        return getMessageWithEvent(messages);
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> getAllMessages(final Member member) {

        return getMessageWithEvent(messageRepository.findByReceiverIdOrderBySendPlannedAtDesc(member.getId()));
    }

    @Transactional(readOnly = true)
    public MessageResponse getMessage(final UUID id, final Member member) {

        final Message findMessage = messageRepository.getById(id);

        if (!findMessage.getReceiverId().equals(member.getId())) {
            throw new ForbiddenException(String.format("[%s] message id's receiver is not equal with current member [%s]", id, member.getId()));
        }

        return createMessageResponse(findMessage);
    }

    private List<MessageResponse> getMessageWithEvent(final List<Message> messages) {
        return messages.stream()
                .map(this::createMessageResponse)
                .toList();
    }

    private MessageResponse createMessageResponse(final Message message) {
        return message.getOptionalEventId()
                .map(eventId -> MessageResponse.of(message, eventRepository.getById(eventId)))
                .orElseGet(() -> MessageResponse.of(message, null));
    }
}
