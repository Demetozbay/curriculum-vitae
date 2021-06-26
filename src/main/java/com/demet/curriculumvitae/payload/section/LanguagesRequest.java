package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.accomplishments.Languages;

import javax.validation.constraints.*;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class LanguagesRequest implements Supplier<Languages>, Function<Section, Languages> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String language;

    @Min(0)
    @Max(5)
    private int proficiency;

    @Override
    public Languages get() {
        Languages languages = new Languages();
        languages.setId(UUID.randomUUID()
                            .toString());
        languages.setLanguage(language);
        languages.setProficiency(proficiency);
        return languages;
    }

    @Override
    public Languages apply(Section section) {
        return null;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
}
