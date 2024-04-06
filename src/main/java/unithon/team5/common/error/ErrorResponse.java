package unithon.team5.common.error;

public record ErrorResponse(
        int code,
        String message
) { }