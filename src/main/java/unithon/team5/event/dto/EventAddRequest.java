package unithon.team5.event.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record EventAddRequest(
        @NotEmpty
        @Length(max = 30)
        String content,
        @Future
        LocalDate plannedAt
) {
}
