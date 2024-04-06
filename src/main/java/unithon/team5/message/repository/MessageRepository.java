package unithon.team5.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unithon.team5.message.Message;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}
