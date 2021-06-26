package com.demet.curriculumvitae.service;

import com.demet.curriculumvitae.model.cv.CurriculumVitae;
import com.demet.curriculumvitae.repository.CurriculumVitaeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CurriculumVitaeService {
    private final CurriculumVitaeRepository repository;

    public CurriculumVitaeService(CurriculumVitaeRepository repository) {this.repository = repository;}

    public Page<CurriculumVitae> getByPage(Map<String, Object> queryParams) {
        return null;
    }

    public Page<CurriculumVitae> getByPage(String username, Map<String, Object> queryParams) {
        return null;
    }

    public CurriculumVitae getById(String id) {
        return null;
    }

    public CurriculumVitae create(String username) {
        return null;
    }

    public CurriculumVitae update(CurriculumVitae vitae) {
        return null;
    }

    public void deleteById(String id) {

    }
}
