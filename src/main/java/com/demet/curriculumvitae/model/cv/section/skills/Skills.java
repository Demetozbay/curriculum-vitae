package com.demet.curriculumvitae.model.cv.section.skills;

import com.demet.curriculumvitae.model.cv.Section;

import java.util.List;

public class Skills extends Section {
    private List<Skill> skillList;

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
