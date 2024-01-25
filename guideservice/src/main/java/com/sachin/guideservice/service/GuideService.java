package com.sachin.guideservice.service;


import com.sachin.guideservice.dto.GuideDTO;
import com.sachin.guideservice.exception.NotFoundException;

import java.util.List;

public interface GuideService {
    String createGuide(GuideDTO guideDTO);

    void update(String guideId, GuideDTO guideDTO) throws NotFoundException;

    void delete(String guideId) throws NotFoundException;

    GuideDTO get(String guideId) throws NotFoundException;

    List<GuideDTO> getAll();
}
