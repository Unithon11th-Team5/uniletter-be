package unithon.team5.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unithon.team5.common.error.NotFoundException;
import unithon.team5.member.Member;

import java.util.Optional;
import java.util.UUID;


public interface MemberRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByIdentifier(final String identifier);

    Boolean existsByNickname(final String nickName);

    default Member getByNickname(final String nickName) {
        return findByNickname(nickName)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] nickname not found", nickName)));
    }
}
