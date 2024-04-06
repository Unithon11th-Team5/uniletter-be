package unithon.team5.event.dto;

import java.time.LocalDate;

public record EventAddRequest(String content, LocalDate plannedAt) {
}
