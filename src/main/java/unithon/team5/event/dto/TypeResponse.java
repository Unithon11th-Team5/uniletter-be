package unithon.team5.event.dto;

import unithon.team5.event.EventType;

public record TypeResponse(String englishName, String koreanName) {

    public static TypeResponse from(final EventType type) {
        return new TypeResponse(type.getEnglishName(), type.getKoreanName());
    }
}
