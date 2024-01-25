package com.sachin.guideservice.util.mapper;

import com.sachin.guideservice.dto.GuideDTO;
import com.sachin.guideservice.entity.Guide;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuideMapperImpl implements GuideMapper {

    private final ModelMapper mapper;


    @Override
    public Guide toGuide(GuideDTO guideDTO) {
        return mapper.map(guideDTO, Guide.class);
    }

    @Override
    public GuideDTO toGuideDto(Guide guide) {
        return mapper.map(guide,GuideDTO.class);
    }
}
