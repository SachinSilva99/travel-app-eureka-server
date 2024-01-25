package com.sachin.guideservice.repo;

import com.sachin.guideservice.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepo extends MongoRepository<Guide,String> {
}
