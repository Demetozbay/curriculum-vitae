package com.demet.curriculumvitae.payload.section;

import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.accomplishments.Publications;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class PublicationsRequest implements Supplier<Publications>, Function<Section, Publications> {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String publisher;

    @NotNull
    @Min(0)
    private long date;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 35)
    private String summary;

    @Override
    public Publications get() {
        Publications publications = new Publications();
        publications.setId(UUID.randomUUID().toString());
        publications.setTitle(title);
        publications.setPublisher(publisher);
        publications.setDate(date);
        publications.setSummary(summary);
        return publications;
    }

    @Override
    public Publications apply(Section section) {
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
