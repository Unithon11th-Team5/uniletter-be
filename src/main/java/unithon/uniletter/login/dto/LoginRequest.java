package unithon.uniletter.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Optional;

public record LoginRequest(
        String name,
        @NotEmpty
        String token,
        @Email
        String email
) {

}
