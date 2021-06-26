package com.demet.curriculumvitae.repository;

import com.demet.curriculumvitae.model.cv.CurriculumVitae;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeRepository extends MongoRepository<CurriculumVitae, String> {
}
