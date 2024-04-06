package unithon.team5.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unithon.team5.login.dto.JwtTokenResponse;
import unithon.team5.login.dto.LoginRequest;
import unithon.team5.login.dto.LoginResponse;
import unithon.team5.login.service.LoginService;
import unithon.team5.member.Member;

@RestController
@RequiredArgsConstructor
public class LoginController implements LoginControllerDocs {

    private final LoginService loginService;

    @PostMapping("/login/apple")
    public ResponseEntity<JwtTokenResponse> registerToken(
            @Valid @RequestBody final LoginRequest loginRequest
    ) {
        final var token = loginService.createToken(loginRequest.token(), loginRequest.email());
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @GetMapping("/info")
    public ResponseEntity<LoginResponse> getLoginInfo(final Member member) {
        return ResponseEntity.ok(LoginResponse.of(member));
    }
}
