package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.background.VolunteerExperience;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class VolunteerExperienceRequest
        implements Supplier<VolunteerExperience>, Function<Section, VolunteerExperience> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String organization;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String role;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String cause;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String summary;

    @NotNull
    @Min(0)
    private long startDate;

    @NotNull
    @Min(0)
    private long endDate;

    @Override
    public VolunteerExperience get() {
        VolunteerExperience experience = new VolunteerExperience();
        experience.setId(UUID.randomUUID()
                             .toString());
        experience.setOrganization(organization);
        experience.setRole(role);
        experience.setCause(cause);
        experience.setSummary(summary);
        experience.setStartDate(startDate);
        experience.setEndDate(endDate);
        return experience;
    }

    @Override
    public VolunteerExperience apply(Section section) {
        return null;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
