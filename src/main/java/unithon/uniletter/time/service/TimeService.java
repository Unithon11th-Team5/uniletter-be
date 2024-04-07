package unithon.uniletter.time.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class TimeService {

    private final TimeGenerator timeGenerator;

    public void setServerTime(final LocalDate localDate) {
        timeGenerator.setLocalDate(localDate);
    }
}
