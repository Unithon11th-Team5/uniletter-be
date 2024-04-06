package unithon.uniletter.member.dto;

import unithon.uniletter.member.Member;

import java.util.UUID;

public record MemberResponse(UUID id, String nickname, String identifier, String name) {

    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(), member.getNickname(), member.getIdentifier(), member.getName());
    }
}
