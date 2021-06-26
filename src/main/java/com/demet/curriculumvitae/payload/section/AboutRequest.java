package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.About;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class AboutRequest implements Supplier<About>, Function<Section, About> {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String summary;

    @Override
    public About get() {
        About about = new About();
        about.setId(UUID.randomUUID()
                        .toString());
        about.setSummary(summary);
        return about;
    }

    @Override
    public About apply(Section section) {
        return null;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
