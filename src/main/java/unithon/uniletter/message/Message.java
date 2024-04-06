package unithon.uniletter.message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import unithon.uniletter.common.BaseEntity;
import unithon.uniletter.event.EventType;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
public class Message extends BaseEntity {

    protected Message() {
        super(null);
    }

    private Message(final UUID senderId,
                    final UUID receiverId,
                    final UUID eventId,
                    final String senderName,
                    final String content,
                    final EventType type,
                    final LocalDate sendPlannedAt) {
        super(null);
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.eventId = eventId;
        this.senderName = senderName;
        this.content = content;
        this.type = type;
        this.sendPlannedAt = sendPlannedAt;
        this.isRead = false;
    }

    @Column(nullable = false, updatable = false)
    private UUID senderId;

    @Column(nullable = false, updatable = false)
    private UUID receiverId;

    private UUID eventId;

    @Column(nullable = false)
    private String senderName;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(nullable = false)
    private LocalDate sendPlannedAt;

    private Boolean isRead;

    public void read() {
        isRead = true;
    }

    public static Message create(final UUID senderId,
                                 final UUID receiverId,
                                 final UUID eventId,
                                 final String senderName,
                                 final String content,
                                 final EventType type,
                                 final LocalDate sendPlannedAt) {
        return new Message(senderId, receiverId, eventId, senderName, content, type, sendPlannedAt);
    }

    public Optional<UUID> getOptionalEventId() {
        return Optional.ofNullable(eventId);
    }
}
