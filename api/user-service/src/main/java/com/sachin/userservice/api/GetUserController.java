package com.sachin.userservice.api;

import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.service.UserService;
import com.sachin.userservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/getusers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class GetUserController {
    private final UserService userService;
    @GetMapping(value = "/{userId}")
    public ResponseEntity<StandardResponse<UserDTO>> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "Ok",
                        userService.get(userId)
                ), HttpStatus.OK
        );
    }
}
