package unithon.uniletter.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import unithon.uniletter.common.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@ToString
public class Member extends BaseEntity {

    protected Member() {
        super(null);
    }

    private Member(final UUID id, final String name, final String nickname, final String identifier) {
        super(id);
        this.name = name;
        this.nickname = nickname;
        this.identifier = identifier;
    }

    @Builder
    public Member(final String name, final String nickname, final String identifier) {
        super(null);
        this.name = name;
        this.nickname = nickname;
        this.identifier = identifier;
    }

    @Column(unique = true, nullable = false)
    private String nickname;

    private String identifier;

    private String name;

    public static Member create(final String name, final String nickname, final String identifier) {
        return new Member(null, name, nickname, identifier);
    }
}
