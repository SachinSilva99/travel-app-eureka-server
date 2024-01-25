package com.sachin.guideservice.api;

import com.sachin.guideservice.dto.GuideDTO;
import com.sachin.guideservice.service.GuideService;
import com.sachin.guideservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/getguides", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class GetGuideController {
    private final GuideService guideService;

    @GetMapping( "/{guideId}")
    public ResponseEntity<StandardResponse<GuideDTO>> getGuide(@PathVariable String guideId) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "Ok",
                        guideService.get(guideId)
                ), HttpStatus.OK
        );
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse<List<GuideDTO>>> GetAllGuides() {
        return new ResponseEntity<>(new StandardResponse<>(200, "OK", guideService.getAll()), HttpStatus.OK);
    }
}
