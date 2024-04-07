package unithon.uniletter.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unithon.uniletter.common.error.NotFoundException;
import unithon.uniletter.message.Message;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query("""
            select message
            from Message message
            where message.isRead = false
            and message.receiverId = :receiverId
            and message.sendPlannedAt <= :today
            order by message.sendPlannedAt asc
            """)
    List<Message> readArriveMessage(final UUID receiverId, final LocalDate today);

    List<Message> findByReceiverIdOrderBySendPlannedAtDesc(UUID receiverId);

    List<Message> findAllByEventIdOrderBySendPlannedAtDesc(UUID eventId);

    default Message getById(final UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException(String.format("[%s] message id not found", id)));
    }
}
