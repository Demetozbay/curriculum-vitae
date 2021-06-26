package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.background.Education;

import javax.validation.constraints.*;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class EducationRequest implements Supplier<Education>, Function<Section, Education> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String school;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String degree;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String field;

    @NotNull
    @Min(0)
    private long startDate;

    @NotNull
    @Min(0)
    private long endDate;

    @NotNull
    @Min(0)
    @Max(5)
    private double grade;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String summary;

    @Override
    public Education get() {
        Education education = new Education();
        education.setId(UUID.randomUUID()
                            .toString());
        education.setSchool(school);
        education.setDegree(degree);
        education.setField(field);
        education.setStartDate(startDate);
        education.setEndDate(endDate);
        education.setGrade(grade);
        education.setSummary(summary);
        return education;
    }

    @Override
    public Education apply(Section section) {
        return null;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
