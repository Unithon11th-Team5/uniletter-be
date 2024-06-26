package unithon.uniletter.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon.uniletter.login.dto.JwtTokenResponse;
import unithon.uniletter.login.dto.LoginRequest;
import unithon.uniletter.login.dto.LoginResponse;
import unithon.uniletter.login.service.LoginService;
import unithon.uniletter.member.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController implements LoginControllerDocs {

    private final LoginService loginService;

    @PostMapping("/apple")
    public ResponseEntity<JwtTokenResponse> registerToken(
            @Valid @RequestBody final LoginRequest loginRequest
    ) {
        final var token = loginService.createToken(loginRequest);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @GetMapping("/info")
    public ResponseEntity<LoginResponse> getLoginInfo(final Member member) {
        return ResponseEntity.ok(LoginResponse.of(member));
    }
}
