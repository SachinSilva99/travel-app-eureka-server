package com.sachin.guideservice.service.impl;


import com.sachin.guideservice.dto.GuideDTO;
import com.sachin.guideservice.entity.Guide;
import com.sachin.guideservice.exception.NotFoundException;
import com.sachin.guideservice.repo.GuideRepo;
import com.sachin.guideservice.service.GuideService;
import com.sachin.guideservice.util.mapper.GuideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final GuideRepo guideRepo;
    private final GuideMapper mapper;


    @Override
    public String createGuide(GuideDTO guideDTO) {
        Guide guide = mapper.toGuide(guideDTO);
        return guideRepo.save(guide).getGuideId();
    }

    @Override
    public void update(String guideId, GuideDTO dto) throws NotFoundException {
        Guide guide = guideRepo.findById(guideId)
                .orElseThrow(() -> new NotFoundException("Guide with ID " + guideId + " not found"));

        if (dto.getGuideProfileImage() != null) {
            guide.setGuideProfileImage(dto.getGuideProfileImage());
        }
        if (dto.getGuideIdImgFront() != null) {
            guide.setGuideIdImgFront(dto.getGuideIdImgFront());
        }
        if (dto.getGuideIdImgBack() != null) {
            guide.setGuideIdImgBack(dto.getGuideIdImgBack());
        }
        guide.setGuideName(dto.getGuideName());
        guide.setGuideManDayValue(dto.getGuideManDayValue());
        guide.setDob(dto.getDob());
        guide.setGender(dto.getGender());
        guide.setContact(dto.getContact());

        guide.setGuideExperience(dto.getGuideExperience());
        guide.setGuide_remarks(dto.getGuide_remarks());
        guideRepo.save(guide);
    }

    @Override
    public void delete(String guideId) throws NotFoundException {
        if (!guideRepo.existsById(guideId)) {
            throw new NotFoundException("Guide with ID " + guideId + " not found");
        }
        guideRepo.deleteById(guideId);
    }

    @Override
    public GuideDTO get(String guideId) throws NotFoundException {
        Guide guide = guideRepo.findById(guideId)
                .orElseThrow(() -> new NotFoundException("Guide with ID " + guideId + " not found"));
        return mapper.toGuideDto(guide);

    }

    @Override
    public List<GuideDTO> getAll() {
        List<Guide> all = guideRepo.findAll();
        System.out.println(all);
        return guideRepo.findAll().stream().map(mapper::toGuideDto).toList();
    }
}
