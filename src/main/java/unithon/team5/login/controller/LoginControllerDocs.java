package unithon.team5.login.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;
import unithon.team5.login.dto.LoginRequest;

public interface LoginControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @Operation(summary = "회원가입 및 로그인",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = {
                                    @ExampleObject(name = "LoginRequest Example", value = """
                                            {
                                                "token": "apple id toekn...",
                                                "email": "0217dayun@naver.com",
                                            }""")
                            })))
    ResponseEntity<Void> registerToken(@Valid @RequestBody final LoginRequest request);
}
