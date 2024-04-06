package unithon.uniletter.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unithon.uniletter.common.error.NotFoundException;
import unithon.uniletter.member.Member;

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
