package unithon.uniletter.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon.uniletter.member.Member;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private UUID id;
    private String nickname;
    private String identifier;

    public static LoginResponse of(Member member) {
        return new LoginResponse(
                member.getId(),
                member.getNickname(),
                member.getIdentifier()
        );
    }
}
