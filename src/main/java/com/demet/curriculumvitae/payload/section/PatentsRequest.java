package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.accomplishments.Patents;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class PatentsRequest implements Supplier<Patents>, Function<Section, Patents> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String office;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String number;

    @NotNull
    @Min(0)
    private long date;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String summary;

    @Override
    public Patents get() {
        Patents patents = new Patents();
        patents.setId(UUID.randomUUID()
                          .toString());
        patents.setTitle(title);
        patents.setOffice(office);
        patents.setNumber(number);
        patents.setDate(date);
        patents.setSummary(summary);
        return patents;
    }

    @Override
    public Patents apply(Section section) {
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
