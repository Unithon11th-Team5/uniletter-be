package unithon.uniletter.event.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import unithon.uniletter.event.EventType;

import java.time.LocalDate;

public record EventAddRequest(
        @NotEmpty
        @Length(max = 30)
        String content,

        @NotNull
        EventType type,

        @Future
        LocalDate plannedAt
) {
}
