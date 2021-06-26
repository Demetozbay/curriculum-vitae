package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.accomplishments.Projects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class ProjectsRequest implements Supplier<Projects>, Function<Section, Projects> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String name;

    @NotNull
    @Min(0)
    private long startDate;

    @NotNull
    @Min(0)
    private long endDate;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String summary;

    @Override
    public Projects get() {
        Projects projects = new Projects();
        projects.setId(UUID.randomUUID()
                           .toString());
        projects.setName(name);
        projects.setStartDate(startDate);
        projects.setEndDate(endDate);
        projects.setSummary(summary);
        return projects;
    }

    @Override
    public Projects apply(Section section) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
