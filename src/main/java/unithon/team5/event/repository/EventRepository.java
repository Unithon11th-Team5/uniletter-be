package unithon.team5.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unithon.team5.event.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("""
            select event
            from Event event
            where event.memberId = :memberId
            AND event.plannedAt >= :today
            order by event.plannedAt ASC
            """)
    List<Event> findEventsAfterToday(final UUID memberId, final LocalDate today);
}
