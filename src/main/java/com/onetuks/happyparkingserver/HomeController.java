package com.onetuks.happyparkingserver;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final String GREETING_COMMENT = "Hello, Happy Parking Server!";

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> home() {
        return ResponseEntity
                .status(OK)
                .body(GREETING_COMMENT);
    }

}
