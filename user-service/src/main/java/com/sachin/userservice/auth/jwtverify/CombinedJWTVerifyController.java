package com.sachin.userservice.auth.jwtverify;

import com.sachin.userservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CombinedJWTVerifyController {

    @PostMapping("/hotel")
    public ResponseEntity<StandardResponse<String>> checkHotel() {
        return new ResponseEntity<>(new StandardResponse<>(200, "OK", null), HttpStatus.OK);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<StandardResponse<String>> checkVehicle() {
        System.out.println("here");
        return new ResponseEntity<>(new StandardResponse<>(200, "OK", null), HttpStatus.OK);
    }
    @PostMapping("/travel")
    public ResponseEntity<StandardResponse<String>> checkTravel() {
        return new ResponseEntity<>(new StandardResponse<>(200, "OK", null), HttpStatus.OK);
    }
    @PostMapping("/gettravel")
    public ResponseEntity<StandardResponse<String>> checkGetTravel() {
        return new ResponseEntity<>(new StandardResponse<>(200, "OK", null), HttpStatus.OK);
    }
}