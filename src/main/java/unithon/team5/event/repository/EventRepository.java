package unithon.team5.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unithon.team5.event.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
