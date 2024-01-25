package com.sachin.userservice.auth.admin;

import com.sachin.userservice.auth.AuthenticationResponse;
import com.sachin.userservice.auth.AuthenticationService;
import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.exception.ImageFileException;
import com.sachin.userservice.util.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminRegisterController {
    private final AuthenticationService service;


    @PostMapping(
            value = "/register/admin",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StandardResponse<AuthenticationResponse>> register(
            @RequestPart @Valid UserDTO userDTO,
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
        return new ResponseEntity<>(
                new StandardResponse<>(
                        200,
                        "Ok",
                        service.register(userDTO)
                ), HttpStatus.OK);
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
