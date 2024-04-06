package unithon.team5.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import unithon.team5.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class Event extends BaseEntity {

    protected Event() {
        super(null);
    }

    private Event(final UUID id, final UUID memberId, final String content, final LocalDateTime plannedAt) {
        super(id);
        this.memberId = memberId;
        this.content = content;
        this.plannedAt = plannedAt;
    }

    @Builder
    public Event(final UUID memberId, final String content, final LocalDateTime plannedAt) {
        this(null, memberId, content, plannedAt);
    }

    @Column(updatable = false, nullable = false)
    private UUID memberId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime plannedAt;

    public static Event create(final UUID memberId, final String content, final LocalDateTime plannedAt) {
        return new Event(null, memberId, content, plannedAt);
    }
}
