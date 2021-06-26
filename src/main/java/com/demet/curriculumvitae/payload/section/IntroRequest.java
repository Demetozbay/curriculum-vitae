package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.Intro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class IntroRequest implements Supplier<Intro>, Function<Section, Intro> {

    @NotNull
    @NotBlank
    @Size(min = 0, max = 255)
    private String imageUri;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 55)
    private String visibleName;

    @Size(min = 3, max = 35)
    private String visiblePosition;

    @Size(min = 3, max = 35)
    private String visibleLocation;

    @Override
    public Intro get() {
        Intro intro = new Intro();
        intro.setId(UUID.randomUUID()
                        .toString());
        intro.setImageUri(imageUri);
        intro.setVisibleName(visibleName);
        intro.setVisiblePosition(visiblePosition);
        intro.setVisibleLocation(visibleLocation);
        return intro;
    }

    @Override
    public Intro apply(Section section) {
        return null;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public void setVisibleName(String visibleName) {
        this.visibleName = visibleName;
    }

    public String getVisiblePosition() {
        return visiblePosition;
    }

    public void setVisiblePosition(String visiblePosition) {
        this.visiblePosition = visiblePosition;
    }

    public String getVisibleLocation() {
        return visibleLocation;
    }

    public void setVisibleLocation(String visibleLocation) {
        this.visibleLocation = visibleLocation;
    }
}
