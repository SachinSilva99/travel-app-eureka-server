package com.sachin.userservice.api;

import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.exception.ImageFileException;
import com.sachin.userservice.service.UserService;
import com.sachin.userservice.util.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StandardResponse<String>> createUser(
            @Valid @RequestPart UserDTO userDTO,
            @RequestPart MultipartFile profilePicture,
            @RequestPart MultipartFile nicPassportFrontImg,
            @RequestPart MultipartFile nicPassportBackImg
    ) {

        try {
            userDTO.setProfilePicture(encodeToBase64(profilePicture));
            userDTO.setNicPassportFrontImg(encodeToBase64(nicPassportFrontImg));
            userDTO.setNicPassportBackImg(encodeToBase64(nicPassportBackImg));
        } catch (IOException e) {
            throw new ImageFileException("something wrong with images");
        }
        String userId = userService.createUser(userDTO);
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.CREATED.value(),
                        " saved successfully",
                        userId
                ),
                HttpStatus.CREATED
        );
    }

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


    @GetMapping(value = "/travel/{userId}")
    public ResponseEntity<StandardResponse<UserDTO>> getUserRequestByTravel(@PathVariable String userId) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "Ok",
                        userService.get(userId)
                ), HttpStatus.OK
        );
    }


    @PutMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse<String>> updateUser(
            @Valid @RequestPart UserDTO userDTO,
            @RequestPart(required = false) MultipartFile profilePicture,
            @RequestPart(required = false) MultipartFile nicPassportFrontImg,
            @RequestPart(required = false) MultipartFile nicPassportBackImg,
            @PathVariable String userId
    ) throws IOException {
        System.out.println("reached put api");
        if (profilePicture != null) {
            System.out.println("here");
            userDTO.setProfilePicture(encodeToBase64(profilePicture));
        }
        if (nicPassportBackImg != null) {
            userDTO.setNicPassportBackImg(encodeToBase64(nicPassportBackImg));
        }
        if (nicPassportFrontImg != null) {
            userDTO.setNicPassportFrontImg(encodeToBase64(nicPassportFrontImg));
        }
        userService.update(userId, userDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<StandardResponse<UserDTO>> deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse<List<UserDTO>>> getAllUsers() {
        System.out.println("get all");
        return new ResponseEntity<>(new StandardResponse<>(200, "OK", userService.getAll()), HttpStatus.OK);
    }


    private String encodeToBase64(MultipartFile file) throws IOException {
        validateImageFile(file);
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    private void validateImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ImageFileException("Invalid image file. Only image files are allowed.");
        }
    }
}
