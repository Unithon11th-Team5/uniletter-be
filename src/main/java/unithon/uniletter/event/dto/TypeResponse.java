package unithon.uniletter.event.dto;

import unithon.uniletter.event.EventType;

public record TypeResponse(String englishName, String koreanName) {

    public static TypeResponse from(final EventType type) {
        return new TypeResponse(type.getEnglishName(), type.getKoreanName());
    }
}
