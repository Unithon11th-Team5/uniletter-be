package unithon.team5.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unithon.team5.login.dto.JwtTokenResponse;
import unithon.team5.login.dto.LoginRequest;
import unithon.team5.login.service.LoginService;

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
}
