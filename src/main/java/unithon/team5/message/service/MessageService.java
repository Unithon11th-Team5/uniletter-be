package unithon.team5.message.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unithon.team5.common.error.ForbiddenException;
import unithon.team5.common.error.NotFoundException;
import unithon.team5.event.repository.EventRepository;
import unithon.team5.member.Member;
import unithon.team5.member.repository.MemberRepository;
import unithon.team5.message.Message;
import unithon.team5.message.dto.MessageRequest;
import unithon.team5.message.dto.MessageResponse;
import unithon.team5.message.repository.MessageRepository;

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

        Member receiver = memberRepository.findByNickname(request.getReceiverNickname()).orElseThrow(
                () -> new NotFoundException(String.format("[%s] nickname not found", request.getReceiverNickname())));

        messageRepository.save(Message.create(
                loginMember.getId(),
                receiver.getId(),
                request.getEventId(),
                request.getSenderName(),
                request.getContent(),
                request.getType(),
                request.getSendPlannedAt()));
    }

    public List<MessageResponse> getUnreadMessages(final Member member) {

        return getMessageWithEvent(messageRepository.findByReceiverIdAndIsReadOrderBySendPlannedAtAsc(member.getId(), false));
    }

    public List<MessageResponse> getAllMessages(final Member member) {

        return getMessageWithEvent(messageRepository.findByReceiverIdOrderBySendPlannedAtDesc(member.getId()));
    }

    public MessageResponse getMessage(final UUID id, final Member member) {

        Message findMessage = messageRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("[%s] message id not found", id)));

        if (!findMessage.getReceiverId().equals(member.getId())) {
            throw new ForbiddenException(String.format("[%s] message id's receiver is not equal with current member [%s]", id, member.getId()));
        }

        return MessageResponse.of(
                findMessage,
                eventRepository.findById(findMessage.getEventId()).orElseThrow(
                        () -> new NotFoundException(String.format("[%s] event id not found", findMessage.getEventId()))));
    }

    private List<MessageResponse> getMessageWithEvent(List<Message> messages) {
        return messages
                .stream().map(
                        message -> {
                            if (message.getEventId() != null) {
                                return MessageResponse.of(
                                        message,
                                        eventRepository.findById(message.getEventId()).orElseThrow(
                                                () -> new NotFoundException(String.format("[%s] event id not found", message.getEventId()))));
                            }
                            return MessageResponse.of(message, null);
                        }
                ).toList();
    }
}
