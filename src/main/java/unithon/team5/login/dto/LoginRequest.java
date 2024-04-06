package unithon.team5.login.dto;

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

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
