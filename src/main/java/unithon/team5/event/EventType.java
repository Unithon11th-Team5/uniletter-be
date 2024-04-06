package unithon.team5.event;

import lombok.Getter;

@Getter
public enum EventType {

    CHEER_UP("응원", "CHEER UP"),
    BIRTHDAY("생일", "BIRTHDAY"),
    THANKSGIVING("명절", "THANKS_GIVING"),
    CHRISTMAS("연말", "CHRISTMAS"),
    MARRIAGE("결혼", "MARRIAGE"),
    NON_EVENT("일상", "NON_EVENT");

    private final String koreanName;
    private final String englishName;

    EventType(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

}
