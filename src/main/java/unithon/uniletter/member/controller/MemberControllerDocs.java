package unithon.uniletter.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestParam;
import unithon.uniletter.member.dto.MemberResponse;

public interface MemberControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @Operation(summary = "닉네임으로 사용자 정보 조회")
    ResponseEntity<MemberResponse> findProfile(@RequestParam final String nickname);
}
