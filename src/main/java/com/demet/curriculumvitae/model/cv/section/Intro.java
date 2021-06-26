package com.demet.curriculumvitae.model.cv.section;

import com.demet.curriculumvitae.model.cv.Section;

public class Intro extends Section {
    private String imageUri;
    private String visibleName;
    private String visiblePosition;
    private String visibleLocation;

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
