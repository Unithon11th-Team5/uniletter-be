package unithon.team5.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unithon.team5.member.Member;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByNickname(String nickname);
}
