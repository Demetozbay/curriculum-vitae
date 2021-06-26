package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.section.skills.Skill;

import javax.validation.constraints.*;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class SkillRequest implements Supplier<Skill>, Function<Skill, Skill> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String name;

    @NotNull
    @Min(0)
    @Max(5)
    private int point;

    @Override
    public Skill get() {
        Skill skill = new Skill();
        skill.setId(UUID.randomUUID()
                        .toString());
        skill.setName(name);
        skill.setPoint(point);
        return skill;
    }

    @Override
    public Skill apply(Skill section) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
