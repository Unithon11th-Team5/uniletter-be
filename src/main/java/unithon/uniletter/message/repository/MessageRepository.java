package unithon.uniletter.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unithon.uniletter.common.error.NotFoundException;
import unithon.uniletter.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByReceiverIdAndIsReadOrderBySendPlannedAtAsc(UUID receiverId, Boolean isRead);

    List<Message> findByReceiverIdOrderBySendPlannedAtDesc(UUID receiverId);

    List<Message> findAllByEventIdOrderBySendPlannedAtDesc(UUID eventId);

    default Message getById(final UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException(String.format("[%s] message id not found", id)));
    }
}
