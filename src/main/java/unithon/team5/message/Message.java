package unithon.team5.message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import unithon.team5.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class Message extends BaseEntity {

    protected Message() {
        super(null);
    }

    private Message(final UUID senderId,
                    final UUID receiverId,
                    final String senderName,
                    final String content,
                    final MessageType type,
                    final LocalDateTime sendPlannedAt) {
        super(null);
        this.senderId = senderId;
        this.receiverId = receiverId;
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

    @Column(nullable = false)
    private String senderName;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(nullable = false)
    private LocalDateTime sendPlannedAt;
    
    private Boolean isRead;

    public void read() {
        isRead = true;
    }

    public static Message create(final UUID senderId,
                                 final UUID receiverId,
                                 final String senderName,
                                 final String content,
                                 final MessageType type,
                                 final LocalDateTime sendPlannedAt) {
        return new Message(senderId, receiverId, senderName, content, type, sendPlannedAt);
    }
}
