package unithon.team5.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import unithon.team5.common.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@ToString
public class Member extends BaseEntity {

    protected Member() {
        super(null);
    }

    private Member(final UUID id, final String nickname, final String identifier) {
        super(id);
        this.nickname = nickname;
        this.identifier = identifier;
    }

    @Builder
    public Member(final String nickname, final String identifier) {
        super(null);
        this.nickname = nickname;
        this.identifier = identifier;
    }

    @Column(unique = true, nullable = false)
    private String nickname;

    private String identifier;

    public static Member create(final String nickname, final String identifier) {
        return new Member(null, nickname, identifier);
    }
}
