package com.wk.lab04;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class BackOfficeController {

    @PostMapping("/endpoint")
    public ResponseEntity<?> createIssue(@RequestBody String requestBody) {
        System.out.println("Request Body: " + requestBody);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
