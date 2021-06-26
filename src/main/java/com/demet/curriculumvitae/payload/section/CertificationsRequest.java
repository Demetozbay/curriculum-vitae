package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.background.Certifications;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class CertificationsRequest implements Supplier<Certifications>, Function<Section, Certifications> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String organization;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String certificationId;

    @NotNull
    @Min(0)
    private long startDate;

    @NotNull
    @Min(0)
    private long endDate;

    @Override
    public Certifications get() {
        Certifications certifications = new Certifications();
        certifications.setId(UUID.randomUUID()
                                 .toString());
        certifications.setName(name);
        certifications.setOrganization(organization);
        certifications.setCertificationId(certificationId);
        certifications.setStartDate(startDate);
        certifications.setEndDate(endDate);
        return certifications;
    }

    @Override
    public Certifications apply(Section section) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
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
