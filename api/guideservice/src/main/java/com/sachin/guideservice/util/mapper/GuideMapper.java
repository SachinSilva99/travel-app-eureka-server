package com.sachin.guideservice.util.mapper;


import com.sachin.guideservice.dto.GuideDTO;
import com.sachin.guideservice.entity.Guide;

public interface GuideMapper {
    Guide toGuide(GuideDTO guideDTO);
    public GuideDTO toGuideDto(Guide guide);
}
