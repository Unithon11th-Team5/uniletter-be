package unithon.uniletter.time.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TimeGenerator {

    private LocalDate localDate;

    public LocalDate generate() {
        return Optional.ofNullable(localDate)
                .orElseGet(LocalDate::now);
    }

    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }
}
