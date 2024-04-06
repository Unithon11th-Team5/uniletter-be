package unithon.uniletter.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unithon.uniletter.member.Member;
import unithon.uniletter.member.dto.MemberResponse;
import unithon.uniletter.member.service.MemberService;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<MemberResponse> findProfile(@RequestParam final String nickname) {
        final var member = memberService.findByNickName(nickname);
        final MemberResponse response = MemberResponse.from(member);
        return ResponseEntity.ok(response);
    }
}
