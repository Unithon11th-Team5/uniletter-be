package unithon.uniletter.event.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import unithon.uniletter.event.EventType;

import java.time.LocalDate;

public record EventAddRequest(
        @NotEmpty
        @Length(max = 300)
        String content,

        @NotNull
        EventType type,

        @FutureOrPresent
        LocalDate plannedAt
) {
}
