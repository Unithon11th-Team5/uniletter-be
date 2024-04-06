package unithon.uniletter.common.error;

public record ErrorResponse(
        int code,
        String message
) { }