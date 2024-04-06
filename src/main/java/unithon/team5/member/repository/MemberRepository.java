package unithon.team5.member.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import unithon.team5.member.Member;


public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByIdentifier(final String identifier);
}
