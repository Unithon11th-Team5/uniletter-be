package unithon.team5.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty
        String token,
        @Email
        String email
) {

}
