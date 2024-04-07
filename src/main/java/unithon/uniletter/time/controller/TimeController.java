package unithon.uniletter.time.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unithon.uniletter.time.TimeRequest;
import unithon.uniletter.time.service.TimeService;

@RestController
@RequiredArgsConstructor
public class TimeController {

    private final TimeService timeService;

    @PostMapping("/time")
    public ResponseEntity<Void> setServerTime(@RequestBody final TimeRequest timeRequest) {
        timeService.setServerTime(timeRequest.localDate());
        return ResponseEntity.ok().build();
    }
}
