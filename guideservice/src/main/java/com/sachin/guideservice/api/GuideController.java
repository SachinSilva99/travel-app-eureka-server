package com.sachin.guideservice.api;

import com.sachin.guideservice.dto.GuideDTO;
import com.sachin.guideservice.exception.ImageFileException;
import com.sachin.guideservice.service.GuideService;
import com.sachin.guideservice.util.StandardResponse;
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
@RequestMapping(value = "/api/v1/guides", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class GuideController {

    private final GuideService guideService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StandardResponse<String>> createUser(
            @Valid @RequestPart GuideDTO guideDTO,
            @RequestPart("guideIdImgFront") MultipartFile guideIdImgFront,
            @RequestPart("guideIdImgBack") MultipartFile guideIdImgBack,
            @RequestPart("guideProfileImage") MultipartFile guideProfileImage
    ) {

        try {
            validateImageFile(guideIdImgFront);
            validateImageFile(guideIdImgBack);
            validateImageFile(guideProfileImage);

            guideDTO.setGuideIdImgFront(encodeToBase64(guideIdImgFront));
            guideDTO.setGuideIdImgBack(encodeToBase64(guideIdImgBack));
            guideDTO.setGuideProfileImage(encodeToBase64(guideProfileImage));
        } catch (IOException e) {
            throw new ImageFileException("something wrong with images");
        }


        String userId = guideService.createGuide(guideDTO);
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
    public ResponseEntity<StandardResponse<GuideDTO>> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "Ok",
                        guideService.get(userId)
                ), HttpStatus.OK
        );
    }

    @PutMapping(value = "/{guideId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse<String>> updateUser(
            @Valid @RequestPart GuideDTO guideDTO,
            @RequestPart(required = false, value = "guideIdImgFront") MultipartFile guideIdImgFront,
            @RequestPart(required = false, value = "guideIdImgBack") MultipartFile guideIdImgBack,
            @RequestPart(required = false, value = "guideProfileImage") MultipartFile guideProfileImage,
            @PathVariable String guideId
    ) {

        try {
            if (guideIdImgFront != null) {

                guideDTO.setGuideIdImgFront(encodeToBase64(guideIdImgFront));
            }
            if (guideIdImgBack != null) {
                guideDTO.setGuideIdImgBack(encodeToBase64(guideIdImgBack));
            }
            if (guideProfileImage != null) {
                guideDTO.setGuideProfileImage(encodeToBase64(guideProfileImage));
            }
        } catch (IOException e) {
            throw new ImageFileException("something wrong with images");
        }
        guideService.update(guideId, guideDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("{guideId}")
    public ResponseEntity<StandardResponse<GuideDTO>> deleteUser(@PathVariable String guideId) {
        guideService.delete(guideId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse<List<GuideDTO>>> GetAllGuides() {
        System.out.println("hehe");
        return new ResponseEntity<>(new StandardResponse<>(200, "OK", guideService.getAll()), HttpStatus.OK);
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
