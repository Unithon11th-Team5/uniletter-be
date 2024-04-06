package unithon.team5.message.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unithon.team5.common.error.NotFoundException;
import unithon.team5.member.Member;
import unithon.team5.member.repository.MemberRepository;
import unithon.team5.message.Message;
import unithon.team5.message.dto.MessageRequest;
import unithon.team5.message.dto.MessageResponse;
import unithon.team5.message.repository.MessageRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    // TODO: 추후 알림 기능 구현하면 추가 로직 구현 필요
    public void sendMessage(final MessageRequest request, final Member loginMember) {

        Member receiver = memberRepository.findByNickname(request.getReceiverNickname()).orElseThrow(
                () -> new NotFoundException(String.format("[%s] nickname not found", request.getReceiverNickname())));

        messageRepository.save(Message.create(
                loginMember.getId(),
                receiver.getId(),
                request.getSenderName(),
                request.getContent(),
                request.getType(),
                request.getSendPlannedAt()));
    }

    public List<MessageResponse> getUnreadMessages(final Member member) {

        return messageRepository.findByReceiverIdAndIsReadOrderBySendPlannedAtAsc(member.getId(), false)
                .stream().map(
                        message -> {
                            message.read();
                            return MessageResponse.of(message);
                        }
                ).toList();
    }
}
