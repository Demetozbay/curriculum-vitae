package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.background.WorkExperience;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class WorkExperienceRequest implements Supplier<WorkExperience>, Function<Section, WorkExperience> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String type;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String company;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String location;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String summary;

    @NotNull
    @Min(0)
    private long startDate;

    @NotNull
    @Min(0)
    private long endDate;

    @Override
    public WorkExperience get() {
        WorkExperience experience = new WorkExperience();
        experience.setId(UUID.randomUUID()
                             .toString());
        experience.setTitle(title);
        experience.setType(type);
        experience.setCompany(company);
        experience.setLocation(location);
        experience.setSummary(summary);
        experience.setStartDate(startDate);
        experience.setEndDate(endDate);
        return experience;
    }

    @Override
    public WorkExperience apply(Section section) {
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
}
