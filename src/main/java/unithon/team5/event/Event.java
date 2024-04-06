package unithon.team5.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import unithon.team5.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
public class Event extends BaseEntity {

    protected Event() {
        super(null);
    }

    private Event(final UUID id, final UUID memberId, final String content, final LocalDateTime sendPlannedAt) {
        super(id);
        this.memberId = memberId;
        this.content = content;
        this.sendPlannedAt = sendPlannedAt;
    }

    @Column(updatable = false, nullable = false)
    private UUID memberId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime sendPlannedAt;

    public static Event create(final UUID memberId, final String content, final LocalDateTime expectedDeliveryDate) {
        return new Event(null, memberId, content, expectedDeliveryDate);
    }
}
