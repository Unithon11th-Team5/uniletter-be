package unithon.team5.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import unithon.team5.common.error.ErrorResponse;
import unithon.team5.event.dto.EventAddRequest;
import unithon.team5.event.dto.EventListResponse;
import unithon.team5.event.dto.TypeListResponse;
import unithon.team5.member.Member;

public interface EventControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "이벤트 추가",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventAddRequest.class),
                            examples = {
                                    @ExampleObject(name = "EventAddRequestExample", value = """
                                            {
                                            \t"content": "이벤트 내용",
                                            \t"type": "이벤트 타입",
                                            \t"plannedAt": "해당 이벤트 날짜"
                                            }""")
                            })))
    ResponseEntity<Void> addEvent(@RequestBody @Valid final EventAddRequest eventAddRequest,
                                  @Parameter(hidden = true) final Member member);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @Operation(summary = "이벤트 조회")
    ResponseEntity<EventListResponse> readAllEvent(
            @Parameter(description = "member id", example = "ete-dfdfd-fdfder", required = true)
            @RequestParam final String memberId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
    })
    @Operation(summary = "이벤트 타입 조회")
    ResponseEntity<TypeListResponse> readAllTypes();
}
