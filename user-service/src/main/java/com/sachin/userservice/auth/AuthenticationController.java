package com.sachin.userservice.auth;

import com.sachin.userservice.config.LogoutService;
import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.entity.enums.USER_TYPE;
import com.sachin.userservice.exception.ImageFileException;
import com.sachin.userservice.util.StandardResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService service;




    @PostMapping(
            value = "/register",
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
            userDTO.setUserType(USER_TYPE.REGISTERED_USER);

            validateImageFile(profilePicture);
            validateImageFile(nicPassportFrontImg);
            validateImageFile(nicPassportBackImg);

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

    @PostMapping("/authenticate")
    public ResponseEntity<StandardResponse<AuthenticationResponse>> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        System.out.println("hehe");
        return new ResponseEntity<>(
                new StandardResponse<>(
                        200,
                        "Ok",
                        service.authenticate(request)
                ), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    private String encodeToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    private void validateImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ImageFileException("Invalid image file. Only image files are allowed.");
        }
    }
}
