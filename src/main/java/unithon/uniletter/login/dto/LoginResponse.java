package unithon.uniletter.login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import unithon.uniletter.member.Member;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class LoginResponse {

    private final UUID id;
    private final String nickname;
    private final String identifier;
    private final String name;

    public static LoginResponse of(Member member) {
        return new LoginResponse(
                member.getId(),
                member.getNickname(),
                member.getIdentifier(),
                member.getName()
        );
    }
}
