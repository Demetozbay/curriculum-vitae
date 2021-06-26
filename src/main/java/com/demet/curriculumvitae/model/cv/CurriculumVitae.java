package com.demet.curriculumvitae.model.cv;

import com.demet.curriculumvitae.model.Entity;

import java.util.List;

public class CurriculumVitae extends Entity {
    private String username;
    private List<Section> sectionList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }
}
