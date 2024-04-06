package unithon.team5.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import unithon.team5.common.BaseEntity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
public class Event extends BaseEntity {

    protected Event() {
        super(null);
    }

    private Event(final UUID id, final UUID memberId, final String content, final EventType type, final LocalDate plannedAt) {
        super(id);
        this.memberId = memberId;
        this.content = content;
        this.type = type;
        this.plannedAt = plannedAt;
    }

    @Builder
    public Event(final UUID memberId, final String content, final EventType type, final LocalDate plannedAt) {
        this(null, memberId, content, type, plannedAt);
    }

    @Column(updatable = false, nullable = false)
    private UUID memberId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(nullable = false)
    private LocalDate plannedAt;

    public static Event create(final UUID memberId, final String content, final EventType type, final LocalDate plannedAt) {
        return new Event(null, memberId, content, type, plannedAt);
    }
}
