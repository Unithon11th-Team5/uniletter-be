package unithon.team5.event.dto;

import unithon.team5.event.Event;

import java.time.LocalDate;
import java.util.List;

public record EventResponse(String content, LocalDate plannedAt) {

    public static List<EventResponse> createList(final List<Event> event) {
        return event.stream()
                .map(EventResponse::from)
                .toList();
    }

    public static EventResponse from(final Event event) {
        return new EventResponse(event.getContent(), event.getPlannedAt().toLocalDate());
    }
}
